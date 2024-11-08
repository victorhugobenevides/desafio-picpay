package com.picpay.desafio.android.ui.navigation

import MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.MAIN.route) {
        composable(AppRoutes.MAIN.route) {
            MainScreen(navController = navController)
        }
    }
}
