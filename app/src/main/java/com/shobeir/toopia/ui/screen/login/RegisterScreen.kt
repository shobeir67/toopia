package com.shobeir.toopia.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.ui.theme.OrangeYellow1
import com.shobeir.toopia.ui.theme.md_theme_light_scrim
import com.shobeir.toopia.ui.theme.roundedShape
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    storeViewModel: StoreViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val code = sharedViewModel.userReg?.code
    val phone = sharedViewModel.userReg?.phone
    var textCode by remember {
        mutableStateOf("")
    }
    var timeLeft by remember {
        mutableIntStateOf(59)
    }
    LaunchedEffect(key1 = Unit) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(md_theme_light_scrim),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_sms_24),
            contentDescription = "exit",

            )
        Text(
            text = stringResource(id = R.string.set_code),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.semiLarge
            ),
            fontFamily = shabnam,
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        MyEditText(
            value = textCode,
            placeholder = stringResource(id = R.string.code),
            onValueChange = { textCode = it },
        )


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        MyButton(text = stringResource(id = R.string.digikala_login)) {
            if (textCode == code) {
                loginViewModel.addUser(phone!!)
                sharedViewModel.addCode(phone)
                loginViewModel.screenState = HomeScreenState.CITY_STATE

            } else {
                Toast.makeText(
                    context,
                    context.resources.getText(R.string.password_format_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        if (timeLeft == 0) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = {
                        loginViewModel.sendSms()
                        timeLeft = 59
                        scope.launch {
                            while (timeLeft > 0) {
                                delay(1000L)
                                timeLeft--
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = OrangeYellow1),
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
                    shape = MaterialTheme.roundedShape.small
                ) {
                    Text(
                        text = "ارسال مجدد",
                        fontFamily = shabnam,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = { loginViewModel.screenState = HomeScreenState.LOGIN_STATE},
                    colors = ButtonDefaults.buttonColors(backgroundColor = OrangeYellow1),
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
                    shape = MaterialTheme.roundedShape.small
                ) {
                    Text(
                        text = "ویرایش شماره",
                        fontFamily = shabnam,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            Text(
                text = "۰۰:$timeLeft",
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.semiLarge
                ),
                fontFamily = shabnam,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




