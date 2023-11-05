package com.shobeir.toopia.ui.screen.forecast

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.model.ModelPish
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.persiandate.PersianDate
import com.shobeir.toopia.persiandate.PersianDateFormat
import com.shobeir.toopia.ui.screen.components.Loading3Dots
import com.shobeir.toopia.ui.theme.md_theme_light_onPrimary
import com.shobeir.toopia.ui.theme.md_theme_light_onPrimaryContainer
import com.shobeir.toopia.ui.theme.md_theme_light_onSecondary
import com.shobeir.toopia.ui.theme.md_theme_light_tertiary
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.utils.Constants
import com.shobeir.toopia.utils.Constants.BASE_URL
import com.shobeir.toopia.viewmodel.PishViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun ForecastScreen(sharedViewModel: SharedViewModel) {
    Forecast(sharedViewModel=sharedViewModel)
}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forecast(
    sharedViewModel: SharedViewModel,
    viewModel: PishViewModel = hiltViewModel(),
    storeViewModel: StoreViewModel = hiltViewModel()
) {
    val team = sharedViewModel.team
    var phoneUser by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {
            phoneUser = it
            delay(300)
            viewModel.getPish(phoneUser)
            viewModel.getResultPlay()
        }
    }

    val contex = LocalContext.current
    var loading by remember {
        mutableStateOf(false)
    }
    var loadingResult by remember {
        mutableStateOf(false)
    }
    var pishGame by remember {
        mutableStateOf<ModelPish?>(null)
    }
    var status by remember {
        mutableStateOf("")
    }

    var statusPlay by remember {
        mutableStateOf(false)
    }

    val getPishGame by viewModel.pishResponse.collectAsState()
    LaunchedEffect(key1 = getPishGame){
        when(getPishGame){
            is NetworkResult.Success -> {
                pishGame = getPishGame.data
                loading = false
            }
            is NetworkResult.Error -> {
                loading = false
                Log.e("3636", "CartAddressSection error : ${getPishGame.message}")
            }
            is NetworkResult.Loading -> {
                loading = true
            }

            else -> {}
        }
    }

    val pishResult by viewModel.setPishResponse.collectAsState()

    LaunchedEffect(key1 = pishResult){
        when(pishResult){
            is NetworkResult.Success -> {
                status=pishResult.message!!
                Toast.makeText(contex, status, Toast.LENGTH_SHORT).show()
                loading = false
            }
            is NetworkResult.Error -> {
                loading = false
                Log.e("3636", "CartAddressSection error : ${pishResult.message}")
            }
            is NetworkResult.Loading -> {
                loading = true
            }

            else -> {}
        }
    }

    var resultPlayItem by remember {
        mutableStateOf<ModelPish?>(null)
    }
    val resultPlay by viewModel.resultResponse.collectAsState()

    LaunchedEffect(key1 = true){
        loadingResult = true
        delay(2000)
        loadingResult = false
        when(resultPlay){
            is NetworkResult.Success -> {
                resultPlayItem=resultPlay.data
            }
            is NetworkResult.Error -> {
                Log.e("3636", "CartAddressSection error : ${resultPlay.message}")
            }
            is NetworkResult.Loading -> {

            }

            else -> {}
        }
    }

    val scope = rememberCoroutineScope()
    pishGame?.let {
               var textGoleOne by remember { mutableStateOf(it.goleOne) }
               var textGoleTow by remember { mutableStateOf(it.goleTow) }

               var textYellowOne by remember { mutableStateOf(it.yellowOne) }
               var textYellowTow by remember { mutableStateOf(it.yellowTow) }

               var textRedOne by remember { mutableStateOf(it.redOne) }
               var textRedTow by remember { mutableStateOf(it.redTow) }

               var textMalekiyatOne by remember { mutableStateOf(it.malekiyatOne) }
               var textMalekiyatTow by remember { mutableStateOf(it.malekiyatTow) }

               var textCornerOne by remember { mutableStateOf(it.cornerOne) }
               var textCornerTow by remember { mutableStateOf(it.cornerTow) }

               var textKhataOne by remember { mutableStateOf(it.khataOne) }
               var textKhataTow by remember { mutableStateOf(it.khataTow) }

               var textAfsaideOne by remember { mutableStateOf(it.afsaideOne) }
               var textAfsaideTow by remember { mutableStateOf(it.afsaideTow) }

               var textShooteOne by remember { mutableStateOf(it.shooteOne) }
               var textShooteTow by remember { mutableStateOf(it.shooteTow) }

        var scoreUser by remember {
            mutableIntStateOf(0)
        }
               val pdate = PersianDate()
               val pFormatter = PersianDateFormat().format(pdate)

               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(md_theme_light_tertiary)
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
                               text = team!!.date_game,
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
                                   AsyncImage(model = BASE_URL +team.logoOne, contentDescription = "",
                                       modifier = Modifier.size(60.dp))

                                   Text(
                                       text = team.teamOne,
                                       color = Color.White,
                                       fontFamily = shabnam,
                                       fontSize = 18.sp
                                   )
                               }

                               Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                   AsyncImage(model = BASE_URL +team.logoTow, contentDescription = "",
                                       modifier = Modifier.size(60.dp))
                                   Text(
                                       text = team.teamTow,
                                       color = Color.White,
                                       fontFamily = shabnam,
                                       fontSize = 18.sp

                                   )
                               }
                           }
                           Spacer(modifier = Modifier.height(8.dp))
                           Text(
                               text = "امتیاز:$scoreUser",
                               color = Color.White,
                               fontFamily = shabnam,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Center,
                               modifier = Modifier.fillMaxWidth()
                           )
                       }

                   }
                   Spacer(modifier = Modifier.height(15.dp))
                   if (loadingResult) {
                       Box(
                           modifier = Modifier
                               .fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                          Loading3Dots(isDark = true)
                       }
                   }
                   resultPlayItem?.let {result->
                       LaunchedEffect(key1 = Unit){
                           delay(1500)
                           if (it.goleOne == result.goleOne){
                               scoreUser += 10
                           }
                           if (it.goleTow == result.goleTow){
                               scoreUser += 10
                           }
                           if (it.yellowOne == result.yellowOne){
                               scoreUser += 10
                           }
                           if (it.yellowTow == result.yellowTow){
                               scoreUser += 10
                           }
                           if (it.redOne == result.redOne){
                               scoreUser += 10
                           }
                           if (it.redTow == result.redTow){
                               scoreUser += 10
                           }
                           if (it.malekiyatOne == result.malekiyatOne){
                               scoreUser += 10
                           }
                           if (it.malekiyatTow == result.malekiyatTow){
                               scoreUser += 10
                           }
                           if (it.cornerOne == result.cornerOne){
                               scoreUser += 10
                           }
                           if (it.cornerTow == result.cornerTow){
                               scoreUser += 10
                           }
                           if (it.khataOne == result.khataOne){
                               scoreUser += 10
                           }
                           if (it.khataTow == result.khataTow){
                               scoreUser += 10
                           }
                           if (it.afsaideOne == result.afsaideOne){
                               scoreUser += 10
                           }
                           if (it.afsaideTow == result.afsaideTow){
                               scoreUser += 10
                           }
                           if (it.shooteOne == result.shooteOne){
                               scoreUser += 10
                           }
                           if (it.shooteTow == result.shooteTow){
                               scoreUser += 10
                           }
                           viewModel.setScore(phone = phoneUser,score = scoreUser.toString())
                       }
                      Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                          Row(
                              modifier = Modifier.fillMaxWidth(),
                              horizontalArrangement = Arrangement.SpaceAround,
                          ) {
                              Text(
                                  text = "پیش بینی شما   نتیجه",
                                  fontSize = 11.sp,
                                  fontFamily = shabnam,
                                  textAlign = TextAlign.Center,
                                  color = md_theme_light_onSecondary,
                                  fontWeight = FontWeight.Bold
                              )
                              Spacer(modifier = Modifier.width(150.dp))
                              Text(
                                  text = "نتیجه   پیش بینی شما",
                                  fontSize = 11.sp,
                                  fontFamily = shabnam,
                                  textAlign = TextAlign.Center,
                                  color = md_theme_light_onSecondary,
                                  fontWeight = FontWeight.Bold
                              )
                          }

                               Row(
                                   modifier = Modifier.fillMaxWidth(),
                                   horizontalArrangement = Arrangement.SpaceAround,
                                   verticalAlignment = Alignment.CenterVertically
                               ) {
                                   OutlinedTextField(
                                       value = textGoleOne,
                                       onValueChange = { textGoleOne = it },
                                       modifier = Modifier
                                           .width(60.dp)
                                           .height(50.dp),
                                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                       textStyle = LocalTextStyle.current.copy(
                                           fontSize = 16.sp,
                                           color = md_theme_light_onSecondary,
                                           textAlign = TextAlign.Center,
                                       ),
                                   )
                                   Text(
                                       text = result.goleOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "گل",
                                       fontFamily = shabnam, fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.goleTow,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   OutlinedTextField(
                                       value = textGoleTow,
                                       onValueChange = { textGoleTow = it },
                                       modifier = Modifier
                                           .width(60.dp)
                                           .height(50.dp),
                                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                       textStyle = LocalTextStyle.current.copy(
                                           fontSize = 16.sp,
                                           color = md_theme_light_onSecondary,
                                           textAlign = TextAlign.Center,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
                                       ),
                                   )
                                   Text(
                                       text = result.yellowOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "کارت زرد",
                                       fontFamily = shabnam,
                                       fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.yellowTow,
                                       fontSize = 16.sp,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
                                       ),
                                   )
                                   Text(
                                       text = result.redOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "کارت قرمز",
                                       fontFamily = shabnam,
                                       fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.redTow,
                                        fontSize = 16.sp,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
                                       ),
                                   )
                                   Text(
                                       text = result.malekiyatOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "درصدمالکیت",
                                       fontFamily = shabnam,
                                       fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.malekiyatTow,
                                       fontSize = 16.sp,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
                                       ),
                                   )
                                   Text(
                                       text = result.cornerOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "کرنر",
                                       fontFamily = shabnam,
                                       fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.cornerTow,
                                       fontSize = 16.sp,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
                                       ),
                                   )
                                   Text(
                                       text = result.khataOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "خطا",
                                       fontFamily = shabnam,
                                       fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.khataTow,
                                       fontSize = 16.sp,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
                                       ),
                                   )
                                   Text(
                                       text = result.afsaideOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "آفساید",
                                       fontFamily = shabnam,
                                       fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.afsaideTow,
                                       fontSize = 16.sp,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,
                                       ),
                                   )
                                   Text(
                                       text = result.shooteOne,
                                       fontSize = 16.sp,
                                       textAlign = TextAlign.Center,
                                       color = md_theme_light_onPrimaryContainer,
                                       fontWeight = FontWeight.Bold
                                   )
                                   Text(
                                       text = "شوت در چارچوب",
                                       fontFamily = shabnam,
                                       fontSize = 20.sp,
                                       modifier = Modifier.width(150.dp),
                                       textAlign = TextAlign.Center,
                                       color = Color.White
                                   )
                                   Text(
                                       text = result.shooteTow,
                                       fontSize = 16.sp,
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
                                           textAlign = TextAlign.Center,
                                           color = md_theme_light_onSecondary,

                                       ),
                                   )
                               }
                               Spacer(modifier = Modifier.height(5.dp))
                          if (pishGame!!.status == "0"){
                              Button(
                                  onClick = {
                                      scope.launch {
                                          viewModel.setPish(
                                              phoneUser,
                                              textGoleOne,
                                              textGoleTow,
                                              textYellowOne,
                                              textYellowTow,
                                              textRedOne,
                                              textRedTow,
                                              textMalekiyatOne,
                                              textMalekiyatTow,
                                              textCornerOne,
                                              textCornerTow,
                                              textKhataOne,
                                              textKhataTow,
                                              textAfsaideOne,
                                              textAfsaideTow,
                                              textShooteOne,
                                              textShooteTow,
                                          )
                                      }
                                  }, modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(10.dp),
                                  colors = ButtonDefaults.buttonColors(
                                      backgroundColor =md_theme_light_onSecondary,
                                  )
                              ) {
                                  Text(
                                      text = "ثبت پیش بینی", fontFamily = shabnam,
                                      fontSize = 18.sp,
                                      fontWeight = FontWeight.Bold
                                  )
                              }
                          }else{
                              Button(
                                  onClick = {}, modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(10.dp),
                                  colors = ButtonDefaults.buttonColors(
                                      backgroundColor =md_theme_light_onSecondary,
                                  )
                              ) {
                                  Text(
                                      text = "پایان مهلت پیش بینی", fontFamily = shabnam,
                                      fontSize = 18.sp,
                                      fontWeight = FontWeight.Bold
                                  )
                              }
                          }

                           }
                   }
         }
    }
}