package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.usecase.di.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsers: GetUsers
) : ViewModel() {

    // LiveData para o estado da UI
    private val _uiState = MutableLiveData<UiState>(UiState.Loading)
    val uiState: LiveData<UiState> = _uiState


    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = getUsers.execute()
                when {
                    result.isSuccess -> {
                        _uiState.value = UiState.Success(result.getOrNull() ?: emptyList())
                    }
                    result.isFailure -> {
                        _uiState.value = UiState.Error(result.exceptionOrNull()?.message ?: "Error")
                    }
                    else -> {
                        _uiState.value = UiState.Error(result.exceptionOrNull()?.message ?: "Error")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error((e.toString()))
            }
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val users: List<User>) : UiState()
        data class Error(val message: String) : UiState()
    }
}
