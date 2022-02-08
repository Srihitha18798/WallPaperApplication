package com.example.wallpaperapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Wallpaper(
        val total: Int,
        val totalHits: Int,
        val hits: List<WallpaperImages>
)

@Entity
data class WallpaperImages(

        @PrimaryKey
        val id: Int,
        val previewURL: String,
        val previewWidth: Int,
        val previewHeight: Int,
        val webformatURL: String,
        val webformatWidth: Int,
        val webformatHeight: Int,
        val largeImageURL: String,
        val imageWidth: Int,
        val imageHeight: Int
)