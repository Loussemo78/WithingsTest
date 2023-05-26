package com.example.withingstest

class PictureRepository(private val service: PixabayService) {

    suspend fun searchImages(query: String): List<Picture> {
        val response = service.searchPictures(query = query)
        if (response.isSuccessful) {
            val imageResponses = response.body()?.hits ?: emptyList()
            return imageResponses.map { Picture(it.id, it.imageUrl) }
        }
        throw Exception("Failed to search images")
    }

}