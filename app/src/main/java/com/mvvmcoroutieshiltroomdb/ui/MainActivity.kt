package com.mvvmcoroutieshiltroomdb.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvmcoroutieshiltroomdb.R
import com.mvvmcoroutieshiltroomdb.adapters.UserAdapter
import com.mvvmcoroutieshiltroomdb.databinding.ActivityMainBinding
import com.mvvmcoroutieshiltroomdb.interfaces.OnUser
import com.mvvmcoroutieshiltroomdb.models.User
import com.mvvmcoroutieshiltroomdb.view_models.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val userViewModel by viewModels<UserViewModel>()

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bind.root)

        loadActivity()

    }

    private fun loadActivity() {

        setOnClickListeners()

        loadUsers()

    }

    private fun setOnClickListeners() {

        with(bind) {

            btnAddOrUpdate.setOnClickListener {

                if (isValidInputs()) {

                    userViewModel.viewModelScope.launch {

                        if (btnAddOrUpdate.text.toString().trim() == getString(R.string.add)) {

                            val user = User(
                                name = etName.text.toString().trim(),
                                age = etAge.text.toString().trim().toInt()
                            )

                            userViewModel.insert(user)

                        } else {

                            val user = User(
                                userId = tvUserId.text.toString().trim().toInt(),
                                name = etName.text.toString().trim(),
                                age = etAge.text.toString().trim().toInt()
                            )

                            userViewModel.update(user)

                        }

                        reset()

                    }

                }

            }

            btnCancel.setOnClickListener { reset() }

        }

    }

    private fun isValidInputs(): Boolean {

        var isValid = true

        with(bind) {

            if (etName.text.toString().trim().isEmpty()) {
                etName.error = getString(R.string.required)
                isValid = false
            }

            if (etAge.text.toString().trim().isEmpty()) {
                etAge.error = getString(R.string.required)
                isValid = false
            }

        }

        return isValid

    }

    private fun loadUsers() {

        with(bind) {

            rvUser.layoutManager =
                LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

            userAdapter = UserAdapter(object : OnUser {

                override fun onEdit(user: User) {
                    tvUserId.text = user.userId.toString()
                    etName.setText(user.name)
                    etAge.setText(user.age.toString())
                    btnAddOrUpdate.text = getString(R.string.update)
                    btnCancel.visibility = View.VISIBLE
                }

                override fun onDelete(user: User) {
                    with(userViewModel) { viewModelScope.launch { delete(user) } }
                }

            })

            rvUser.adapter = userAdapter

        }

        userViewModel.getUsers().observe(this) {

            userAdapter.submitList(it.toMutableList())

        }

    }

    private fun reset() {

        with(bind) {

            tvUserId.text = null

            etName.text = null

            etAge.text = null

            btnCancel.visibility = View.GONE

            btnAddOrUpdate.text = getString(R.string.add)

        }

    }

}