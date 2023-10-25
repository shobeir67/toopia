package com.shobeir.toopia.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.navigation.Screen

@Composable
fun HomeScreen(navController:NavHostController,sharedViewModel: SharedViewModel) {
    Home(navController = navController, sharedViewModel = sharedViewModel)
}


@Composable
fun Home(navController:NavHostController,sharedViewModel: SharedViewModel) {

    Column(Modifier.fillMaxSize().padding(10.dp)) {
        Button(onClick = { navController.navigate(Screen.Forecast.route) }) {
           Text(text = "پیش بینی کنید")
        }
    }
}


