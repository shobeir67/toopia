package com.shobeir.toopia.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.UserRegister
import com.shobeir.toopia.ui.theme.md_theme_light_outline
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.utils.InputValidation.isValidPhoneNumber
import com.shobeir.toopia.viewmodel.LoginViewModel
import ir.truelearn.digikala.ui.screens.profile.MyButton
import ir.truelearn.digikala.ui.screens.profile.MyEditText
import kotlin.random.Random


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    loginViewModel.codeState = random().toString()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        item {
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.digi_smile),
                contentDescription = "",
            )
        }
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        item {
            Text(
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.semiLarge
                ),
                style = MaterialTheme.typography.h6,
                text = stringResource(id = R.string.loginTxt),
                color = md_theme_light_outline,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            MyEditText(
                value = loginViewModel.inputPhoneState,
                placeholder = stringResource(id = R.string.phone_and_email),
                onValueChange = {
                    loginViewModel.inputPhoneState = it
                }
            )
        }
        item {
            MyButton(text = stringResource(id = R.string.digikala_entry)) {
                if (isValidPhoneNumber(loginViewModel.inputPhoneState)
                ) {
                    loginViewModel.sendSms()
                    sharedViewModel.addUserReg(UserRegister(phone = loginViewModel.inputPhoneState, code = random().toString()))
                    loginViewModel.screenState = ProfileScreenState.REGISTER_STATE
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.login_error),
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        }

    }
}

fun random(): Int {
    val min = 10000
    val max = 99999
    val r = Random
    return r.nextInt(max - min + 1) + min
}