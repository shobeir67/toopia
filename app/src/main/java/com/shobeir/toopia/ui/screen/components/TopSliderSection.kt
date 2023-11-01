package com.shobeir.toopia.ui.screen.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.viewmodel.HomeViewModel


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopSliderSection(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    sharedViewModel: SharedViewModel,) {

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }
    var error by remember {
        mutableStateOf(false)
    }


    val sliderResult by viewModel.slider.collectAsState()
    when (sliderResult) {
        is NetworkResult.Success -> {
            sliderList = sliderResult.data ?: emptyList()
            loading = false
            error=false
        }
        is NetworkResult.Error -> {
            loading = false
            error=true
            Log.e("3636", "Top Slider error : ${sliderResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
            error=true
        }
    }
    if (error){
        Box(modifier = Modifier.fillMaxSize()){
            Text(text = "خطا در دریافت اطلاعات...")
        }
    }

    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(config.screenHeightDp.dp, true)
    } else {
        if (sliderList.isNotEmpty()){
            ViewPagerSlider(images = sliderList, navController = navController,
                sharedViewModel = sharedViewModel)
        }
    }
    


}