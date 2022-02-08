package com.example.wallpaperapplication.di

import com.example.wallpaperapplication.presentation.main.MainActivity
import dagger.Module

@Module
abstract class ActivitiesModule {
    abstract fun contributeMainActivity(): MainActivity
}