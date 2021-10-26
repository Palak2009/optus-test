package com.optus.codetest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.optus.codetest.network.ApiRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class GalleryViewModel(private val repository: ApiRepository) : ViewModel() {

    val photos = repository.photosWithUserId

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading get() = _isLoading

    private val _errors = MutableLiveData<Exception>()
    val errors get() = _errors

    fun getAlbumWithUserId(userId: Int) {
        if (userId != -1) {
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    repository.getAlbumWithUserId(userId)
                } catch (e: Exception) {
                    _errors.value = e
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

}