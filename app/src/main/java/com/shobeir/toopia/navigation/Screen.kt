package com.shobeir.toopia.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Forecast: Screen("forecast_screen")
    object Profile: Screen(route = "profile_screen")
    object Setting: Screen(route = "setting_screen")
    object ControlAudio: Screen(route = "control_screen")

}