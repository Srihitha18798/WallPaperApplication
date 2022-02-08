package com.example.wallpaperapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wallpaperapplication.data.model.WallpaperImages
import com.example.wallpaperapplication.domain.network.apiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: apiService
) {

    private val wallpaperImages = MutableLiveData<List<WallpaperImages>>()
    private val key="22491268-fcbf6c76232c86c5c48879e1e"
    private val image_type="photo"
    private val pretty=true





    suspend fun fetchWallpaperImages(category:String,page:Int) {
        val response = apiService.getImages(key, image_type, pretty, category,page)
        wallpaperImages.postValue(response.hits)

    }


    suspend fun getWallpaperImages(category: String,page: Int): LiveData<List<WallpaperImages>> {
            Log.e("network", "connected")
            fetchWallpaperImages(category,page)
            return wallpaperImages

        }



}