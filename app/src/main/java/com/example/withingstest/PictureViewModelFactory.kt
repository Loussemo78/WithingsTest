package com.example.withingstest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PictureViewModelFactory(private val repository: PictureRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PictureViewModel::class.java)) {
            return PictureViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}