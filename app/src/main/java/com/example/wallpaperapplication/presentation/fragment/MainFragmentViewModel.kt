package com.example.wallpaperapplication.presentation.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapplication.data.model.WallpaperImages
import com.example.wallpaperapplication.data.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    var wallpaperImages = MutableLiveData<List<WallpaperImages>>()

    fun getWallpaperImages(category:String,page:Int) {
        viewModelScope.launch {
            repository.getWallpaperImages(category,page).observeForever {
                wallpaperImages.postValue(it)
            }
        }
    }

}