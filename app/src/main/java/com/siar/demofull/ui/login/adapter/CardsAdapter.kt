package com.siar.demofull.ui.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siar.demofull.R
import com.siar.demofull.data.Person
import com.siar.demofull.databinding.ListItemPersonBinding

class CardsAdapter(private val mlist: ArrayList<Person>): RecyclerView.Adapter<CardsAdapter.PersonViewHolder>() {
    private lateinit var binding: ListItemPersonBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        binding = ListItemPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val model = mlist[position]
        binding.lblAge.text = model.age.toString()
        binding.lblNames.text = model.names
        binding.lblSurnames.text = model.surnames

        showApodos(model.apodos)
    }

    override fun getItemCount() = mlist.size

    private fun showApodos(apodos: ArrayList<String>){
        if (apodos.isNotEmpty()){
            val lenght = apodos.size
            val views = arrayListOf(binding.lblApodo1, binding.lblApodo2, binding.lblApodo3, binding.lblApodo4, binding.lblApodo5, binding.lblApodo6)
            for ((index, value ) in apodos.withIndex()){
                views[index].visibility = View.VISIBLE
                views[index].text = value
            }
        }
    }

    class PersonViewHolder(binding: ListItemPersonBinding): RecyclerView.ViewHolder(binding.root)
}