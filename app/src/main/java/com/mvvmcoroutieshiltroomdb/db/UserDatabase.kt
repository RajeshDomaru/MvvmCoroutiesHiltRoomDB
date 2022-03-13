package com.mvvmcoroutieshiltroomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvvmcoroutieshiltroomdb.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }

}