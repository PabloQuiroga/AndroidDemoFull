package com.siar.demofull.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siar.demofull.databinding.FragmentStepOneBinding

class StepOneFragment : Fragment() {
    private lateinit var binding: FragmentStepOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showAppBar()
    }

    private fun showAppBar() {
        binding.registerToolbar.title = "Nuevo Registro"
    }
}