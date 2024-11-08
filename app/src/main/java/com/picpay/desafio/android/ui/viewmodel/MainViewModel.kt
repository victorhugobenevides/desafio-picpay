package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.usecase.GetUsers
import kotlinx.coroutines.launch

class MainViewModel(private val getUsers: GetUsers) : ViewModel() {
    private val _users = MutableLiveData<List<User>?>()
    val users: MutableLiveData<List<User>?> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = getUsers.execute()
            _isLoading.value = false
            when {
                result.isSuccess -> _users.value = result.getOrNull()
                result.isFailure -> _error.value = "Erro ao carregar usuários" 
            }
        }
    }
}