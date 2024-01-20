package com.shobeir.toopia.ui.screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobeir.toopia.ui.theme.shabnam

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

