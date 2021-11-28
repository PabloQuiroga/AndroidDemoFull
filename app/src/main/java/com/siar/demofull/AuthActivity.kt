package com.siar.demofull

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.siar.demofull.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Analitycs Events
        val analitycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message", "Integracion de Firebase completa")
        analitycs.logEvent("InitScreen", bundle)

        setup()
        session()
    }

    override fun onStart() {
        super.onStart()

        binding.authLayout.visibility = VISIBLE
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

    private fun setup() {
        title = "Autenticacion Firebase"

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
            putExtra("provider", provider)
        }
        startActivity(homeIntent)
    }
}