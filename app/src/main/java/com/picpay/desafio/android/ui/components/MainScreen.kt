package com.picpay.desafio.android.ui.components

import ErrorScreen
import LoadingScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.picpay.desafio.android.ui.theme.colorPrimary
import com.picpay.desafio.android.ui.viewmodel.MainViewModel

@Composable
fun MainScreenState(navController: NavHostController?, viewModel: MainViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.observeAsState()
    MainScreen(navController = navController, uiState = uiState.value)
}

@Composable
fun MainScreen(uiState: MainViewModel.UiState?, navController: NavHostController? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorPrimary)
    ) {
        when (uiState) {
            is MainViewModel.UiState.Loading -> LoadingScreen()
            MainViewModel.UiState.Idle -> LoadingScreen()
            is MainViewModel.UiState.Success -> UserList(users = uiState.users)
            is MainViewModel.UiState.Error -> ErrorScreen(message = uiState.message)
            null -> {}
        }
    }
}
