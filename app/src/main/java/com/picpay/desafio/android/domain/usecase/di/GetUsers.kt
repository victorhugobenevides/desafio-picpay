package com.picpay.desafio.android.domain.usecase.di

import com.picpay.desafio.android.domain.entity.User

interface GetUsers {
    suspend fun execute(): Result<List<User>>
}