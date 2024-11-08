package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usecase.di.GetUsers
import javax.inject.Inject

class GetUsersImpl @Inject constructor(private val userRepository: UserRepository) : GetUsers {
    override suspend fun execute(): Result<List<User>> {
        return userRepository.getUsers()
    }
}