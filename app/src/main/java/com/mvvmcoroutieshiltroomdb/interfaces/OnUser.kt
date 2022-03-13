package com.mvvmcoroutieshiltroomdb.interfaces

import com.mvvmcoroutieshiltroomdb.models.User

interface OnUser {

    fun onEdit(user: User)

    fun onDelete(user: User)

}