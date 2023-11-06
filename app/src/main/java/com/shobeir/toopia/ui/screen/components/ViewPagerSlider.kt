package com.shobeir.toopia.ui.screen.components


import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.*
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.theme.font_standard
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@SuppressLint("SuspiciousIndentation")
@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(
    images:List<Slider>,
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
)
{
    val pageCount = images.size
    val pagerState = rememberPagerState(pageCount)

      LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(3000)
            tween<Float>(600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        } }
    Column {
        HorizontalPager(state = pagerState, count = pageCount) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f, stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f, stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
            ) {
                val newKids = images[page]
                Banner(newKids=newKids,navController=navController, sharedViewModel = sharedViewModel)
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState, spacing = 4.dp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            activeColor = Color(0xFF26625E),
            inactiveColor = Color(0xFFBFE6E3)
        ) } }


@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Composable
fun Banner(newKids: Slider,navController: NavHostController,sharedViewModel: SharedViewModel) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clickable {
                        navController.navigate(Screen.WebView.route+ "?url=${newKids.url}")
                    }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(newKids.image)
                        .crossfade(true)
                        .build(),
                    contentDescription ="banner",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
}

