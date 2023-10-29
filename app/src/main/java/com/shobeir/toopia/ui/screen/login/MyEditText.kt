package com.shobeir.toopia.ui.screen.login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobeir.toopia.ui.theme.CursorColor
import com.shobeir.toopia.ui.theme.DarkCyan
import com.shobeir.toopia.ui.theme.roundedShape
import com.shobeir.toopia.ui.theme.searchBarBg
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing

@Composable
fun MyEditText(
    value: String,
    placeholder:String,
    onValueChange: (it:String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = {onValueChange(it)},
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(
                start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge,
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.semiLarge
            ),
        shape = MaterialTheme.roundedShape.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.searchBarBg,
            focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
            unfocusedIndicatorColor =  MaterialTheme.colors.searchBarBg,
            cursorColor =  MaterialTheme.colors.CursorColor,
        ),
        placeholder = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Text(
                    text = placeholder,
                    fontFamily = shabnam,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

    )

}