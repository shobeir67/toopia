package com.shobeir.toopia.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.ui.theme.md_theme_light_outline
import com.shobeir.toopia.ui.theme.md_theme_light_tertiary
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.viewmodel.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    storeViewModel: StoreViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val code = sharedViewModel.user?.code
    val phone = sharedViewModel.user?.phone
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
        modifier = Modifier.fillMaxSize().background(md_theme_light_tertiary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

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
            onValueChange = {textCode = it},
        )


      Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
      MyButton(text = stringResource(id = R.string.digikala_login)) {
                if (textCode == code) {
                        loginViewModel.addUser(phone!!)
                        storeViewModel.savePhone(phone=phone)
                        loginViewModel.screenState = HomeScreenState.HOME_STATE

                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.password_format_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
       }
        if (timeLeft == 0){
            Text(
                text = "ارسال مجدد",
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.semiLarge
                ),
                fontFamily = shabnam,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }else{
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

