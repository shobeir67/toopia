package com.shobeir.toopia.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Forecast: Screen("forecast_screen")
    object Profile: Screen(route = "profile_screen")
    object Login: Screen(route = "login_screen")
    object Code: Screen(route = "code_screen")

}