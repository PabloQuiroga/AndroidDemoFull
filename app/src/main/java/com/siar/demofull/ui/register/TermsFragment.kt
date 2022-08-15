package com.siar.demofull.ui.register

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.siar.demofull.R
import com.siar.demofull.databinding.FragmentTermsBinding

class TermsFragment : Fragment() {
    private lateinit var binding: FragmentTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backNavigate(false)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAcceptTerms.setOnClickListener {
            backNavigate(true)
        }
    }

    private fun backNavigate(acceptedTerms: Boolean){
        val action = TermsFragmentDirections.actionTermsFragmentToStepOneFragment(acceptedTerms)
        findNavController().navigate(action)
    }
}