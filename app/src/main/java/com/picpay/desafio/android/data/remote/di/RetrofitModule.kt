package com.picpay.desafio.android.data.remote.di


import com.picpay.desafio.android.data.remote.PicPayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl(): String =
        "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)
}
