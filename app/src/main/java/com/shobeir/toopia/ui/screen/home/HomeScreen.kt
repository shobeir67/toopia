package com.shobeir.toopia.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ContactSupport
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.cheknet.ConnectionState
import com.shobeir.toopia.cheknet.connectivityState
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.data.model.ModelPish
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.screen.components.OurLoading
import com.shobeir.toopia.ui.screen.components.TopSliderSection
import com.shobeir.toopia.ui.screen.login.LoginScreen
import com.shobeir.toopia.ui.screen.login.HomeScreenState
import com.shobeir.toopia.ui.screen.login.RegisterScreen
import com.shobeir.toopia.ui.theme.md_theme_light_onSecondary
import com.shobeir.toopia.ui.theme.md_theme_light_secondary
import com.shobeir.toopia.ui.theme.md_theme_light_tertiary
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.utils.Constants.BASE_URL
import com.shobeir.toopia.viewmodel.HomeViewModel
import com.shobeir.toopia.viewmodel.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
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

    if (phoneUser != "null") {
        Home(
            navController = navController,
            sharedViewModel = sharedViewModel,
            storeViewModel = storeViewModel
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
                    storeViewModel = storeViewModel
                )
            }

            HomeScreenState.REGISTER_STATE -> {
                RegisterScreen(sharedViewModel = sharedViewModel,navController = navController)
            }
        }
    }
}
@Composable
fun Home(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    storeViewModel: StoreViewModel,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllData()
    }
    var teamGame by remember {
        mutableStateOf<ModelTeam?>(null)
    }
    val scope = rememberCoroutineScope()
    var error by remember {
        mutableStateOf(false)
    }
    val teamResponse by viewModel.teamResponse.collectAsState()
    LaunchedEffect(key1 = teamResponse) {
        when (teamResponse) {
            is NetworkResult.Success -> {
                teamGame = teamResponse.data ?: teamGame
                error = false
            }

            is NetworkResult.Error -> {
                error = false
                Log.e("3636", "CartAddressSection error : ${teamResponse.message}")
            }

            is NetworkResult.Loading -> {
                error = true
            }

            else -> {}
        }
    }
    var winnerItem by remember {
        mutableStateOf<User?>(null)
    }
    val winner by viewModel.winnerResponse.collectAsState()
    LaunchedEffect(key1 = winner) {
        when (winner) {
            is NetworkResult.Success -> {
                winnerItem = winner.data ?: winnerItem
            }
            is NetworkResult.Error -> {
                Log.e("3636", "CartAddressSection error : ${winner.message}")
            }
            is NetworkResult.Loading -> {
                error = true
            }
            else -> {}
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp, start = 8.dp, end = 8.dp, top = 0.dp)){
        Column(
            Modifier
                .fillMaxWidth()
                .background(md_theme_light_tertiary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSliderSection(navController = navController, sharedViewModel = sharedViewModel)
            Spacer(modifier = Modifier.height(13.dp))

                teamGame?.let {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(md_theme_light_secondary)
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Text(
                                text = it.date_game,
                                color = Color.White,
                                fontFamily = shabnam,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(13.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    AsyncImage(
                                        model = BASE_URL + it.logoOne, contentDescription = "",
                                        modifier = Modifier.size(60.dp)
                                    )
                                    Text(
                                        text = it.teamOne,
                                        color = Color.White,
                                        fontFamily = shabnam,
                                        fontSize = 18.sp
                                    )
                                }

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    AsyncImage(
                                        model = BASE_URL + it.logoTow, contentDescription = "",
                                        modifier = Modifier.size(60.dp)
                                    )
                                    Text(
                                        text = it.teamTow,
                                        color = Color.White,
                                        fontFamily = shabnam,
                                        fontSize = 18.sp

                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(13.dp))
                            if (it.status == "1") {
                                Button(
                                    onClick = {
                                        sharedViewModel.addTeam(teamGame!!)
                                        navController.navigate(Screen.Forecast.route)
                                    }, colors = ButtonDefaults.buttonColors(
                                        backgroundColor = md_theme_light_onSecondary
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = "آمار مسابقه", fontFamily = shabnam,
                                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                                    )
                                }
                            } else {
                                Button(
                                    onClick = {
                                        sharedViewModel.addTeam(teamGame!!)
                                        navController.navigate(Screen.Forecast.route)
                                    }, colors = ButtonDefaults.buttonColors(
                                        backgroundColor = md_theme_light_onSecondary
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = "پیش بینی کنید", fontFamily = shabnam,
                                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    winnerItem?.let { user ->
                        if (it.status == "1") {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .background(md_theme_light_secondary)
                                        .padding(10.dp),
                                )
                                {
                                    Image(
                                        painter = painterResource(id = R.drawable.firstplace),
                                        contentDescription = "",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Spacer(modifier = Modifier.width(40.dp))
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "برنده این بازی",
                                            color = Color.White,
                                            fontFamily = shabnam,
                                            fontSize = 18.sp,
                                        )
                                        Spacer(modifier = Modifier.height(13.dp))
                                        Text(
                                            text = "${user.phone.substring(7, 11)}***${
                                                user.phone.substring(
                                                    0,
                                                    4
                                                )
                                            }",
                                            color = Color.White,
                                            fontFamily = shabnam,
                                            fontSize = 18.sp,
                                        )
                                        Text(
                                            text = "امتیاز:${user.score}",
                                            color = Color.White,
                                            fontFamily = shabnam,
                                            fontSize = 18.sp,
                                        )
                                    }
                                }


                            }
                        }
                    }

                }
        }
        Box(modifier=Modifier.align(Alignment.BottomStart)){
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(md_theme_light_secondary), horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            scope.launch {
                                storeViewModel.clearDataStore()
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_contact_support_24),
                            contentDescription = "exit"
                        )
                        Text(text = "راهنما", fontFamily = shabnam, color = Color.White)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            scope.launch {
                                storeViewModel.clearDataStore()
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_co_present_24),
                            contentDescription = "exit"
                        )
                        Text(text = "درباره ما", fontFamily = shabnam, color = Color.White)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            scope.launch {
                                storeViewModel.clearDataStore()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Call, contentDescription = "",
                            tint = Color.White)
                        Text(text = "تماس با ما", fontFamily = shabnam, color = Color.White)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            scope.launch {
                                storeViewModel.clearDataStore()
                            }
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "",
                            tint = Color.White)
                        Text(text = "خروج", fontFamily = shabnam, color = Color.White)
                    }
                }
            }
        }
    }
}



