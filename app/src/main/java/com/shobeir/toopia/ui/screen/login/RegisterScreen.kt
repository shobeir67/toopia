package com.shobeir.toopia.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.ui.theme.md_theme_light_outline
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.viewmodel.LoginViewModel

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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.set_code),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.semiLarge
            ),
            fontFamily = shabnam,
            fontSize = 20.sp,
            color = md_theme_light_outline,
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
                        storeViewModel.savePhone(phone=phone!!)
                        loginViewModel.screenState = HomeScreenState.HOME_STATE

                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.password_format_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
       }

    }

}

