package com.shobeir.toopia.ui.screen.karbordi.nobateman

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shobeir.toopia.R
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.theme.digikalaRed
import com.shobeir.toopia.ui.theme.roundedShape
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.utils.DigitHelper.digitBySeparator
import com.shobeir.toopia.utils.ZarinpalPurchase

@Composable
fun ConfirmPurchaseScreen(
    navController: NavController,
    phone: String,
    viewModel: ViewModelNobat= hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as Activity

    var orderState by remember { mutableStateOf(context.getString(R.string.waiting_for_purchase)) }
    var transaction by remember { mutableStateOf("") }

    LaunchedEffect(true ){
        ZarinpalPurchase.purchase(
            activity,
            1000,//orderPrice.toLong(),
            "نوبت دهی اینترنتی"
        ) { transactionID ->
            viewModel.setPriceUserNobat(phone)
            orderState = context.getString(R.string.purchase_is_ok)
            transaction = transactionID

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                MaterialTheme.spacing.medium,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.final_price),
                fontFamily = shabnam,
                fontSize = 16.sp,
            )

            Text(
                text = "${digitBySeparator("50000")} تومان ",
                fontFamily = shabnam,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                fontFamily = shabnam,
                fontSize = 16.sp,
            )

            Text(
                text = orderState,
                fontFamily = shabnam,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Button(
            onClick = {
                navController.navigate(Screen.NobateMan.route){
                    popUpTo(Screen.NobateMan.route) {
                        inclusive = true
                    }
                }      
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.digikalaRed),
            shape = MaterialTheme.roundedShape.small,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                    ),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colors.digikalaRed,
                fontFamily = shabnam,
                fontWeight = FontWeight.Bold,
            )
        }

    }


}