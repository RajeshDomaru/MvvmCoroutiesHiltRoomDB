package com.mvvmcoroutieshiltroomdb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mvvmcoroutieshiltroomdb.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User): Int

    @Delete
    suspend fun delete(user: User): Int

    @Query("SELECT * FROM user_tbl")
    fun getUsers(): LiveData<List<User>>

}