package com.picpay.desafio.android.domain.usecase.di

import com.picpay.desafio.android.domain.usecase.GetUsersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserUseCaseModule {

    @Binds
    @Singleton
    fun bindUserUseCase(repository: GetUsersImpl): GetUsers
}