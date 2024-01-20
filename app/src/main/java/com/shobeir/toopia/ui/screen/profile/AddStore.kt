package com.shobeir.toopia.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shobeir.toopia.R
import com.shobeir.toopia.data.model.City
import com.shobeir.toopia.ui.screen.shop.ShopViewModel
import com.shobeir.toopia.ui.theme.roundedShape
import com.shobeir.toopia.ui.theme.searchBarBg
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.ui.theme.spacing

@Composable
fun AddStore(
    viewModel: ShopViewModel= hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Text(text = stringResource(id = R.string.textstorenew),fontFamily = shabnam, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(),shape = MaterialTheme.roundedShape.small) {
            Text(text = "پرداخت", fontFamily = shabnam, fontSize = 16.sp)
        }
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