package com.shobeir.toopia.ui.screen.karbordi.nobateman.chaman

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.model.nobatdehi.Nobat
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.screen.components.CircularBox
import com.shobeir.toopia.ui.screen.components.CircularBoxTow
import com.shobeir.toopia.ui.screen.karbordi.nobateman.ViewModelNobat
import com.shobeir.toopia.ui.theme.GreenDate
import com.shobeir.toopia.ui.theme.grayCategory
import com.shobeir.toopia.ui.theme.shabnam
import ir.shobeir.nobatemanapp.model.Sanse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ChamanScreen(
    navController: NavHostController,
    viewModel: SharedViewModel,
    viewModelSanse: ViewModelNobat = hiltViewModel()
) {
    val user = viewModel.user
    var sanse by remember {
        mutableStateOf<Sanse?>(null)
    }
    val context = LocalContext.current


    var newsList by remember {
        mutableStateOf<List<Nobat>>(emptyList())
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
                newsList = it.nobat
            }
        }

    }
    var bg201 by remember {
        mutableStateOf(GreenDate)
    }
    var bg202 by remember {
        mutableStateOf(GreenDate)
    }
    var bg203 by remember {
        mutableStateOf(GreenDate)
    }
    var bg204 by remember {
        mutableStateOf(GreenDate)
    }
    var bg205 by remember {
        mutableStateOf(GreenDate)
    }
    var bg206 by remember {
        mutableStateOf(GreenDate)
    }
    var bg207 by remember {
        mutableStateOf(GreenDate)
    }
    var bg208 by remember {
        mutableStateOf(GreenDate)
    }
    var bg209 by remember {
        mutableStateOf(GreenDate)
    }
    var bg210 by remember {
        mutableStateOf(GreenDate)
    }
    var bg211 by remember {
        mutableStateOf(GreenDate)
    }
    var bg212 by remember {
        mutableStateOf(GreenDate)
    }
    var bg213 by remember {
        mutableStateOf(GreenDate)
    }
    var bg214 by remember {
        mutableStateOf(GreenDate)
    }
    var bg215 by remember {
        mutableStateOf(GreenDate)
    }
    var bg216 by remember {
        mutableStateOf(GreenDate)
    }
    var bg217 by remember {
        mutableStateOf(GreenDate)
    }
    var bg218 by remember {
        mutableStateOf(GreenDate)
    }
    var bg219 by remember {
        mutableStateOf(GreenDate)
    }
    var bg220 by remember {
        mutableStateOf(GreenDate)
    }
    var bg221 by remember {
        mutableStateOf(GreenDate)
    }

    var textNobat by remember {
        mutableStateOf("موردی را انتخاب نکرده اید!")
    }
    for (i in newsList.indices) {
        when (newsList[i].code) {
            "201" -> bg201 = Color.Red
            "202" -> bg202 = Color.Red
            "203" -> bg203 = Color.Red
            "204" -> bg204 = Color.Red
            "205" -> bg205 = Color.Red
            "206" -> bg206 = Color.Red
            "207" -> bg207 = Color.Red
            "208" -> bg208 = Color.Red
            "209" -> bg209 = Color.Red
            "210" -> bg210 = Color.Red
            "211" -> bg211 = Color.Red
            "212" -> bg212 = Color.Red
            "213" -> bg213 = Color.Red
            "214" -> bg214 = Color.Red
            "215" -> bg215 = Color.Red
            "216" -> bg216 = Color.Red
            "217" -> bg217 = Color.Red
            "218" -> bg218 = Color.Red
            "219" -> bg219 = Color.Red
            "220" -> bg220 = Color.Red
            "221" -> bg221 = Color.Red
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
                text = "زمین چمن مصنوعی", fontFamily = shabnam,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = MaterialTheme.colors.grayCategory
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
                            if (bg201 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "شنبه ${date.shanbe} ساعت${date.sansepanjom}چمن مصنوعی"
                                codeNobat = "201"
                            }
                        },
                        backgroundColor = bg201
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg202 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "شنبه ${date.shanbe} ساعت${date.sansesheshom}چمن مصنوعی"
                                codeNobat = "202"
                            }
                        },
                        backgroundColor = bg202
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg203 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "شنبه ${date.shanbe} ساعت${date.sansehaftom}چمن مصنوعی"
                                codeNobat = "203"
                            }
                        },
                        backgroundColor = bg203
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
                            if (bg204 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "یکشنبه ${date.yekshanbe} ساعت${date.sansepanjom}چمن مصنوعی"
                                codeNobat = "204"

                            }
                        },
                        backgroundColor = bg204
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg205 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "یکشنبه ${date.yekshanbe} ساعت${date.sansesheshom}چمن مصنوعی"
                                codeNobat = "205"
                            }
                        },
                        backgroundColor = bg205
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg206 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "یکشنبه ${date.yekshanbe} ساعت${date.sansehaftom}چمن مصنوعی"
                                codeNobat = "206"
                            }
                        },
                        backgroundColor = bg206
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
                            if (bg207 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "دوشنبه ${date.doshanbe} ساعت${date.sansepanjom}چمن مصنوعی"
                                codeNobat = "207"
                            }
                        },
                        backgroundColor = bg207
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg208 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "دوشنبه ${date.doshanbe} ساعت${date.sansesheshom}چمن مصنوعی"
                                codeNobat = "208"
                            }
                        },
                        backgroundColor = bg208
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg209 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "دوشنبه ${date.doshanbe} ساعت${date.sansehaftom}چمن مصنوعی"
                                codeNobat = "209"
                            }
                        },
                        backgroundColor = bg209
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
                            if (bg210 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "سه شنبه ${date.seshanbe} ساعت${date.sansepanjom}چمن مصنوعی"
                                codeNobat = "210"
                            }
                        },
                        backgroundColor = bg210
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg211 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "سه شنبه ${date.seshanbe} ساعت${date.sansesheshom}چمن مصنوعی"
                                codeNobat = "211"
                            }
                        },
                        backgroundColor = bg211
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg212 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "سه شنبه ${date.seshanbe} ساعت${date.sansehaftom}چمن مصنوعی"
                                codeNobat = "212"
                            }
                        },
                        backgroundColor = bg212
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
                            if (bg213 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "چهارشنبه ${date.chaharshanbe} ساعت${date.sansepanjom}چمن مصنوعی"
                                codeNobat = "213"
                            }
                        },
                        backgroundColor = bg213
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg214 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "چهارشنبه ${date.chaharshanbe} ساعت${date.sansesheshom}چمن مصنوعی"
                                codeNobat = "214"
                            }
                        },
                        backgroundColor = bg214
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg215 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "چهارشنبه ${date.chaharshanbe} ساعت${date.sansehaftom}چمن مصنوعی"
                                codeNobat = "215"
                            }
                        },
                        backgroundColor = bg215
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
                            if (bg216 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "پنجشنبه ${date.panjshanbe} ساعت${date.sansepanjom}چمن مصنوعی"
                                codeNobat = "216"
                            }
                        },
                        backgroundColor = bg216
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg217 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "پنجشنبه ${date.panjshanbe} ساعت${date.sansesheshom}چمن مصنوعی"
                                codeNobat = "217"
                            }
                        },
                        backgroundColor = bg217
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg218 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat =
                                    "پنجشنبه ${date.panjshanbe} ساعت${date.sansehaftom}چمن مصنوعی"
                                codeNobat = "218"
                            }
                        },
                        backgroundColor = bg218
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
                            if (bg219 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "جمعه ${date.jomeh} ساعت${date.sansepanjom}چمن مصنوعی"
                                codeNobat = "219"
                            }
                        },
                        backgroundColor = bg219
                    )
                    CircularBoxTow(
                        date = date.sansesheshom,
                        onItemClick = {
                            if (bg220 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "جمعه ${date.jomeh} ساعت${date.sansesheshom}چمن مصنوعی"
                                codeNobat = "120"
                            }
                        },
                        backgroundColor = bg220
                    )
                    CircularBoxTow(
                        date = date.sansehaftom,
                        onItemClick = {
                            if (bg221 == Color.Red) {
                                Toast.makeText(context, "قبلاً رزرو شده است", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                textNobat = "جمعه ${date.jomeh} ساعت${date.sansehaftom}چمن مصنوعی"
                                codeNobat = "221"
                            }
                        },
                        backgroundColor = bg221
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "نوبت انتخابی شما:",
                    fontFamily = shabnam, color = GreenDate, fontSize = 18.sp
                )
                Text(
                    text = textNobat,
                    fontFamily = shabnam,
                    color = Color.Red,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))
                if (textNobat != "موردی را انتخاب نکرده اید!") {
                    Button(
                        onClick = {
                            if (user!!.price == "0") {
                                Toast.makeText(context, "اعتبار شما کافی نیست", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                scope.launch {
                                    loading = true
                                    viewModelSanse.addNobat(
                                        codeNobat,
                                        user.phone!!,
                                        textNobat
                                    )
                                    viewModelSanse.status.collectLatest {
                                        it?.let { msg ->
                                            when (msg) {
                                                "ok" -> {
                                                    navController.navigate(Screen.Home.route)
                                                    Toast.makeText(
                                                        context,
                                                        "ثبت نوبت انجام شد...",
                                                        Toast.LENGTH_SHORT)
                                                        .show()
                                                }

                                                "no" -> {
                                                    Toast.makeText(
                                                        context,
                                                        "شخص دیگری ثبت نموده. زمان دیگری را نتخاب کنید",
                                                        Toast.LENGTH_SHORT)
                                                        .show()
                                                }

                                                "error" -> {
                                                    Toast.makeText(
                                                        context,
                                                        "خطا در ثبت نوبت!!",
                                                        Toast.LENGTH_SHORT)
                                                        .show()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }, modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "ثبت نوبت", style = MaterialTheme.typography.h2)
                    }
                }
            }
        }
    }
}