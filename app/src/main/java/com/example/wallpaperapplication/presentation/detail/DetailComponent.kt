package com.example.wallpaperapplication.presentation.detail

import dagger.Subcomponent

@Subcomponent
interface DetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }
    fun inject(detailActivity: DetailActivity)
}