package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.repository.UserRepositoryImp
import com.picpay.desafio.android.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserRepositoryModule {

    @Binds
    @Singleton
    fun bindUserRepository(repository: UserRepositoryImp): UserRepository
}