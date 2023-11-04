package com.shobeir.toopia.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.ui.screen.components.WebPageScreen
import com.shobeir.toopia.ui.screen.forecast.ForecastScreen
import com.shobeir.toopia.ui.screen.home.HomeScreen
import com.shobeir.toopia.ui.screen.login.LoginScreen
import com.shobeir.toopia.ui.screen.login.RegisterScreen

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

        composable(route=Screen.Forecast.route){
            ForecastScreen(sharedViewModel)
        }

        composable(route= Screen.Login.route){
            LoginScreen(sharedViewModel = sharedViewModel)
        }

        composable(route=Screen.Code.route){
            RegisterScreen(sharedViewModel = sharedViewModel)
        }

        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(url = url)
            }
        }

    }
}