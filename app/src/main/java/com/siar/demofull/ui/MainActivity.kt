package com.siar.demofull.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.siar.demofull.BuildConfig
import com.siar.demofull.R

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, BuildConfig.BASE_URL)
    }
}