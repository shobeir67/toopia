package com.shobeir.toopia.ui.screen.forecast

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
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobeir.toopia.R
import com.shobeir.toopia.persiandate.PersianDate
import com.shobeir.toopia.persiandate.PersianDateFormat
import com.shobeir.toopia.ui.theme.md_theme_light_onPrimary
import com.shobeir.toopia.ui.theme.md_theme_light_onPrimaryContainer
import com.shobeir.toopia.ui.theme.shabnam
import kotlinx.coroutines.delay



@Composable
fun ForecastScreen() {
    Forecast()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forecast() {
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


    val goleOne by remember { mutableStateOf("") }
    val goleTow by remember { mutableStateOf("") }

    val yellowOne by remember { mutableStateOf("") }
    val yellowTow by remember { mutableStateOf("") }

    val redOne by remember { mutableStateOf("") }
    val redTow by remember { mutableStateOf("") }

    val malekiyatOne by remember { mutableStateOf("") }
    val malekiyatTow by remember { mutableStateOf("") }

    val cornerOne by remember { mutableStateOf("") }
    val cornerTow by remember { mutableStateOf("") }

    val khataOne by remember { mutableStateOf("") }
    val khataTow by remember { mutableStateOf("") }

    val afsaideOne by remember { mutableStateOf("") }
    val afsaideTow by remember { mutableStateOf("") }

    val shooteOne by remember { mutableStateOf("") }
    val shooteTow by remember { mutableStateOf("") }

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textGoleOne,
                    onValueChange = { textGoleOne = it }, modifier = Modifier
                        .width(60.dp)
                        .height(50.dp)
                        .padding(0.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =goleOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "گل",
                    fontFamily = shabnam, fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =goleTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textGoleTow,
                    onValueChange = { textGoleTow = it }, modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textYellowOne,
                    onValueChange = { textYellowOne = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =yellowOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "کارت زرد",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =yellowTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textYellowTow,
                    onValueChange = { textYellowTow = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textRedOne,
                    onValueChange = { textRedOne = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =redOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "کارت قرمز",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =redTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textRedTow,
                    onValueChange = { textRedTow = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textMalekiyatOne,
                    onValueChange = { textMalekiyatOne = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =malekiyatOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "درصدمالکیت",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =malekiyatTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textMalekiyatTow,
                    onValueChange = { textMalekiyatTow = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textCornerOne,
                    onValueChange = { textCornerOne = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =cornerOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "کرنر",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =cornerTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textCornerTow,
                    onValueChange = { textCornerTow = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textKhataOne,
                    onValueChange = { textKhataOne = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =khataOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "خطا",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =khataTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textKhataTow,
                    onValueChange = { textKhataTow = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textAfsaideOne,
                    onValueChange = { textAfsaideOne = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =afsaideOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "آفساید",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =afsaideTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textAfsaideTow,
                    onValueChange = { textAfsaideTow = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textShooteOne,
                    onValueChange = { textShooteOne = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                )
                Text(
                    text =shooteOne,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "شوت در چارچوب",
                    fontFamily = shabnam,
                    fontSize = 20.sp,
                    modifier = Modifier.width(150.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text =shooteTow,
                    fontFamily = shabnam, fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = md_theme_light_onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = textShooteTow,
                    onValueChange = { textShooteTow = it },
                    modifier = Modifier
                        .width(60.dp)
                        .height(50.dp),
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
                    .padding(10.dp),
                 colors = ButtonDefaults.buttonColors(
                    backgroundColor = md_theme_light_onPrimaryContainer
                )
            ) {
                Text(
                    text = "ثبت پیش بینی", fontFamily = shabnam,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }

}