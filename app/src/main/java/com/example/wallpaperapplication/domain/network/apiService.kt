package com.example.wallpaperapplication.domain.network

import com.example.wallpaperapplication.data.model.Wallpaper
import retrofit2.http.GET
import retrofit2.http.Query

interface apiService {

    @GET("api/")
    suspend fun getImages(@Query("key") key: String, @Query("image_type") image_type: String, @Query("pretty") pretty: Boolean, @Query("category") category: String, @Query("page") page: Int): Wallpaper


}