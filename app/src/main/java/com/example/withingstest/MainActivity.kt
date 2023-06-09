package com.example.withingstest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.withingstest.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PictureViewModel
    private lateinit var viewModelFactory: PictureViewModelFactory
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PictureAdapter
    private val selectedImages = mutableListOf<ImageResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init recyclerview
        binding.imageRecyclerView.layoutManager = LinearLayoutManager(this)

        //init adapter
        adapter = PictureAdapter{
            Toast.makeText(this, "Image ID: ${it.id}", Toast.LENGTH_SHORT).show()
            if (selectedImages.contains(it)) {
                selectedImages.remove(it)
            } else {
                selectedImages.add(it)
            }

        }
        binding.imageRecyclerView.adapter = adapter


        val service = RetrofitClient.createPixabayService()
        val repository = PictureRepository(service)

        //init view model
        viewModelFactory = PictureViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PictureViewModel::class.java]

        viewModel.pictures.observe(this, Observer { images ->
            adapter.setPictures(images)
            adapter.notifyDataSetChanged()
            // Affichage des résultats dans la console
            for (image in images) {
                Log.d("MY-TAG", "Image ID: ${image.id}, Image URL: ${image.imageUrl}")
            }
        })

        // click on search button
        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            viewModel.searchPictures(query)
        }


        // click on selection view button
        binding.viewSelectionButton.setOnClickListener{
            val selectedPictures = adapter.getSelectedImages()
            if (selectedPictures.size >= 2){
                val intent = Intent(this, SelectedPicturesActivity::class.java)
                intent.putExtra("SELECTED_IMAGES", ArrayList(selectedPictures))
                startActivity(intent)
            }else{
                Toast.makeText(this, "Please select at least 2 images", Toast.LENGTH_SHORT).show()
            }
        }
       /* binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val imageType = "photo"
                viewModel.searchPictures(query,imageType)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })*/
    }

    }
