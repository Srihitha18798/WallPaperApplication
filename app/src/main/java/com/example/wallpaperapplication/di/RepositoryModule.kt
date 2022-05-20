package com.example.wallpaperapplication.di

import android.content.Context
import com.example.wallpaperapplication.data.database.wallpaperDao
import com.example.wallpaperapplication.data.repository.MainRepository
import com.example.wallpaperapplication.domain.network.apiService
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    fun provideWallpaperRepository(
            apiService: apiService, wallpaperDao: wallpaperDao,
            context: Context
    ): MainRepository {
        return MainRepository(apiService, wallpaperDao, context)
    }
}