package com.mvvmcoroutieshiltroomdb.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvmcoroutieshiltroomdb.databinding.UserItemBinding
import com.mvvmcoroutieshiltroomdb.interfaces.OnUser
import com.mvvmcoroutieshiltroomdb.models.User

class UserAdapter(private val onUser: OnUser) :
    PagingDataAdapter<User, UserAdapter.UserViewHolder>(UserDiffUtils()) {

    inner class UserViewHolder(private val itemViewBind: UserItemBinding) :
        RecyclerView.ViewHolder(itemViewBind.root) {

        fun loadView(user: User) {

            with(itemViewBind) {

                Log.i("User Details => ", "${user.name}  :  ${user.age} : $itemCount")

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
        val item = getItem(position) ?: return
        holder.loadView(item)
    }

    class UserDiffUtils : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

}