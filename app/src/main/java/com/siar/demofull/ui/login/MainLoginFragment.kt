package com.siar.demofull.ui.login

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.siar.demofull.R
import com.siar.demofull.databinding.FragmentMainLoginBinding
import com.siar.demofull.ui.base.BaseFragment

class MainLoginFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentMainLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMainLogin.setOnClickListener(this)
        binding.btnMainSignup.setOnClickListener(this)
        binding.btnMainRecover.setOnClickListener(this)

        binding.lblLinkTerms.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onClick(v: View?) {
        val action = when (v?.id) {
            R.id.btnMainLogin -> R.id.action_mainLoginFragment_to_loginFragment
            R.id.btnMainSignup -> R.id.action_mainLoginFragment_to_signupFragment
            else -> R.id.action_mainLoginFragment_to_recoverFragment
        }

        findNavController().navigate(action)
    }
}