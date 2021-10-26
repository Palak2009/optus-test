package com.optus.codetest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.optus.codetest.models.Photo

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<Photo>)

    @get:Transaction
    @get:Query("SELECT * FROM Photo")
    val photos: LiveData<List<Photo>>

    @Query("SELECT * FROM Photo WHERE albumId = :userId")
    suspend fun photosWithUserId(userId: Int): List<Photo>

}