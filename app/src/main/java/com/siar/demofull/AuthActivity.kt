package com.siar.demofull

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.siar.demofull.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val GOOGLE_SIGNIN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Analitycs Events
        val analitycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message", "Integracion de Firebase completa")
        analitycs.logEvent("InitScreen", bundle)

        // Remote config
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        val firebaseConfig = Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
        firebaseConfig.setDefaultsAsync(mapOf("auth_app_title" to ""))
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                val paramTitle = Firebase.remoteConfig.getString("auth_app_title")
                setup(paramTitle)
            }else{
                setup(getString(R.string.app_title))
            }
        }

        notification()

        session()
    }

    override fun onStart() {
        super.onStart()

        binding.authLayout.visibility = VISIBLE
    }

    private fun notification() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            it.result?.let {
                println("Este es el token del dispositivo: ${it}")
            }
        }
        // Temas (Topics)
        FirebaseMessaging.getInstance().subscribeToTopic("tutorial")

        // Recuperar info de las notificaciones
        val url: String? = intent.getStringExtra("url")
        url?.let {
            println("Ha llegado informacion en una push: $it")
        }
    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider != null) {
            binding.authLayout.visibility = GONE
            navigateToHome(email, ProviderType.valueOf(provider))
        }
    }

    private fun setup(param: String) {
        title = param

        validateInput()
    }

    // Valida input y habilita components
    private fun validateInput() {
        binding.editTextEmail.addTextChangedListener {
            if (it?.length!! >= 4) {
                binding.edtPsw.visibility = VISIBLE
                binding.editTextPassword.isEnabled = true
            }
        }
        binding.editTextPassword.addTextChangedListener {
            if (it?.length!! >= 4) {
                binding.btnLogin.isEnabled = true
                binding.btnSignup.isEnabled = true
            }
        }

        binding.btnSignup.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword( //registro en Firebase
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    navigateToHome( it.result?.user?.email ?: "", ProviderType.BASIC)
                } else {
                    showAlert()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword( //login con Firebase
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    navigateToHome( it.result?.user?.email ?: "", ProviderType.BASIC)
                } else {
                    showAlert()
                }
            }
        }

        binding.btnGoogle.setOnClickListener {
            // configuracion
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGNIN)
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al autenticar el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun navigateToHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGNIN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credencial = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credencial).addOnCompleteListener {
                        if (it.isSuccessful) {
                            navigateToHome( account.email ?: "", ProviderType.GOOGLE)
                        } else {
                            showAlert()
                        }
                    }
                }
            }catch (e: ApiException) {
                showAlert()
            }


        }
    }
}