package com.siar.demofull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.core.widget.addTextChangedListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.siar.demofull.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Analitycs Events
        val analitycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message", "Integracion de Firebase completa")
        analitycs.logEvent("InitScreen", bundle)

        setup()
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
            if (it?.length!! >= 4) binding.btnLogin.isEnabled = true
        }
    }
}