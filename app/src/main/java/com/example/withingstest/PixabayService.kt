package com.example.withingstest

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PixabayService {
    @GET("/api/")
    suspend fun searchPictures(
        @Header("Authorization") apiKey: String = "18021445-326cf5bcd3658777a9d22df6f",
        @Query("q") query: String,
        @Query("image_type") imageType: String = "photo"
    ): Response<SearchResponse>
}

data class SearchResponse(
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    val hits: List<ImageResponse>
)

data class ImageResponse(
    val id: Int,
    @SerializedName("webformatURL")
    val imageUrl: String
)