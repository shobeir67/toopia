package com.shobeir.toopia.ui.screen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobeir.toopia.ui.theme.TextWhite
import com.shobeir.toopia.ui.theme.darkText
import com.shobeir.toopia.ui.theme.shabnam

@Composable
fun CircularIconBox(
    title: String,
    image: Painter,
    onItemClick:()->Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .clickable {
                onItemClick()
            }
    ) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)

            )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = shabnam,
            color = MaterialTheme.colors.darkText,
        )
    }

}