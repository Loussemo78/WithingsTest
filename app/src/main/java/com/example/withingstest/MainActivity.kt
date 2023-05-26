package com.example.withingstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        adapter = PictureAdapter()
        binding.imageRecyclerView.adapter

        val repository = PictureRepository()
        val viewModelFactory = PictureViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PictureViewModel::class.java)
        viewModel.pictures.observe(this, Observer { images ->

            adapter.notifyDataSetChanged()
        })


    }
}