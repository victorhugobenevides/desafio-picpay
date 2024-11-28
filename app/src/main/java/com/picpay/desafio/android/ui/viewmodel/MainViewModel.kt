package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.usecase.di.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val getUsers: GetUsers
) : ViewModel() {

    // LiveData para o estado da UI
    private val _uiState = MutableLiveData<UiState>(UiState.Idle)
    open var uiState: MutableLiveData<UiState> = _uiState


    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            withContext(Dispatchers.IO) {
                val result = getUsers.execute()
                when {
                    result.isSuccess -> {
                        _uiState.value = UiState.Success(result.getOrNull() ?: emptyList())
                    }
                    result.isFailure -> {
                        _uiState.value = UiState.Error(result.exceptionOrNull()?.message ?: "Error")
                    }
                }
            }
        }
    }

    sealed class UiState {
        data object Idle : UiState()
        data object Loading : UiState()
        data class Success(val users: List<User>) : UiState()
        data class Error(val message: String) : UiState()
    }
}
