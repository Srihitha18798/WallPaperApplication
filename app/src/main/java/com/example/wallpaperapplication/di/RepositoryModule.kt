package com.example.wallpaperapplication.di

import com.example.wallpaperapplication.data.repository.MainRepository
import com.example.wallpaperapplication.domain.network.apiService
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    fun provideWallpaperRepository(
        apiService: apiService
    ): MainRepository {
        return MainRepository(apiService)
    }


}