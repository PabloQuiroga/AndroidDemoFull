package com.siar.demofull.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siar.demofull.databinding.FragmentRecoverBinding

class RecoverFragment : Fragment() {
    private lateinit var binding: FragmentRecoverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecoverBinding.inflate(inflater, container, false)
        return binding.root
    }
}