package com.shobeir.toopia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.shobeir.toopia.ui.screen.home.HomeScreen
import com.shobeir.toopia.ui.theme.ToopiaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToopiaTheme {
                HomeScreen()
            }
        }
    }
}

