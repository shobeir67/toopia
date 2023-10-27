package com.shobeir.toopia.ui.screen.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shobeir.toopia.ui.screen.components.Loading3Dots
import com.shobeir.toopia.ui.theme.digikalaRed
import com.shobeir.toopia.ui.theme.spacing

@Composable
fun LoadingButton() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(
                start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge,
                bottom = MaterialTheme.spacing.medium
            ),
        shape = MaterialTheme.shapes.medium
    ) {

        Loading3Dots(isDark = false)
    }
}