package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository

class GetUsersImpl(private val userRepository: UserRepository) : GetUsers {
    override suspend fun execute(): Result<List<User>> {
        return userRepository.getUsers()
    }
}