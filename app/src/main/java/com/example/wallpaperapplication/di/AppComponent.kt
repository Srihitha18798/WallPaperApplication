package com.example.wallpaperapplication.di

import android.content.Context
import com.example.wallpaperapplication.MyApplication
import com.example.wallpaperapplication.presentation.detail.DetailComponent
import com.example.wallpaperapplication.presentation.fragment.MainFragmentComponent
import com.example.wallpaperapplication.presentation.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ActivitiesModule::class,
            AppSubcomponents::class,
            NetworksModule::class,
            RepositoryModule::class,
            StorageModule::class
        ]
)
interface AppComponent {

    fun inject(myApplication: MyApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun mainFragmentComponent(): MainFragmentComponent.Factory
    fun detailComponent(): DetailComponent.Factory

}