package ir.shobeir.baladomapp.ui.utils

import androidx.compose.ui.graphics.Color

data class MeditationType(
    val id:Int,
    val teacher: String,
    val title: String,
    val description: String,
    val background: Int,
    val contentColor: Color,
    val timeBackgroundColor: Color
)
