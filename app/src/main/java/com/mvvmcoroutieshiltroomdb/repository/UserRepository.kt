package com.mvvmcoroutieshiltroomdb.repository

import com.mvvmcoroutieshiltroomdb.db.UserDao
import com.mvvmcoroutieshiltroomdb.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insert(user: User) = userDao.insert(user)

    suspend fun update(user: User) = userDao.update(user)

    suspend fun delete(user: User) = userDao.delete(user)

    fun getUsersPaging() = userDao.getUsersPaging()

}