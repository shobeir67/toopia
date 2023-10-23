package com.shobeir.toopia.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)


data class RoundedShape(
    val extraSmall : RoundedCornerShape = RoundedCornerShape(4.dp),
    val semiSmall : RoundedCornerShape = RoundedCornerShape(6.dp),
    val small : RoundedCornerShape = RoundedCornerShape(8.dp),
    val biggerSmall : RoundedCornerShape = RoundedCornerShape(10.dp),
    val semiMedium : RoundedCornerShape = RoundedCornerShape(14.dp),
    val medium : RoundedCornerShape = RoundedCornerShape(16.dp),
    val biggerMedium : RoundedCornerShape = RoundedCornerShape(18.dp),
    val large : RoundedCornerShape = RoundedCornerShape(24.dp),
)

val LocalShape = compositionLocalOf { RoundedShape() }

val MaterialTheme.roundedShape: RoundedShape
    @Composable
    @ReadOnlyComposable
    get() = LocalShape.current