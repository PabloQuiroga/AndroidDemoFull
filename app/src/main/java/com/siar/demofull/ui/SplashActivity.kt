package com.siar.demofull.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.siar.demofull.ui.home.HomeActivity
import com.siar.demofull.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(
            Intent(this,
                if(isLogged()) HomeActivity::class.java else LoginActivity::class.java
            )
        )
    }

    private fun isLogged(): Boolean{
        val sharedPref = getPreferences(MODE_PRIVATE)
        return sharedPref.getBoolean("isLogged", false)
    }
}
