package com.example.wallpaperapplication.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wallpaperapplication.data.database.wallpaperDao
import com.example.wallpaperapplication.data.model.WallpaperImages
import com.example.wallpaperapplication.domain.network.apiService
import com.example.wallpaperapplication.presentation.utility.isNetworkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val apiService: apiService,
        private val wallpaperDao: wallpaperDao,
        private val context: Context
) {

    private val wallpaperImages = MutableLiveData<List<WallpaperImages>>()
    private val key = "22491268-fcbf6c76232c86c5c48879e1e"
    private val image_type = "photo"
    private val pretty = true

    init {
        wallpaperImages.observeForever {
            saveWallpaperImages(it)
        }
    }

    fun saveWallpaperImages(wallpaperImages: List<WallpaperImages>) {
        CoroutineScope(Dispatchers.IO).launch {
            wallpaperDao.insertWallpapers(wallpaperImages)
        }
    }

    suspend fun fetchWallpaperImages(category: String, page: Int) {
        val response = apiService.getImages(key, image_type, pretty, category, page)
        wallpaperImages.postValue(response.hits)

    }

    suspend fun getWallpaperImages(category: String, page: Int): LiveData<List<WallpaperImages>> {
        if (context.isNetworkAvailable()) {
            Log.e("network", "connected")
            fetchWallpaperImages(category, page)
            return wallpaperImages
        } else {
            Log.e("network", "not connected")
            Toast.makeText(context, "Internet is not connected", Toast.LENGTH_LONG).show()
            return withContext(Dispatchers.IO) {
                wallpaperDao.getWallpapers()
            }
        }
    }


}