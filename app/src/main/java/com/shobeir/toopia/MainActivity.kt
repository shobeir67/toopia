package com.shobeir.toopia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shobeir.toopia.navigation.SetupNavGraph
import com.shobeir.toopia.ui.screen.home.HomeScreen
import com.shobeir.toopia.ui.theme.ToopiaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            ToopiaTheme {
                SetupNavGraph(navController = navController)
            }
        }
    }
}

