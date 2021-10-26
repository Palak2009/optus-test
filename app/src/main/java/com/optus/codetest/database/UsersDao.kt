package com.optus.codetest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.optus.codetest.models.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @get:Transaction
    @get:Query("SELECT * FROM User")
    val users: LiveData<List<User>>

}