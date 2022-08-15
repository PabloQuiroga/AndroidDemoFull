package com.siar.demofull.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.siar.demofull.R
import com.siar.demofull.databinding.FragmentStepOneBinding
import com.siar.demofull.ui.base.BaseFragment

class StepOneFragment : BaseFragment() {
    private lateinit var binding: FragmentStepOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* maneja el back del sistema */
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.e("BACKBUTTON", "Back button clicks")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStepOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.registerToolbar){
            showAppbar(this,getString(R.string.register_toolbar), true)
            this.setNavigationOnClickListener {
                Log.e("Navegacion toolbar...", "va atras")
            }
        }
    }
}