package com.shobeir.toopia.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.ui.screen.login.LoginScreen
import com.shobeir.toopia.ui.screen.login.HomeScreenState
import com.shobeir.toopia.ui.screen.login.RegisterScreen
import com.shobeir.toopia.viewmodel.LoginViewModel
import ir.shobeir.avayekaryan.model.Image
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.screen.components.TopSliderSection
import com.shobeir.toopia.ui.screen.news.NewsItem
import com.shobeir.toopia.ui.screen.news.NewsScreen
import com.shobeir.toopia.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel = hiltViewModel(),
    viewModel: HomeViewModel = hiltViewModel(),
    storeViewModel: StoreViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var loading by remember {
        mutableStateOf(false)
    }
    var phoneUser by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        loading = true
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {
            phoneUser = it
        }
        storeViewModel.readString(PreferenceHelper.CITY).collectLatest {
            city = it
        }
    }

    if (phoneUser != "null") {
        Home(
            navController = navController,
            sharedViewModel = sharedViewModel,
            viewModel = viewModel
        )
    } else {
        when (loginViewModel.screenState) {
            HomeScreenState.LOGIN_STATE -> {
                LoginScreen(sharedViewModel = sharedViewModel)
            }
            HomeScreenState.HOME_STATE -> {
                Home(
                    navController = navController,
                    sharedViewModel = sharedViewModel,
                    viewModel = viewModel

                )
            }
            HomeScreenState.REGISTER_STATE -> {
                RegisterScreen(sharedViewModel = sharedViewModel,navController = navController)
            }

            HomeScreenState.CITY_STATE -> {
                CityScreen(sharedViewModel=sharedViewModel)
            }
        }
    }
}


@Composable
fun Home(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: HomeViewModel
) {
    LaunchedEffect(key1 = true){
        viewModel.getAllData()
    }
   NewsScreen(navController = navController,
       sharedViewModel = sharedViewModel,
       viewModel = viewModel
   )
}



