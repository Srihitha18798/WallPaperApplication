package com.example.wallpaperapplication.presentation.fragment

import dagger.Subcomponent

@Subcomponent
interface MainFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainFragmentComponent
    }

    fun inject(mainFragment: MainFragment)
}