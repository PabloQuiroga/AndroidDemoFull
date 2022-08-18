package com.siar.demofull.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.siar.demofull.R
import com.siar.demofull.databinding.FragmentStepOneBinding
import com.siar.demofull.ui.base.BaseFragment


class SignUpFragment : BaseFragment() {
    private var TAG = this::class.java.simpleName

    private lateinit var binding: FragmentStepOneBinding
    val args: SignUpFragmentArgs by navArgs()

    private var termsAccepted: Boolean = false

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

        checkData(termsAccepted)

        /*binding.checkBox.setOnCheckedChangeListener { _, b ->
            binding.btnRegister.isEnabled = b
        }*/

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_termsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        termsAccepted = args.termsAccepted
        //binding.checkBox.isChecked = termsAccepted
    }

    private fun checkData(termsAccepted: Boolean){
        //val chkTerms = binding.checkBox.isChecked

        //binding.checkBox.isChecked = termsAccepted
        //binding.btnRegister.isEnabled = chkTerms
    }
}