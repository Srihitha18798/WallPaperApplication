package com.example.wallpaperapplication.di

import android.content.Context
import androidx.room.Room
import com.example.wallpaperapplication.data.database.wallpaperDao
import com.example.wallpaperapplication.data.database.wallpaperDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): wallpaperDb {
        return Room.databaseBuilder(
                context.applicationContext,
                wallpaperDb::class.java,
                "wallpapers_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesUserDao(wallpaperDb: wallpaperDb): wallpaperDao {
        return wallpaperDb.getWallpaperDao()
    }

}