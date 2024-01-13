package com.shobeir.toopia.ui.screen.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.ui.theme.shabnam
import com.shobeir.toopia.utils.Constants

@Composable
fun NewsItem(
    news: News,
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(120.dp)
            .padding(5.dp)
            .clickable {
                 sharedViewModel.addNews(news)
                 navController.navigate(Screen.DetailsNews.route)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier
            .fillMaxWidth(0.7f)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = news.title,
                fontFamily = shabnam,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(painter = painterResource(id = R.drawable.calendar), contentDescription ="date", tint = Color.Gray)
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Text(
                        text = news.created_time,
                        fontFamily = shabnam,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

        }
        if (news.image.isEmpty()){
            Image(painter = painterResource(R.drawable.ic_baseline_photo_24),
                contentDescription = "slider",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(color = Color.Gray)
            )
        }else{
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(if (MaterialTheme.colors.isLight) R.drawable.loading_light else R.drawable.loading_dark)
                    .data(Constants.BASE_URL +news.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10.dp)),

                )
        }
    }
}