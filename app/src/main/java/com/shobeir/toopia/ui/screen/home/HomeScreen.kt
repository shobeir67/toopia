package com.shobeir.toopia.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.screen.login.LoginScreen
import com.shobeir.toopia.ui.screen.login.HomeScreenState
import com.shobeir.toopia.ui.screen.login.RegisterScreen
import com.shobeir.toopia.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel = hiltViewModel(),
    storeViewModel: StoreViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var loading by remember {
        mutableStateOf(false)
    }
    var phoneUser by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        loading = true
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {
            phoneUser = it
        }
    }
    scope.launch {
        delay(100)
        loading = false
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }else {
        if (phoneUser != "null"){
            Home(navController = navController, sharedViewModel = sharedViewModel, storeViewModel = storeViewModel)
        }else{
            when (loginViewModel.screenState) {
                HomeScreenState.LOGIN_STATE -> {
                    LoginScreen(sharedViewModel = sharedViewModel)
                }
                HomeScreenState.HOME_STATE -> {
                    Home(navController = navController, sharedViewModel = sharedViewModel,storeViewModel = storeViewModel)
                }
                HomeScreenState.REGISTER_STATE -> {
                    RegisterScreen(sharedViewModel = sharedViewModel)
                }
            }
        }
    }

}


@Composable
fun Home(navController: NavHostController, sharedViewModel: SharedViewModel,
         storeViewModel: StoreViewModel)
{
    val scope = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        ) {
        Button(onClick = { navController.navigate(Screen.Forecast.route) }) {
            Text(text = "پیش بینی کنید")
        }

        Button(onClick = {
            scope.launch {
                storeViewModel.clearDataStore()
            }
        }) {
            Text(text = "exit")
        }
    }
}


