package com.shobeir.toopia.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Forecast: Screen("forecast_screen")
    object Profile: Screen(route = "profile_screen")
    object Login: Screen(route = "login_screen")
    object Code: Screen(route = "code_screen")
    object WebView : Screen("webView_screen")
    object Shop : Screen("shop_screen")
    object Karbordi : Screen("karbordi_screen")
    object NobateMan : Screen("nobat_screen")
    object Toopia : Screen("toopia_screen")
    object DetailsNews : Screen("detailsNews_screen")
    object Salon : Screen("salon_screen")
    object Chaman : Screen("chaman_screen")
    object Panel : Screen("panel_screen")
    object ConfirmPurchase : Screen("confirm_purchase_screen")
    object AddStore : Screen("addStore_Screen")
    object Agahiha : Screen("agahi_Screen")
    object City : Screen("city_Screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}