package com.example.withingstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.withingstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PictureViewModel
    private lateinit var viewModelFactory: PictureViewModelFactory
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PictureAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PictureAdapter{
            Toast.makeText(this, "Image ID: ${it.id}", Toast.LENGTH_SHORT).show()
        }
        binding.imageRecyclerView.adapter = adapter
        val service = RetrofitClient.createPixabayService()
        val repository = PictureRepository(service)
         viewModelFactory = PictureViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PictureViewModel::class.java)
        viewModel.pictures.observe(this, Observer { images ->
            adapter.setPictures(images)
            adapter.notifyDataSetChanged()
            // Affichage des résultats dans la console
            for (image in images) {
                Log.d("MainActivity", "Image ID: ${image.id}, Image URL: ${image.pictureUrl}")
            }
        })

        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            val imageType = "photo"

            viewModel.searchPictures(query , imageType)
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
