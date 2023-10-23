package com.shobeir.toopia.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobeir.toopia.ui.theme.md_theme_light_onPrimary
import com.shobeir.toopia.R
import com.shobeir.toopia.persiandate.PersianDate
import com.shobeir.toopia.persiandate.PersianDateFormat
import com.shobeir.toopia.ui.theme.shabnam
import kotlinx.coroutines.delay

@Composable
fun HomeScreen() {
    Home()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    var textGoleOne by remember { mutableStateOf("0") }
    var textGoleTow by remember { mutableStateOf("0") }

    var textYellowOne by remember { mutableStateOf("0") }
    var textYellowTow by remember { mutableStateOf("0") }

    var textRedOne by remember { mutableStateOf("0") }

    var textRedTow by remember { mutableStateOf("0") }

    var textMalekiyatOne by remember { mutableStateOf("0") }
    var textMalekiyatTow by remember { mutableStateOf("0") }

    var textCornerOne by remember { mutableStateOf("0") }
    var textCornerTow by remember { mutableStateOf("0") }

    var textKhataOne by remember { mutableStateOf("0") }
    var textKhataTow by remember { mutableStateOf("0") }

    var textAfsaideOne by remember { mutableStateOf("0") }
    var textAfsaideTow by remember { mutableStateOf("0") }

    var textShooteOne by remember { mutableStateOf("0") }
    var textShooteTow by remember { mutableStateOf("0") }


    var goleOne by remember { mutableStateOf("") }
    var goleTow by remember { mutableStateOf("") }

    var yellowOne by remember { mutableStateOf("") }
    var yellowTow by remember { mutableStateOf("") }

    var redOne by remember { mutableStateOf("") }
    var redTow by remember { mutableStateOf("") }

    var malekiyatOne by remember { mutableStateOf("") }
    var malekiyatTow by remember { mutableStateOf("") }

    var cornerOne by remember { mutableStateOf("") }
    var cornerTow by remember { mutableStateOf("") }

    var khataOne by remember { mutableStateOf("") }
    var khataTow by remember { mutableStateOf("") }

    var afsaideOne by remember { mutableStateOf("") }
    var afsaideTow by remember { mutableStateOf("") }

    var shooteOne by remember { mutableStateOf("") }
    var shooteTow by remember { mutableStateOf("") }

    val pdate= PersianDate()
    val pFormatter = PersianDateFormat().format(pdate)
    var timeLeft by remember {
        mutableIntStateOf(120)
    }
    LaunchedEffect(key1 = timeLeft){
        while (timeLeft >0){
            delay(1000L)
            timeLeft--
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(md_theme_light_onPrimary)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.bg),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = pFormatter,
                    color = Color.White,
                    fontFamily = shabnam,
                    fontSize = 20.sp,
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
                        Image(
                            painter = painterResource(id = R.mipmap.svoqvqnj),
                            contentDescription = "", modifier = Modifier.size(60.dp)
                        )

                        Text(
                            text = "جبل الطارق", color = Color.White, fontFamily = shabnam, fontSize = 18.sp
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.mipmap.z4aa3n0p),
                            contentDescription = "",
                            modifier = Modifier.size(60.dp)
                        )
                        Text(
                            text = "ایرلند", color = Color.White, fontFamily = shabnam, fontSize = 18.sp

                            )
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(15.dp))

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//            Text(
//                text = "آمار مسابقه", fontFamily = shabnam, fontSize = 26.sp,
//                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textGoleOne,
                    onValueChange = { textGoleOne = it }, modifier = Modifier
                        .size(50.dp)
                        .padding(0.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "گل",
                    fontFamily = shabnam, fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textGoleTow,
                    onValueChange = { textGoleTow = it }, modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textYellowOne,
                    onValueChange = { textYellowOne = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "کارت زرد",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textYellowTow,
                    onValueChange = { textYellowTow = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textRedOne,
                    onValueChange = { textRedOne = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "کارت قرمز",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textRedTow,
                    onValueChange = { textRedTow = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textMalekiyatOne,
                    onValueChange = { textMalekiyatOne = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "مالکیت",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textMalekiyatTow,
                    onValueChange = { textMalekiyatTow = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textCornerOne,
                    onValueChange = { textCornerOne = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "کرنر",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textCornerTow,
                    onValueChange = { textCornerTow = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textKhataOne,
                    onValueChange = { textKhataOne = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "خطا",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textKhataTow,
                    onValueChange = { textKhataTow = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textAfsaideOne,
                    onValueChange = { textAfsaideOne = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "آفساید",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textAfsaideTow,
                    onValueChange = { textAfsaideTow = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textShooteOne,
                    onValueChange = { textShooteOne = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text = "شوت در چارچوب",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = textShooteTow,
                    onValueChange = { textShooteTow = it },
                    modifier = Modifier.size(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "ثبت پیش بینی", fontFamily = shabnam,
                    fontSize = 18.sp,
                )
            }
        }
    }
}


