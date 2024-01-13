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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.shobeir.toopia.data.model.City
import com.shobeir.toopia.data.model.Shoping
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.screen.login.MyEditText
import com.shobeir.toopia.ui.screen.shop.ShopViewModel
import com.shobeir.toopia.ui.theme.CursorColor
import com.shobeir.toopia.ui.theme.DarkCyan
import com.shobeir.toopia.ui.theme.searchBarBg
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddStore(
    viewModel: ShopViewModel= hiltViewModel()
) {
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
    var cityList by remember {
        mutableStateOf<List<City>>(emptyList())
    }
    var error by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit){
        viewModel.getCity()
        viewModel.city.collectLatest {cityResult->
            when (cityResult) {
                is NetworkResult.Success -> {
                    cityList = cityResult.data ?: emptyList()
                    error = false
                }

                is NetworkResult.Error -> {
                    error = true

                }

                is NetworkResult.Loading -> {
                    error = false
                }

            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        DropdownMenuBox(cityList, label = "بالاشهر"){
            city = it
        }
        MyEditText(value = titelStore, placeholder ="نام غرفه" , onValueChange ={
            titelStore=it
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

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
    listItem:List<City>,
    label:String,
    selectedItem:(String)->Unit
) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(label) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge,
                top = MaterialTheme.spacing.biggerSmall,
                bottom = MaterialTheme.spacing.semiLarge
            )
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
                        text = { Text(text = item.name,fontFamily = shabnam, fontSize = 18.sp) },
                        onClick = {
                            selectedText = item.name
                            selectedItem(item.name)
                            expanded = false
                        },
                        modifier=Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}