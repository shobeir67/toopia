package com.shobeir.toopia.ui.screen.news

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.screen.components.TopSliderSection
import com.shobeir.toopia.viewmodel.HomeViewModel
import timber.log.Timber


@Composable
fun NewsScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: HomeViewModel
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
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