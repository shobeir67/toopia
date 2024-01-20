package com.shobeir.toopia.ui.screen.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.shobeir.toopia.R
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.datastore.PreferenceHelper
import com.shobeir.toopia.data.datastore.StoreViewModel
import com.shobeir.toopia.data.model.Store
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.screen.shop.ShopViewModel
import com.shobeir.toopia.ui.theme.shabnam
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoreScreen(
    navController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    storeViewModel: StoreViewModel= hiltViewModel()
    ) {
    val context =LocalContext.current

    var phoneUser by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        storeViewModel.readString(PreferenceHelper.MOBILE_NAME).collectLatest {
            phoneUser = it
        }

    }
    var store by remember {
        mutableStateOf<Store?>(null)
    }
    var loading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit){
        delay(400)
        viewModel.getStoreUser(phoneUser)
        viewModel.storeUser.collectLatest {
            when(it){
                is NetworkResult.Success -> {
                    store = it.data
                    loading = false
                }
                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "CartAddressSection error : ${it.message}")
                }
                is NetworkResult.Loading -> {
                    loading = true
                }

            }
        }
    }
    if (loading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }else{
        Column(modifier = Modifier.fillMaxSize()) {
            //TopBar(name = "shobeir_shahriari", modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(4.dp))
            store?.let { ProfileSection(store = it) }
            Spacer(modifier = Modifier.height(15.dp))
            store?.let { ButtonSection(modifier = Modifier.fillMaxWidth(), store = it,
                sharedViewModel=sharedViewModel)
            }
            Spacer(modifier = Modifier.height(10.dp))
            PostSection()
        }
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.shop),
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.trolle),
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }

}

@Composable
fun ProfileSection(modifier: Modifier = Modifier,store: Store) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            RounImage(
                image = rememberAsyncImagePainter(model = store.image),
                modifier = Modifier
                    .size(80.dp)

            )
            ProfileDescription(
                displayName = store.title,
                description = store.tozihat,
                address = store.city,
                followedBy = listOf("shabir", "vahid"),
                otherCount = 17
            )
        }

    }
}

@Composable
fun RounImage(
    image: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
            .padding(3.dp)
            .clip(CircleShape),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun StateSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileState(numberText = "", text = "")
        ProfileState(numberText = "", text = "")
        ProfileState(numberText = "", text = "")
    }
}

@Composable
fun ProfileState(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp, fontFamily = shabnam
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, fontFamily = shabnam)
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    address: String,
    followedBy: List<String>,
    otherCount: Int
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            fontFamily = shabnam,
            fontSize = 16.sp
        )
        Text(
            text = description,
            letterSpacing = letterSpacing,
            fontFamily = shabnam,
            fontSize = 14.sp
        )

    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    store: Store,
    sharedViewModel: SharedViewModel,

    ) {
    val minWidth = 95.dp
    val height = 30.dp
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    )
    {
        ActionButton(
            text = "ویرایش اطلاعات",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height),
            icon = Icons.Default.Edit
        ) {

        }
        ActionButton(
            text = "افزودن محصول",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height),
            icon = Icons.Default.Add
        ) {

        }

        ActionButton(
            text = "حذف غرفه",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height),
            icon = Icons.Default.Delete
        ) {

        }

    }

}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    clickItem: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
            .clickable { clickItem() }

    ) {
        if (icon != null) {
            Icon(
                imageVector = icon, contentDescription = null,
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        if (text != null) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontFamily = shabnam,
                fontSize = 14.sp
            )
        }

    }
}


@ExperimentalFoundationApi
@Composable
fun PostSection(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        GridCells.Fixed(3)
    ) {
        items(10) {
            Image(
                painter = painterResource(id = R.drawable.king_kohli), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}
