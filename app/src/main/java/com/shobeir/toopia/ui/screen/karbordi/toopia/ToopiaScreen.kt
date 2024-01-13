package com.shobeir.toopia.ui.screen.karbordi.toopia

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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.theme.OrangeYellow1
import com.shobeir.toopia.ui.theme.md_theme_light_scrim
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.utils.Constants
import com.shobeir.toopia.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun ToopiaScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    storeViewModel: StoreViewModel= hiltViewModel(),
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
        .padding(bottom = 20.dp, start = 8.dp, end = 8.dp, top = 5.dp)){
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            teamGame?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .background(md_theme_light_scrim)
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
                                    model = Constants.BASE_URL + it.logoOne, contentDescription = "",
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
                                    model = Constants.BASE_URL + it.logoTow, contentDescription = "",
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
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = OrangeYellow1
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
                                    backgroundColor = OrangeYellow1
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
                                    .background(md_theme_light_scrim)
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
                                        text = "${user.phone!!.substring(7, 11)}***${
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
        Box(modifier= Modifier.align(Alignment.BottomStart)){
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(md_theme_light_scrim), horizontalArrangement = Arrangement.SpaceAround,
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
