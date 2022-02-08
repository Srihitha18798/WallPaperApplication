package com.example.wallpaperapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallpaperapplication.data.model.WallpaperImages

@Dao
interface wallpaperDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallpapers(users: List<WallpaperImages>)

    @Query("SELECT * FROM WallpaperImages")
    fun getWallpapers(): LiveData<List<WallpaperImages>>
}