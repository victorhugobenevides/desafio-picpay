package com.picpay.desafio.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.picpay.desafio.android.ui.components.MainScreenState


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.MAIN.route) {
        composable(AppRoutes.MAIN.route) {
            MainScreenState(navController = navController)
        }
    }
}
