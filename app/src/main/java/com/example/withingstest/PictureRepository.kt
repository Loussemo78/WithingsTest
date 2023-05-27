package com.example.withingstest

import android.net.Uri

class PictureRepository(private val service: PixabayService) {

    suspend fun searchPictures(query: String): List<ImageResponse> {
        val encodedQuery = Uri.encode(query)
        val response = service.searchPictures(query = encodedQuery)
        if (response.isSuccessful) {
            val imageResponses = response.body()?.hits ?: emptyList()
            return imageResponses.map { ImageResponse(it.id, it.imageUrl) }
        }
        throw Exception("Failed to search images")
    }

}