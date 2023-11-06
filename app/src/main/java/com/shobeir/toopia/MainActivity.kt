package com.shobeir.toopia

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shobeir.toopia.cheknet.NetworkConnection
import com.shobeir.toopia.navigation.SetupNavGraph
import com.shobeir.toopia.ui.screen.components.AnimatedCounter
import com.shobeir.toopia.ui.screen.home.HomeScreen
import com.shobeir.toopia.ui.theme.ToopiaTheme
import com.shobeir.toopia.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashViewModel.isLoading.value
            }
        }
        setContent {
            navController = rememberNavController()
            ToopiaTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
//                val networkConnection= NetworkConnection(applicationContext)
//                    networkConnection.observe(this) { isConnected ->
//                        if (isConnected) {
//                            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
//
//                        } else {
//                            Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show()
//                        }
//                    }
                SetupNavGraph(navController = navController)
               }
            }

        }
    }
}

