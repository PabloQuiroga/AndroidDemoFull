package com.siar.demofull.ui.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.siar.demofull.R
import com.siar.demofull.data.Images

class ImagesAdapter(private val mList: List<Images>): RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_image, parent, false)

        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val Items = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(Items.image)
    }

    override fun getItemCount() = mList.size


    class ImageViewHolder(v: View): RecyclerView.ViewHolder(v){
        val imageView: ImageView = v.findViewById(R.id.img)
    }


}