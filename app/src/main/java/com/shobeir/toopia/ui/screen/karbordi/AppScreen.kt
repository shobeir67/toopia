package com.shobeir.toopia.ui.screen.karbordi

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shobeir.toopia.R
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.screen.components.CenterBannerItem
import com.shobeir.toopia.ui.theme.shabnam

@Composable
fun AppScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp, top = 10.dp)
            .padding(horizontal = 10.dp),
        ) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clickable {
                    navController.navigate(Screen.NobateMan.route)
                }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.logoman),
                        contentDescription = "")
                    Text(text = " نوبت دهی سالن و چمن مصنوعی کریان", fontFamily = shabnam, fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clickable {navController.navigate(Screen.Toopia.route)}) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "توپیا - پیش بینی مسابقات فوتبال", fontFamily = shabnam, fontSize = 18.sp)
                }
            }
    }
}