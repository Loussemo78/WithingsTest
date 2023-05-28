package com.example.withingstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.withingstest.databinding.ActivitySelectedPicturesBinding

class SelectedPicturesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedPicturesBinding
    private lateinit var adapter: SelectedPicturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val selectedImages = intent.getSerializableExtra("SELECTED_IMAGES") as ArrayList<ImageResponse>

        adapter = SelectedPicturesAdapter(selectedImages)
        binding.imageRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.imageRecyclerView.adapter = adapter

    }
}