package com.example.withingstest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.withingstest.databinding.ItemSelectedPictureBinding

class SelectedPicturesAdapter(private val selectedImages: List<ImageResponse>) : RecyclerView.Adapter<SelectedPicturesAdapter.SelectedPictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedPictureViewHolder {
        val binding = ItemSelectedPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectedPictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectedPictureViewHolder, position: Int) {
        val picture = selectedImages[position]
        holder.bind(picture)
    }

    override fun getItemCount(): Int {
        return selectedImages.size
    }

    inner class SelectedPictureViewHolder(private val binding: ItemSelectedPictureBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: ImageResponse) {
            Glide.with(binding.root)
                .load(picture.imageUrl)
                .into(binding.selectedImage)
        }
    }
}
