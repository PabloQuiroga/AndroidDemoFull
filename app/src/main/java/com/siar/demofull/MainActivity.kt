package com.siar.demofull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Analitycs Events
        val analitycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message", "Integracion de Firebase completa")
        analitycs.logEvent("InitScreen", bundle)
    }
}