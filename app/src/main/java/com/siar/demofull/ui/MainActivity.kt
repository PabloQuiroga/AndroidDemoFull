package com.siar.demofull.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.siar.demofull.BuildConfig
import com.siar.demofull.R
import com.siar.demofull.databinding.ActivityMainBinding
import com.siar.demofull.ui.register.RegisterActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = this::class.java.simpleName

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e(TAG, BuildConfig.BASE_URL)
        initComponents()
    }

    private fun initComponents() {
        binding.btnLogin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val actionIntent = when(v?.id){
            //TODO change this when other activities are implemented
            R.id.btnLogin -> Intent(this, RegisterActivity::class.java)
            R.id.btnSignup -> Intent(this, RegisterActivity::class.java)
            else -> {}
        }
        if(actionIntent is Intent) startActivity(actionIntent) else Log.e(TAG, "No intent provided")
    }


}