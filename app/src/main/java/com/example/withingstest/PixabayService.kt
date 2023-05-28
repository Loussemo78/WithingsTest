package com.example.withingstest

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PixabayService {

    companion object {
        const val API_KEY = "36785429-1ca911719f61f6ba9c51085a7"
    }

    @GET("/api/")
    suspend fun searchPictures(
        @Query("key")apiKey: String = API_KEY,
        @Query("q") query: String,
    ): Response<SearchResponse>
}

@Parcelize
data class SearchResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("hits")
    val hits: List<ImageResponse>
):Parcelable

@Parcelize
data class ImageResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("webformatURL")
    val imageUrl: String
):Parcelable