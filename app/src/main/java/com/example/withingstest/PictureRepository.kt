package com.example.withingstest

class PictureRepository(private val service: PixabayService) {

    suspend fun searchPictures(query: String , imageType:String): List<Picture> {
        val response = service.searchPictures(query = query , imageType = imageType)
        if (response.isSuccessful) {
            val imageResponses = response.body()?.hits ?: emptyList()
            return imageResponses.map { Picture(it.id, it.imageUrl) }
        }
        throw Exception("Failed to search images")
    }

}