package com.siar.demofull.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siar.demofull.R
import com.siar.demofull.data.Images
import com.siar.demofull.databinding.FragmentRecoverBinding
import com.siar.demofull.ui.login.adapter.ImagesAdapter

class RecoverFragment : Fragment() {
    private lateinit var binding: FragmentRecoverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = ArrayList<Images>()
        for (i in 1..20) {
            data.add(Images(
                if(i % 2 == 0) R.drawable.captura else R.drawable.captura2
            ))
        }
        val adapter = ImagesAdapter(data);
        binding.recyclerImages.adapter = adapter
    }
}


