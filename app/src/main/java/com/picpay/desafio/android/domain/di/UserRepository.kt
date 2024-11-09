package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
}