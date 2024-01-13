package com.shobeir.toopia.ui.screen.karbordi.nobateman.salon

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.nobatdehi.Nobat
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.screen.components.CircularBox
import com.shobeir.toopia.ui.screen.components.CircularBoxTow
import com.shobeir.toopia.ui.screen.karbordi.nobateman.ViewModelNobat
import com.shobeir.toopia.ui.theme.GreenDate
import com.shobeir.toopia.ui.theme.TextWhite
import com.shobeir.toopia.ui.theme.grayCategory
import com.shobeir.toopia.ui.theme.shabnam
import ir.shobeir.nobatemanapp.model.Sanse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SalonScreen(
    navController: NavHostController,
    viewModel: SharedViewModel,
    viewModelSanse: ViewModelNobat = hiltViewModel()
) {
    val user = viewModel.user
    var sanse by remember {
        mutableStateOf<Sanse?>(null)
    }
    val context = LocalContext.current


    var listNobat by remember {
        mutableStateOf(emptyList<Nobat>())
    }

    var loading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        loading = true
        viewModelSanse.getSanse()
        viewModelSanse.listSanse.collectLatest { list ->
            list?.let {
                sanse = it.sanse
                listNobat = it.nobat

            }
        }
    }
    var bg101 by remember {
        mutableStateOf(GreenDate)
    }
    var bg102 by remember {
        mutableStateOf(GreenDate)
    }
    var bg103 by remember {
        mutableStateOf(GreenDate)
    }
    var bg104 by remember {
        mutableStateOf(GreenDate)
    }
    var bg105 by remember {
        mutableStateOf(GreenDate)
    }
    var bg106 by remember {
        mutableStateOf(GreenDate)
    }
    var bg107 by remember {
        mutableStateOf(GreenDate)
    }
    var bg108 by remember {
        mutableStateOf(GreenDate)
    }
    var bg109 by remember {
        mutableStateOf(GreenDate)
    }
    var bg110 by remember {
        mutableStateOf(GreenDate)
    }
    var bg111 by remember {
        mutableStateOf(GreenDate)
    }
    var bg112 by remember {
        mutableStateOf(GreenDate)
    }
    var bg113 by remember {
        mutableStateOf(GreenDate)
    }
    var bg114 by remember {
        mutableStateOf(GreenDate)
    }
    var bg115 by remember {
        mutableStateOf(GreenDate)
    }
    var bg116 by remember {
        mutableStateOf(GreenDate)
    }
    var bg117 by remember {
        mutableStateOf(GreenDate)
    }
    var bg118 by remember {
        mutableStateOf(GreenDate)
    }
    var bg119 by remember {
        mutableStateOf(GreenDate)
    }
    var bg120 by remember {
        mutableStateOf(GreenDate)
    }
    var bg121 by remember {
        mutableStateOf(GreenDate)
    }
    var state by remember {
        mutableStateOf(false)
    }
    var textNobat by remember {
        mutableStateOf("موردی را انتخاب نکرده اید!")
    }
    for (i in listNobat.indices) {
        when (listNobat[i].code) {
            "101" -> {
                bg101 = Color.Red
            }
            "102" -> {
                bg102 = Color.Red
            }
            "103" -> {
                bg103 = Color.Red
            }
            "104" -> bg104 = Color.Red
            "105" -> bg105 = Color.Red
            "106" -> bg106 = Color.Red
            "107" -> bg107 = Color.Red
            "108" -> bg108 = Color.Red
            "109" -> bg109 = Color.Red
            "110" -> bg110 = Color.Red
            "111" -> bg111 = Color.Red
            "112" -> bg112 = Color.Red
            "113" -> bg113 = Color.Red
            "114" -> bg114 = Color.Red
            "115" -> bg115 = Color.Red
            "116" -> bg116 = Color.Red
            "117" -> bg117 = Color.Red
            "118" -> bg118 = Color.Red
            "119" -> bg119 = Color.Red
            "120" -> bg120 = Color.Red
            "121" -> bg121 = Color.Red
        }
    }
    var codeNobat by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    scope.launch {
        delay(400)
        loading = false
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            Text(
                text = "سالن شهدا", fontFamily = shabnam,
                modifier = Modifier.align(CenterHorizontally)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp), color = MaterialTheme.colors.grayCategory
            )
            sanse?.let { date ->
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularBox(
                        title = "شنبه",
                        date = date.shanbe,
                        backgroundColor = Color.Yellow,
                        onItemClick = {}
                    )
                    CircularBoxTow(
                        date = date.sansepanjom,
                        onItemClick = {
                            if (bg101 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "شنبه ${date.shanbe} ساعت${date.sansepanjom}سالن شهدا"
                                codeNobat = "101"
                            }
                        },
                        backgroundColor = bg101
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg102 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "شنبه ${date.shanbe} ساعت${date.sansesheshom}سالن شهدا"
                                codeNobat = "102"
                            }
                        },
                        backgroundColor = bg102
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg103 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "شنبه ${date.shanbe} ساعت${date.sansehaftom}سالن شهدا"
                                codeNobat = "103"
                            }
                        },
                        backgroundColor = bg103
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularBox(
                        title = "یکشنبه",
                        date = date.yekshanbe,
                        backgroundColor = Color.Yellow,
                        onItemClick = {})
                    CircularBoxTow(
                        date = date.sansepanjom,
                        onItemClick = {
                            if (bg104 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat ="یکشنبه ${date.yekshanbe} ساعت${date.sansepanjom}سالن شهدا"
                                codeNobat = "104"

                            }
                        },
                        backgroundColor = bg104
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg105 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "یکشنبه ${date.yekshanbe} ساعت${date.sansesheshom}سالن شهدا"
                                codeNobat = "105"
                            }
                        },
                        backgroundColor = bg105
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg106 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "یکشنبه ${date.yekshanbe} ساعت${date.sansehaftom}سالن شهدا"
                                codeNobat = "106"
                            }
                        },
                        backgroundColor = bg106
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularBox(
                        title = "دوشنبه",
                        date = date.doshanbe,
                        backgroundColor = Color.Yellow,
                        onItemClick = {})
                    CircularBoxTow(
                        date = date.sansepanjom,
                        onItemClick = {
                            if (bg107 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "دوشنبه ${date.doshanbe} ساعت${date.sansepanjom}سالن شهدا"
                                codeNobat = "107"
                            }
                        },
                        backgroundColor = bg107
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg108 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "دوشنبه ${date.doshanbe} ساعت${date.sansesheshom}سالن شهدا"
                                codeNobat = "108"
                            }
                        },
                        backgroundColor = bg108
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg109 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "دوشنبه ${date.doshanbe} ساعت${date.sansehaftom}سالن شهدا"
                                codeNobat = "109"
                            }
                        },
                        backgroundColor = bg109
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularBox(
                        title = "سه شنبه",
                        date = date.seshanbe,
                        backgroundColor = Color.Yellow,
                        onItemClick = {})
                    CircularBoxTow(
                        date = date.sansepanjom,
                        onItemClick = {
                            if (bg110 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "سه شنبه ${date.seshanbe} ساعت${date.sansepanjom}سالن شهدا"
                                codeNobat = "110"
                            }
                        },
                        backgroundColor = bg110
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg111 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "سه شنبه ${date.seshanbe} ساعت${date.sansesheshom}سالن شهدا"
                                codeNobat = "111"
                            }
                        },
                        backgroundColor = bg111
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg112 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "سه شنبه ${date.seshanbe} ساعت${date.sansehaftom}سالن شهدا"
                                codeNobat = "112"
                            }
                        },
                        backgroundColor = bg112
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularBox(
                        title = "چهارشنبه",
                        date = date.chaharshanbe,
                        backgroundColor = Color.Yellow,
                        onItemClick = {})
                    CircularBoxTow(
                        date = date.sansepanjom,
                        onItemClick = {
                            if (bg113 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "چهارشنبه ${date.chaharshanbe} ساعت${date.sansepanjom}سالن شهدا"
                                codeNobat = "113"
                            }
                        },
                        backgroundColor = bg113
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg114 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "چهارشنبه ${date.chaharshanbe} ساعت${date.sansesheshom}سالن شهدا"
                                codeNobat = "114"
                            }
                        },
                        backgroundColor = bg114
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg115 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "چهارشنبه ${date.chaharshanbe} ساعت${date.sansehaftom}سالن شهدا"
                                codeNobat = "115"
                            }
                        },
                        backgroundColor = bg115
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularBox(
                        title = "پنجشنبه",
                        date = date.panjshanbe,
                        backgroundColor = Color.Yellow,
                        onItemClick = {})
                    CircularBoxTow(
                        date = date.sansepanjom,
                        onItemClick = {
                            if (bg116 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "پنجشنبه ${date.panjshanbe} ساعت${date.sansepanjom}سالن شهدا"
                                codeNobat = "116"
                            }
                        },
                        backgroundColor = bg116
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg117 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "پنجشنبه ${date.panjshanbe} ساعت${date.sansesheshom}سالن شهدا"
                                codeNobat = "117"
                            }
                        },
                        backgroundColor = bg117
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg118 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "پنجشنبه ${date.panjshanbe} ساعت${date.sansehaftom}سالن شهدا"
                                codeNobat = "118"
                            }
                        },
                        backgroundColor = bg118
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularBox(
                        title = "جمعه",
                        date = date.jomeh,
                        backgroundColor = Color.Yellow,
                        onItemClick = {})
                    CircularBoxTow(
                        date = date.sansepanjom,
                        onItemClick = {
                            if (bg119 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "جمعه ${date.jomeh} ساعت${date.sansepanjom}سالن شهدا"
                                codeNobat = "119"
                            }
                        },
                        backgroundColor = bg119
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg120 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "جمعه ${date.jomeh} ساعت${date.sansesheshom}سالن شهدا"
                                codeNobat = "120"
                            }
                        },
                        backgroundColor = bg120
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg121 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "جمعه ${date.jomeh} ساعت${date.sansehaftom}سالن شهدا"
                                codeNobat = "121"
                            }
                        },
                        backgroundColor = bg121
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "نوبت انتخابی شما:",
                    fontFamily = shabnam, color = GreenDate, fontSize = 18.sp
                )
                Text(text = textNobat, fontFamily= shabnam, color = Color.Red, fontSize = 18.sp)

                if (textNobat != "موردی را انتخاب نکرده اید!") {
                    Button(onClick = {
                        if (user!!.price == "0") {
                            Toast.makeText(context, "اعتبار شما کافی نیست", Toast.LENGTH_SHORT)
                                .show()
                        }else{
                            scope.launch {
                                loading = true
                                viewModelSanse.addNobat(codeNobat,user.phone!!,textNobat)
                                viewModelSanse.status.collectLatest {
                                    it?.let {msg->
                                        when(msg){
                                            "ok"->{
                                                navController.navigate(Screen.NobateMan.route)
                                                Toast.makeText(context, "ثبت نوبت انجام شد...", Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                            "no"->{
                                                Toast.makeText(context, "توسط شخص دیگری ثبت شده. زمان دیگری را انتخاب کنید", Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                            "error" ->{
                                                Toast.makeText(context, "خطا در ثبت نوبت!!", Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }, modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text ="ثبت نوبت", fontFamily = shabnam, color = TextWhite)
                    }
                }
            }
        }
    }
}
