package com.shobeir.toopia.ui.screen.shop


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shobeir.toopia.ui.theme.roundedShape
import com.shobeir.toopia.ui.theme.spacing
import com.shobeir.toopia.data.model.Shoping
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.theme.shabnam
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ShopScreen(
    viewModel: ShopViewModel = hiltViewModel()
) {
    var shopingList by remember {
        mutableStateOf<List<Shoping>>(emptyList())
    }
    val scope = rememberCoroutineScope()
    var error by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        viewModel.getAllData()
        viewModel.shoping.collectLatest { shopingResult ->
            when (shopingResult) {
                is NetworkResult.Success -> {
                    shopingList = shopingResult.data ?: emptyList()
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
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
        items(shopingList) {
            CardCustom(it)
        }
    }

}

@Composable
fun CardCustom(shoping: Shoping) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(
                horizontal = MaterialTheme.spacing.semiSmall
            )
            .clickable {
            },
        shape = MaterialTheme.roundedShape.small,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.extraSmall)
        ) {
            AsyncImage(
                model = shoping.image, contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = shoping.title,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.small),
                fontFamily = shabnam,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
        }
    }
}