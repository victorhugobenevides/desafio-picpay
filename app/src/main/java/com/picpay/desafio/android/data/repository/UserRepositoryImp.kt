package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val picPayService: PicPayService
) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
            return Result.success(picPayService.getUsers())
    }
}