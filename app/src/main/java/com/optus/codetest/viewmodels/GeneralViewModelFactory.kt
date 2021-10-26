package com.optus.codetest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.optus.codetest.network.ApiRepository

class GeneralViewModelFactory(private val repository: ApiRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            return GalleryViewModel(repository) as T
        }

        throw ClassNotFoundException("Invalid ViewModel in MyViewModelFactory!")
    }
}