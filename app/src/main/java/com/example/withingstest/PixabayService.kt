package com.example.withingstest

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
    @GET("/api/")
    suspend fun searchPictures(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Response<SearchResponse>
}

data class SearchResponse(
    val hits: List<ImageResponse>
)

data class ImageResponse(
    val id: Int,
    @SerializedName("webformatURL")
    val imageUrl: String
)