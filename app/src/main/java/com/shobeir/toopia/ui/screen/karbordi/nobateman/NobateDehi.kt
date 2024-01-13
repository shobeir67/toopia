package com.shobeir.toopia.ui.screen.karbordi.nobateman

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.theme.grayCategory
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.viewmodel.PishViewModel
import com.shobeir.toopia.ui.screen.components.CircularIconBox
import com.shobeir.toopia.ui.theme.TextWhite
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NobateDehi(
    navController: NavHostController,
    storeViewModel: StoreViewModel= hiltViewModel(),
    viewModel: PishViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    var loading by remember {
        mutableStateOf(false)
    }

    var phoneUser by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {
            phoneUser = it
        }

    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    var user by remember {
        mutableStateOf<User?>(null)
    }
    var error by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        delay(200)
        loading=true
        viewModel.getUser(phoneUser)
        viewModel.user.collectLatest {
            user = it.data
        }

    }
    scope.launch {
        delay(400)
        loading = false
    }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetContent = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }
                .padding(10.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "راهنمای ثبت نوبت",
                    fontFamily = shabnam,
                    fontSize = 20.sp
                )
                Text(
                    text = stringResource(R.string.rahnema),
                    fontFamily = shabnam, fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = "طراح و برنامه نویس: شبیرشهریاری",
                    fontFamily = shabnam,
                    fontSize = 12.sp
                )
            }
        },
        sheetState = bottomSheetState
    ) {

        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                CircularProgressIndicator()
            }
        }
        user?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                if (dialogOpen) {
                    AlertDialog(
                        onDismissRequest = {
                            dialogOpen = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    scope.launch {

                                    }
                                    dialogOpen = false
                                }
                            ) {
                                Text(text = "تایید", fontFamily = shabnam)
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    // close the dialog
                                    dialogOpen = false
                                }
                            ) {
                                Text(text = "انصراف", fontFamily = shabnam)
                            }
                        },
                        title = {
                            Text(text = "بله", fontFamily = shabnam)
                        },
                        text = {
                            Text(
                                text = "آیا می خواهید از حساب خود خارج شوید؟",
                                fontFamily = shabnam
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        shape = RoundedCornerShape(5.dp),
                        backgroundColor = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontFamily = shabnam,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("موجودی: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontFamily = shabnam,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("${user!!.price} تومان")
                        }
                    })

                    Button(
                        onClick = {
                            scope.launch {

                            }
                        }
                    ) {
                        Text(
                            text = "به روزسانی موجودی",
                            fontSize = 14.sp,
                            color = TextWhite,
                            fontFamily = shabnam
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                val uriHandler = LocalUriHandler.current
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    CircularIconBox(
                        image = painterResource(id = R.drawable.guide),
                        title = "راهنما",
                        onItemClick = {
                            scope.launch {
                                bottomSheetState.show()
                            }
                        }
                    )
                    CircularIconBox(
                        image = painterResource(id = R.drawable.money),
                        title = "افزایش موجودی",
                        onItemClick = {
                            if (user!!.price == "50000") {
                                Toast.makeText(
                                    context,
                                    "موجودی شما کافیست",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                val url ="https://baladom.ir/toopia/zarrinpal.php?mobile=${phoneUser}"
                                navController.navigate(route = Screen.WebView.route + "?url=${url}")
                               // navController.navigate(route = Screen.ConfirmPurchase.route + "?phone=${phoneUser}")
                            }

                        }
                    )
                    CircularIconBox(
                        image = painterResource(id = R.drawable.manager),
                        title = "مدیریت",
                        onItemClick = {
                            if (user!!.status == "0") {
                                Toast.makeText(
                                    context,
                                    "امکان دسترسی به این بخش را ندارید",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                navController.navigate(Screen.Panel.route)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(modifier = Modifier.size(160.dp, 45.dp),
                        onClick = {
                            sharedViewModel.addUser(user!!)
                            navController.navigate(Screen.Salon.route) {
                                popUpTo(Screen.NobateMan.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }) {
                        Text(
                            text = "سالن شهداء",
                            fontFamily = shabnam,
                            color = TextWhite
                        )
                    }
                    Button(modifier = Modifier.size(160.dp, 45.dp),
                        onClick = {
                            sharedViewModel.addUser(user!!)
                            navController.navigate(Screen.Chaman.route) {
                                popUpTo(Screen.NobateMan.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }) {
                        Text(
                            text = "چمن مصنوعی",
                            fontFamily = shabnam,
                            color = TextWhite
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
                Text(
                    text = "نوبت های من",
                    fontFamily = shabnam,
                    fontSize = 16.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(5.dp))
                ArshivScreen(viewModel = sharedViewModel, phone = phoneUser)
            }
        }
    }
}


