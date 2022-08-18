package com.siar.demofull.ui.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siar.demofull.databinding.ListItemNameBinding

class ApodoAdapte(private val mlist: ArrayList<String>): RecyclerView.Adapter<ApodoAdapte.ApodoViewHolder>()  {
    private lateinit var binding: ListItemNameBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodoViewHolder {
        binding = ListItemNameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ApodoAdapte.ApodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApodoViewHolder, position: Int) {
        val model = mlist[position]
        binding.lblApodo.text = model
    }

    override fun getItemCount() = mlist.size

    data class ApodoViewHolder(val binding: ListItemNameBinding): RecyclerView.ViewHolder(binding.root)
}