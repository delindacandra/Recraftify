package com.dicoding.recraftify.data.preferences

data class UserModel(

    val email: String,
    val token: String,
    val isLogin: Boolean = false
)
