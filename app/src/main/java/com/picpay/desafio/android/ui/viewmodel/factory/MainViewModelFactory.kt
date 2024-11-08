package com.picpay.desafio.android.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.domain.usecase.GetUsers
import com.picpay.desafio.android.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val getUsers: GetUsers) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getUsers) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido")
    }
}