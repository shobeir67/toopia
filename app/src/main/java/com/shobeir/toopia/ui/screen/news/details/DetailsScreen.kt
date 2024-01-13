package com.shobeir.toopia.ui.screen.news.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.shobeir.toopia.SharedViewModel
import org.w3c.dom.Comment
import com.shobeir.toopia.R
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.theme.shabnam
import timber.log.Timber

@Composable
fun DetailsScreen(
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    viewModel:NewsViewModel= hiltViewModel()
) {
    val context = LocalContext.current
    val news = sharedViewModel.news
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllImageNews(news!!.code)
    }
    var imageList by remember {
        mutableStateOf<List<ImagePost>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }
    var error by remember {
        mutableStateOf(false)
    }
    val imageResult by viewModel.imageNews.collectAsState()
    when (imageResult) {
        is NetworkResult.Success -> {
            imageList = imageResult.data ?: emptyList()
            loading = false
            error=false
        }
        is NetworkResult.Error -> {
            loading = false
            error=true
            Timber.tag("3636").e("Top Slider error : %s", imageResult.message)
        }
        is NetworkResult.Loading -> {
            loading = true
            error=false
        }

    }
    val scrollState = rememberScrollState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp, bottom = 47.dp)
            .background(Color.White)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .graphicsLayer {
                        alpha = 1f - (scrollState.value.toFloat() / scrollState.maxValue)
                        translationY = 0.5f * scrollState.value
                    },
                contentAlignment = Alignment.Center
            ) {
                if (imageList.isEmpty()) {
                    Image(
                        rememberAsyncImagePainter(model =news!!.image),
                        contentDescription = "slider",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillWidth,
                        colorFilter = ColorFilter.tint(color = Color.Gray)
                    )
                } else {
                    ImageItemLayout(
                        images = imageList,
                        postBookmarked = false,
                    ){}
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Text(
                        text = news?.created_time!!,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopEnd),
                        fontFamily = shabnam,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = news?.title!!,
                    modifier = Modifier.padding(8.dp),
                    fontFamily = shabnam,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = news.description,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontFamily = shabnam,
                    fontSize = 16.sp
                )
            }

        }
    }
}

