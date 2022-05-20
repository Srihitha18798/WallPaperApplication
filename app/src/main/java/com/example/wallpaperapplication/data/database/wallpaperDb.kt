package com.example.wallpaperapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallpaperapplication.data.model.WallpaperImages

@Database(entities = [WallpaperImages::class], version = 1)
abstract class wallpaperDb : RoomDatabase() {

    abstract fun getWallpaperDao(): wallpaperDao
}