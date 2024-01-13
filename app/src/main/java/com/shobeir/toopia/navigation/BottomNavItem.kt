package com.shobeir.toopia.navigation


data class BottomNavItem(
    val name : String,
    val route : String,
    val selectedIcon : Int,
    val deSelectedIcon : Int
)
