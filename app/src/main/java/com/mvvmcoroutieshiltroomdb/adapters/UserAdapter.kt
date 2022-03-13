package com.mvvmcoroutieshiltroomdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvmcoroutieshiltroomdb.databinding.UserItemBinding
import com.mvvmcoroutieshiltroomdb.interfaces.OnUser
import com.mvvmcoroutieshiltroomdb.models.User

class UserAdapter(private val onUser: OnUser) :
    ListAdapter<User, UserAdapter.UserViewHolder>(userDiffUtils) {

    inner class UserViewHolder(private val itemViewBind: UserItemBinding) :
        RecyclerView.ViewHolder(itemViewBind.root) {

        fun loadView(user: User) {

            with(itemViewBind) {

                tvName.text = user.name

                tvAge.text = user.age.toString()

                ivEditUser.setOnClickListener { onUser.onEdit(user) }

                ivDeleteUser.setOnClickListener { onUser.onDelete(user) }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.loadView(currentList[position])
    }

}

private val userDiffUtils = object : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}