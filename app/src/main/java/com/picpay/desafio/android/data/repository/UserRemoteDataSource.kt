package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRemoteDataSource(private val picPayService: PicPayService) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return try {
            return Result.success(picPayService.getUsers())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}