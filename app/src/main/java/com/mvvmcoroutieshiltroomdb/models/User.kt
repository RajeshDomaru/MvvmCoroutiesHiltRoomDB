package com.mvvmcoroutieshiltroomdb.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tbl")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,
    val name: String,
    val age: Int
)
