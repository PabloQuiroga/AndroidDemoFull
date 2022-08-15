package com.siar.demofull.ui.base

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.siar.demofull.R

open class BaseFragment: Fragment() {

    /* backButton en false para deshabilitar icono de regresar */
    open fun showAppbar(v: View, title: String, backButton: Boolean){
        with(v as Toolbar){
            this.title = title
            if(backButton) this.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_arrow)
        }
    }

    open fun hideAppbar(v: View, hidden: Boolean){
        (v as Toolbar).visibility = View.GONE
    }
}