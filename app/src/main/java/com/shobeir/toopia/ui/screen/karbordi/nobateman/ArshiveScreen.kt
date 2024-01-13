package com.shobeir.toopia.ui.screen.karbordi.nobateman

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shobeir.toopia.SharedViewModel
import com.shobeir.toopia.data.model.nobatdehi.Nobat
import com.shobeir.toopia.ui.theme.grayCategory
import com.shobeir.toopia.ui.theme.shabnam
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ArshivScreen(
    phone:String,
    viewModel: SharedViewModel,
    viewModelSanse: ViewModelNobat = hiltViewModel()
) {

    var listNobat by remember {
        mutableStateOf(emptyList<Nobat>())
    }

    var loading by remember {
        mutableStateOf(false)
    }
    val textNew = remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = Unit) {
        loading = true
        viewModelSanse.getSanse()
        viewModelSanse.listSanse.collectLatest { list ->
            list?.let {
                listNobat = it.nobat
            }
        }
        delay(500)
        textNew.value = "برای این هفته نوبتی ثبت نکرده اید..."
    }
    val nodatNew = mutableListOf<Nobat>()
    listNobat.forEach {
        if (it.mobile == phone) {
           nodatNew.addAll(listOf(it))
        }
    }
    Column(modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        if (nodatNew.isEmpty()) {
            Text(
                text = textNew.value,
                fontFamily = shabnam,
                color = MaterialTheme.colors.grayCategory
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(nodatNew.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(10.dp)

                    ) {
                        Column(modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = nodatNew[index].zaman, fontFamily = shabnam)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = nodatNew[index].tarikh, fontFamily = shabnam)
                        }
                    }
                }
            }
        }
    }
}