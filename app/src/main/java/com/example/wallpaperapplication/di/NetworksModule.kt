package com.example.wallpaperapplication.di

import com.example.wallpaperapplication.domain.network.apiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworksModule {
    @Provides
    @Singleton
    fun getBaseUrl(): String = "https://pixabay.com/"

    @Provides
    @Singleton
    fun getRetrofitBuilder(baseUrl: String): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): apiService = retrofit.create(apiService::class.java)


}
