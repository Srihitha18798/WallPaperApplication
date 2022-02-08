package com.example.wallpaperapplication.di

import com.example.wallpaperapplication.presentation.detail.DetailComponent
import com.example.wallpaperapplication.presentation.fragment.MainFragmentComponent
import com.example.wallpaperapplication.presentation.main.MainComponent
import dagger.Module

@Module(
    subcomponents = arrayOf(MainComponent::class,MainFragmentComponent::class,DetailComponent::class)
)
class AppSubcomponents {
}