package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.di.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val picPayService: PicPayService
) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val users = picPayService.getUsers()
            Result.success(users)
        } catch (e: HttpException) {
            Result.failure(IOException("Erro HTTP: ${e.message}", e))
        } catch (e: IOException) {
            Result.failure(IOException("Erro de rede: ${e.message}", e))
        } catch (e: Exception) {
            Result.failure(Exception("Erro desconhecido: ${e.message}", e))
        }

    }
}