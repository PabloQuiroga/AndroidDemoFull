package com.siar.demofull

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.siar.demofull.databinding.ActivityHomeBinding

enum class ProviderType {
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        //guardado de datos de inicio
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"

        binding.emailTextView.text = email
        binding.providerTextView.text = provider

        binding.logoutBtn.setOnClickListener {
            //borrado de datos de inicio
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        binding.btnCrash.setOnClickListener {
            FirebaseCrashlytics.getInstance().setUserId(email) //registra datos del usuario en el crash
            FirebaseCrashlytics.getInstance().setCustomKey("provider", provider) //envio de parametro personalizado

            //enviar log de contexto
            FirebaseCrashlytics.getInstance().log("Se ha pulsado el boton de forzar error")

            throw RuntimeException("Test Crash by Pablo") // Forzado de crash
        }

        binding.setButton.setOnClickListener {
            db.collection("users").document(email).set(
                hashMapOf(
                    "provider" to provider,
                    "address" to binding.editTextAddress.text.toString(),
                    "phone" to binding.editTextPhone.text.toString()
                )
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showDialog("Exito", "Los datos se han guardado correctamente")
                    } else {
                        showDialog("Error", "Se ha producido un error al guardar los datos")
                    }
                }
        }
        binding.getButton.setOnClickListener {
            db.collection("users").document(email).get().addOnSuccessListener {
                if (it.exists()){
                    binding.editTextAddress.setText(it.get("address") as String?)
                    binding.editTextPhone.setText(it.get("phone") as String?)
                }else{
                    showDialog("Error", "Se ha producido un error al cargar los datos")
                }

            }
        }
        binding.deleteButton.setOnClickListener {
            db.collection("users").document(email).delete().addOnCompleteListener {
                if (it.isSuccessful) {
                    showDialog("Exito", "Los datos se han borrado correctamente")
                } else {
                    showDialog("Error", "Se ha producido un error al borrar los datos")
                }
            }
        }
    }

    private fun showDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}