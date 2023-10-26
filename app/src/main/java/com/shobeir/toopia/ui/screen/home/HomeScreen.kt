package com.shobeir.toopia.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.screen.login.LoginScreen
import com.shobeir.toopia.ui.screen.login.ProfileScreenState
import com.shobeir.toopia.ui.screen.login.RegisterScreen
import com.shobeir.toopia.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel = hiltViewModel(),
    storeViewModel: StoreViewModel = hiltViewModel()
) {
    var phoneUser by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {
            phoneUser = it
        }
    }

    if (phoneUser != "null"){
        Home(navController = navController, sharedViewModel = sharedViewModel)
    }else{
        when (loginViewModel.screenState) {
            ProfileScreenState.LOGIN_STATE -> {
                LoginScreen(sharedViewModel = sharedViewModel)
            }
            ProfileScreenState.HOME_STATE -> {
                Home(navController = navController, sharedViewModel = sharedViewModel)
            }
            ProfileScreenState.REGISTER_STATE -> {
                RegisterScreen(sharedViewModel = sharedViewModel)
            }
        }
    }
}


@Composable
fun Home(navController: NavHostController, sharedViewModel: SharedViewModel) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)) {
        Button(onClick = { navController.navigate(Screen.Forecast.route) }) {
            Text(text = "پیش بینی کنید")
        }
    }
}


