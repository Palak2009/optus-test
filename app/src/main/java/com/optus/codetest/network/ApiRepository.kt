package com.optus.codetest.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.optus.codetest.database.AppDatabase
import com.optus.codetest.models.Photo
import com.optus.codetest.utils.DLog

class ApiRepository(private val database: AppDatabase) {

    companion object {
        private lateinit var repository: ApiRepository
        fun getInstance(database: AppDatabase): ApiRepository {
            if (!::repository.isInitialized) {
                repository = ApiRepository(database)
            }
            return repository
        }
    }

    val usersFromDb = database.getUsersDao().users.map { it }

    private val _photosWithUserId = MutableLiveData<List<Photo>>()
    val photosWithUserId get() = _photosWithUserId

    suspend fun getUsersFromNetwork() {
        DLog.i("getUsersFromNetwork")
        val users = Factory.getNetworkService().getUsers()
        database.getUsersDao().insertUsers(users)
    }

    suspend fun getPhotosFromNetwork() {
        DLog.i("getPhotosFromNetwork")
        val photos = Factory.getNetworkService().getPhotos()
        database.getPhotosDao().insertPhotos(photos)
    }

    suspend fun getAlbumWithUserId(userId: Int) {
        DLog.i("getAlbumWithUserId")
        _photosWithUserId.postValue(database.getPhotosDao().photosWithUserId(userId))
    }

}