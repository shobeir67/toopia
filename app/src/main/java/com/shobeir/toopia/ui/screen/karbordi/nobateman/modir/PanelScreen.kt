package com.shobeir.toopia.ui.screen.karbordi.nobateman.modir

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shobeir.toopia.data.model.nobatdehi.Nobat
import com.shobeir.toopia.ui.screen.karbordi.nobateman.ViewModelNobat
import com.shobeir.toopia.ui.theme.grayCategory
import com.shobeir.toopia.ui.theme.shabnam
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PanelScreen(
                viewModelSanse: ViewModelNobat = hiltViewModel()) {
    var listNobat by remember {
        mutableStateOf(emptyList<Nobat>())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        loading = true
        viewModelSanse.getSanse()
        viewModelSanse.listSanse.collectLatest { list ->
            list?.let {
                listNobat = it.nobat
            }
        }
    }
    val scope = rememberCoroutineScope()
    scope.launch {
        delay(300)
        loading=false
    }
    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(listNobat.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth().background(MaterialTheme.colors.grayCategory).padding(8.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp)

                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = listNobat[index].name, fontFamily = shabnam)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = listNobat[index].zaman, fontFamily = shabnam)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = listNobat[index].mobile, fontFamily = shabnam)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = listNobat[index].tarikh, fontFamily = shabnam)
                    }
                }
            }

        }

    }
}