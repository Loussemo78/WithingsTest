package com.example.withingstest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.withingstest.databinding.ItemPictureBinding

class PictureAdapter(private val onItemClickListener: (ImageResponse) -> Unit) : RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    private val pictures = mutableListOf<ImageResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = pictures[position]
        holder.bind(picture)

        val isSelected = pictures.contains(picture)
        holder.itemView.isActivated = isSelected

        holder.itemView.setOnClickListener {
            toggleImageSelection(picture)
            onItemClickListener.invoke(picture)
        }
    }


    override fun getItemCount(): Int {
        return pictures.size
    }

    fun setPictures(newPicture: List<ImageResponse>) {
        val diffResult = DiffUtil.calculateDiff(PictureDiffCallback(pictures, newPicture))
        pictures.clear()
        pictures.addAll(newPicture)
        diffResult.dispatchUpdatesTo(this)
        //notifyDataSetChanged()
    }

    //handle image selected
    private fun toggleImageSelection(image: ImageResponse) {
        if (pictures.contains(image)) {
            pictures.remove(image)
        } else {
            pictures.add(image)
        }
        notifyDataSetChanged()
    }

    fun getSelectedImages(): List<ImageResponse> {
        return pictures
    }



    inner class PictureViewHolder(private val binding: ItemPictureBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(picture: ImageResponse) {
            // Charger l'image depuis l'URL et l'afficher dans l'ImageView à l'aide de View Binding
            Glide.with(binding.root)
                .load(picture.imageUrl)
                .into(binding.image)

            binding.imageId.text = picture.id.toString()
        }
    }

    private class PictureDiffCallback(private val oldList: List<ImageResponse>, private val newList: List<ImageResponse>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldPicture = oldList[oldItemPosition]
            val newPicture = newList[newItemPosition]
            return oldPicture.id == newPicture.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldPicture = oldList[oldItemPosition]
            val newPicture = newList[newItemPosition]
            return oldPicture == newPicture
        }
    }
}