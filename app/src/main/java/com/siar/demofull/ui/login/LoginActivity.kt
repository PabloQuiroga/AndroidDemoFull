package com.siar.demofull.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siar.demofull.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}