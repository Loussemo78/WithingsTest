package com.example.withingstest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.withingstest.databinding.ItemPictureBinding

class PictureAdapter(private val pictures: List<Picture>) : RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = pictures[position]
        holder.bind(picture)
    }


    override fun getItemCount(): Int {
        return pictures.size
    }

    inner class PictureViewHolder(private val binding: ItemPictureBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(picture: Picture) {
            // Charger l'image depuis l'URL et l'afficher dans l'ImageView à l'aide de View Binding
            Glide.with(binding.root)
                .load(picture.pictureUrl)
                .into(binding.image)

            binding.imageId.text = picture.id.toString()
        }
    }
}