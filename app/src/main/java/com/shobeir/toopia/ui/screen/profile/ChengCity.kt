package com.shobeir.toopia.ui.screen.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.data.model.City
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.screen.login.HomeScreenState
import com.shobeir.toopia.ui.screen.shop.ShopViewModel
import com.shobeir.toopia.ui.theme.OrangeYellow1
import com.shobeir.toopia.ui.theme.md_primaryContainer
import com.shobeir.toopia.ui.theme.roundedShape
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ChengCity(
    navController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel(),
    storeViewModel: StoreViewModel = hiltViewModel()
) {
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
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(3)) {
        items(cityList) {
            Button(onClick = {
                storeViewModel.saveCity(it.name)
                navController.popBackStack()
            }, modifier = Modifier
                .width(80.dp)
                .padding(horizontal = 5.dp),
                shape = MaterialTheme.roundedShape.small,
                colors = ButtonDefaults.buttonColors(backgroundColor = md_primaryContainer)
            ) {
                Text(text = it.name, fontFamily = shabnam, fontSize = 16.sp)
            }
        }
    }
}