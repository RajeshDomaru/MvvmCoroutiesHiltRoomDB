package com.mvvmcoroutieshiltroomdb.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mvvmcoroutieshiltroomdb.models.User
import com.mvvmcoroutieshiltroomdb.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    suspend fun insert(user: User) = userRepository.insert(user)

    suspend fun update(user: User) = userRepository.update(user)

    suspend fun delete(user: User) = userRepository.delete(user)

    val usersPaging
        get() = Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                initialLoadSize = 5
            )
        ) {
            userRepository.getUsersPaging()
        }.flow.cachedIn(viewModelScope)
}