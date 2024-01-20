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
import com.shobeir.toopia.ui.screen.home.CityScreen
import com.shobeir.toopia.ui.screen.karbordi.toopia.ForecastScreen
import com.shobeir.toopia.ui.screen.home.HomeScreen
import com.shobeir.toopia.ui.screen.karbordi.AppScreen
import com.shobeir.toopia.ui.screen.karbordi.nobateman.ConfirmPurchaseScreen
import com.shobeir.toopia.ui.screen.karbordi.nobateman.NobateDehi
import com.shobeir.toopia.ui.screen.karbordi.nobateman.chaman.ChamanScreen
import com.shobeir.toopia.ui.screen.login.LoginScreen
import com.shobeir.toopia.ui.screen.login.RegisterScreen
import com.shobeir.toopia.ui.screen.news.details.DetailsScreen
import com.shobeir.toopia.ui.screen.profile.ProfileScreen
import com.shobeir.toopia.ui.screen.shop.ShopScreen
import com.shobeir.toopia.ui.screen.karbordi.nobateman.modir.PanelScreen
import com.shobeir.toopia.ui.screen.karbordi.nobateman.salon.SalonScreen
import com.shobeir.toopia.ui.screen.karbordi.toopia.ToopiaScreen
import com.shobeir.toopia.ui.screen.profile.AddStore
import com.shobeir.toopia.ui.screen.profile.ChengCity
import com.shobeir.toopia.ui.screen.profile.StoreScreen

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
            LoginScreen(sharedViewModel = sharedViewModel, navController = navController)
        }

        composable(route=Screen.Code.route){
            RegisterScreen(sharedViewModel = sharedViewModel,navController = navController)
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

        composable(route = Screen.ConfirmPurchase.route) {
                ConfirmPurchaseScreen(
                    navController = navController,
                    phone ="09177651665",
                )
        }

        composable(route= Screen.Shop.route){
            ShopScreen()
        }

        composable(route= Screen.Karbordi.route){
            AppScreen(navController=navController)
        }

        composable(route= Screen.Profile.route){
            ProfileScreen(navController=navController)
        }

        composable(route= Screen.NobateMan.route){
            NobateDehi(navController = navController , sharedViewModel = sharedViewModel)
        }

        composable(route= Screen.DetailsNews.route){
           DetailsScreen(sharedViewModel = sharedViewModel,
               navController = navController)
        }

        composable(route = Screen.Salon.route) {
            SalonScreen(navController = navController,viewModel = sharedViewModel)
        }
        composable(route = Screen.Chaman.route) {
            ChamanScreen(navController = navController,viewModel = sharedViewModel)
        }
        composable(route = Screen.Panel.route) {
            PanelScreen()
        }

        composable(route = Screen.Toopia.route) {
            ToopiaScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = Screen.AddStore.route) {
            AddStore()
        }

        composable(route = Screen.City.route) {
           CityScreen(sharedViewModel=sharedViewModel, navController = navController)
        }
        composable(route = Screen.ChangeCity.route) {
           ChengCity(navController = navController)
        }
        composable(route = Screen.Store.route) {
           StoreScreen(navController=navController,sharedViewModel=sharedViewModel)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController=navController,sharedViewModel=sharedViewModel)
        }

    }
}