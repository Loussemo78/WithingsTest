package com.example.withingstest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PictureViewModel(private val repository: PictureRepository):ViewModel() {
    private val _picture = MutableLiveData<List<Picture>>()
    val pictures: LiveData<List<Picture>> = _picture

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun searchPictures(query: String) {
        viewModelScope.launch {
            try {
                val result = repository.searchImages(query)
                _picture.value = result
            } catch (e: Exception) {
                _error.value = "Failed to search images: ${e.message}"
            }
        }
    }
}