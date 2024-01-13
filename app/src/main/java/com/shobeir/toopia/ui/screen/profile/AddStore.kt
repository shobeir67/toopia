package com.shobeir.toopia.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobeir.toopia.ui.screen.login.MyEditText
import com.shobeir.toopia.ui.theme.CursorColor
import com.shobeir.toopia.ui.theme.DarkCyan
import com.shobeir.toopia.ui.theme.searchBarBg
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing

@Composable
fun AddStore() {
    var name by remember{
        mutableStateOf("")
    }
    var titelStore by remember{
        mutableStateOf("")
    }
    var address by remember{
        mutableStateOf("")
    }
    var city by remember{
        mutableStateOf("")
    }
    var category by remember{
        mutableStateOf("")
    }
    val coffeeDrinks = arrayOf("کریان", "راونگ", "کهوردان", "گوجگ", "زرتوجی")
    val categoryList = arrayOf("کریان", "راونگ", "کهوردان", "گوجگ", "زرتوجی")
    Column(modifier = Modifier.fillMaxSize()) {
        DropdownMenuBox(coffeeDrinks, label = "بالاشهر"){
            city = it
        }
        MyEditText(value = titelStore, placeholder ="نام غرفه" , onValueChange ={
            titelStore=it
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        DropdownMenuBox(categoryList, label ="دسته بندی"){
            category=it
        }

        MyEditText(value = name, placeholder = "نام و نام خانوادگی", onValueChange = {name=it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        MyEditText(value = address, placeholder = "آدرس", onValueChange = {address=it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(
    listItem:Array<String>,
    label:String,
    selectedItem:(String)->Unit
) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(label) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding( start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge,
                top = MaterialTheme.spacing.biggerSmall,
                bottom = MaterialTheme.spacing.semiLarge)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.searchBarBg),
                textStyle = LocalTextStyle.current.copy(fontFamily = shabnam,
                    fontSize = 18.sp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listItem.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item,fontFamily = shabnam, fontSize = 18.sp) },
                        onClick = {
                            selectedText = item
                            selectedItem(item)
                            expanded = false
                        },
                        modifier=Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}