package com.mvvmcoroutieshiltroomdb.view_models

import androidx.lifecycle.ViewModel
import com.mvvmcoroutieshiltroomdb.models.User
import com.mvvmcoroutieshiltroomdb.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    suspend fun insert(user: User) = userRepository.insert(user)

    suspend fun update(user: User) = userRepository.update(user)

    suspend fun delete(user: User) = userRepository.delete(user)

    fun getUsers() = userRepository.getUsers()

}