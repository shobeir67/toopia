package com.shobeir.toopia.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.ui.theme.md_primaryContainer
import com.shobeir.toopia.ui.theme.semiDarkText
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.R
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.navigation.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavHostController,
    storeViewModel: StoreViewModel = hiltViewModel(),
) {
    var city by rememberSaveable { mutableStateOf("") }
    var phoneUser by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        storeViewModel.readString(PreferenceHelper.CITY).collectLatest {
            city = it
        }
    }
    LaunchedEffect(key1 = phoneUser) {
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {phone->
            phoneUser = phone
        }
    }
    if (phoneUser == "null"){
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Profile.route) {
                inclusive = true
            }
        }
    }else{
        Column(modifier = Modifier
            .fillMaxSize()
            .background(md_primaryContainer)
            .padding(10.dp)
        )
        {
            Text(text ="کاربری: $phoneUser", modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center, fontFamily = shabnam, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                MyOrdersItem(
                    text = "تماس با ما",
                    painter = painterResource(id = R.drawable.icons8_call_512px),
                    isHaveDivider = true
                ){

                }
                MyOrdersItem(
                    text = "بالاشهرمن: $city",
                    painter = painterResource(id = R.drawable.location),
                    isHaveDivider = true
                ){
                    navController.navigate(Screen.ChangeCity.route)
                }
                MyOrdersItem(
                    text = "خروج از حساب",
                    painter = painterResource(id = R.drawable.logout),
                    isHaveDivider = false

                ){
                    scope.launch {
                        storeViewModel.clearDataStore()
                        navController.navigate(Screen.Home.route)
                    }
                }
            }
        }
    }

}


@Composable
private fun MyOrdersItem(
    text: String,
    painter: Painter,
    isHaveDivider: Boolean,
    action: () -> Unit = {}
) {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .clickable { action() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                fontFamily = shabnam,
                color = MaterialTheme.colors.semiDarkText,
                text = text,
                fontSize = 16.sp
            )
        }
        if (isHaveDivider){
            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(90.dp)
                    .alpha(0.4f),
                color = Color.LightGray,
            )
        }

}

@Composable
fun SettingMenuSection(
    navController: NavHostController,
    storeViewModel: StoreViewModel
) {
    var city by rememberSaveable { mutableStateOf("") }
    var phoneUser by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        storeViewModel.readString(PreferenceHelper.CITY).collectLatest {
            city = it
        }
    }
    LaunchedEffect(key1 = phoneUser) {
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {phone->
            phoneUser = phone
        }
    }

    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = phoneUser, fontFamily = shabnam, fontSize = 18.sp)
        MenuRowItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.shop),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            },
            text = phoneUser,
            isHaveDivider = true
        ) {
            navController.navigate(Screen.AddStore.route)
        }
        MenuRowItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.shop),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            },
            text = "غرفه من",
            isHaveDivider = true
        ) {
            navController.navigate(Screen.Store.route)
        }
        MenuRowItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.trolle),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            },
            text = "افزودن محصول",
            isHaveDivider = true
        ) {


        }
        MenuRowItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.advertise),
                    contentDescription = "", modifier = Modifier.size(40.dp)
                )

            },
            text = "ثبت آگهی",
            isHaveDivider = true
        ) {

        }

        MenuRowItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.icons8_call_512px),
                    contentDescription = "", modifier = Modifier.size(40.dp)
                )

            },
            text = "تماس با ما",
            isHaveDivider = true
        ) {

        }
        MenuRowItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "", modifier = Modifier.size(40.dp)
                )

            },
            text = "بالاشهرمن: ${city}",
            isHaveDivider = true
        ) {
            navController.navigate(Screen.ChangeCity.route)
        }
        MenuRowItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "", modifier = Modifier.size(40.dp)
                )

            },
            text = "خروج از حساب",
            isHaveDivider = false
        ) {
            scope.launch {
                storeViewModel.clearDataStore()
                navController.navigate(Screen.Home.route)
            }
        }
    }

}

