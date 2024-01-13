package com.shobeir.toopia.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.ui.theme.md_primaryContainer
import com.shobeir.toopia.ui.theme.semiDarkText
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.R
import com.shobeir.toopia.navigation.Screen

@Composable
fun ProfileScreen( navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(md_primaryContainer)) {
        SettingMenuSection(navController =navController )
    }

}


@Composable
private fun MyOrdersItem(
    text: String,
    painter: Painter
) {
    Row(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            androidx.compose.material.Text(
                fontFamily = shabnam,
                color = MaterialTheme.colors.semiDarkText,
                text = text
            )
        }
        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(90.dp)
                .alpha(0.4f),
            color = Color.LightGray,
        )
    }
}

@Composable
fun SettingMenuSection(
    navController: NavHostController,
) {

    MenuRowItem(
        icon = {
           Image(painter = painterResource(id = R.drawable.shop), contentDescription ="", modifier = Modifier.size(40.dp) )
        },
        text = "ایجاد غرفه",
        isHaveDivider = true
    ) {
        navController.navigate(Screen.AddStore.route)
    }

    MenuRowItem(
        icon = {
            Image(painter = painterResource(id = R.drawable.trolle), contentDescription ="", modifier = Modifier.size(40.dp) )
        },
        text = "افزودن محصول",
        isHaveDivider = true
    ) {


    }

    MenuRowItem(
        icon = {
            Image(painter = painterResource(id = R.drawable.advertise),
                contentDescription ="", modifier = Modifier.size(40.dp) )

        },
        text = "ثبت آگهی",
        isHaveDivider = true
    ) {

    }

    MenuRowItem(
        icon = {
            Image(painter = painterResource(id = R.drawable.call_480px),
                contentDescription ="", modifier = Modifier.size(40.dp) )

        },
        text = "تماس با ما",
        isHaveDivider = true
    ) {

    }

    MenuRowItem(
        icon = {
            Image(painter = painterResource(id = R.drawable.logout),
                contentDescription ="", modifier = Modifier.size(40.dp) )

        },
        text = "خروج از حساب",
        isHaveDivider = false
    ) {

    }

}

