package com.example.wallpaperapplication

import android.app.Application
import com.example.wallpaperapplication.di.AppComponent
import com.example.wallpaperapplication.di.DaggerAppComponent

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {

    }

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
}