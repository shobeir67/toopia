package com.shobeir.toopia.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.ui.screen.home.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

    }
}