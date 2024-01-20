package com.shobeir.toopia.ui.screen.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.screen.components.ActionButton
import com.shobeir.toopia.ui.screen.components.TopSliderSection
import com.shobeir.toopia.ui.screen.home.CityScreen
import com.shobeir.toopia.ui.theme.OrangeYellow1
import com.shobeir.toopia.ui.theme.md_primaryContainer
import com.shobeir.toopia.ui.theme.roundedShape
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


@Composable
fun NewsScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: HomeViewModel,
    storeViewModel:StoreViewModel
) {
    var newsList by remember {
        mutableStateOf<List<News>>(emptyList())
    }
    val context = LocalContext.current
    var loading by remember {
        mutableStateOf(false)
    }
    var error by remember {
        mutableStateOf(false)
    }
    var city by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        storeViewModel.readString(PreferenceHelper.CITY).collectLatest {
            city = it
        }
    }

    val newsResult by viewModel.news.collectAsState()
    when (newsResult) {
        is NetworkResult.Success -> {
            newsList = newsResult.data ?: emptyList()
            loading = false
            error=false
        }
        is NetworkResult.Error -> {
            loading = false
            error=true
            Timber.tag("3636").e("Top Slider error : %s", newsResult.message)
        }
        is NetworkResult.Loading -> {
            loading = true
            error=false
        }

    }
    if (city =="null"){
        CityScreen(navController = navController, sharedViewModel = sharedViewModel)
    }else{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            item {
                ActionButton(
                    text = city,
                    modifier = Modifier
                        .padding(8.dp)
                        .defaultMinSize(minWidth = 95.dp)
                        .height(30.dp),
                    icon = Icons.Default.LocationOn
                ) {
                    navController.navigate(Screen.ChangeCity.route)
                }
            }
            item {
                TopSliderSection(navController = navController,
                    sharedViewModel = sharedViewModel)
            }

            items(newsList.size) { index ->
                NewsItem(
                    news = newsList[index],
                    navController = navController,
                    sharedViewModel = sharedViewModel,
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
            }

        }
    }

}