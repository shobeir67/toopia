package com.shobeir.toopia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shobeir.toopia.navigation.BottomNavigationBar
import com.shobeir.toopia.navigation.Screen
import com.shobeir.toopia.navigation.SetupNavGraph
import com.shobeir.toopia.ui.theme.ToopiaTheme
import com.shobeir.toopia.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.pushpole.sdk.PushPole
import com.shobeir.toopia.ui.screen.home.CityScreen
import com.shobeir.toopia.ui.screen.profile.AddStore

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val splashViewModel: SplashViewModel by viewModels()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        PushPole.initialize(this,true)
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
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                onItemClick ={navController.navigate(it.route){
                                    popUpTo(Screen.Home.route){
                                        inclusive=false
                                    }
                                }
                                }
                            )
                        }
                    ){
                       SetupNavGraph(navController = navController)
                    }
               }
            }
        }
    }
}

