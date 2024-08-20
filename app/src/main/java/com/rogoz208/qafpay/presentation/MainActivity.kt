package com.rogoz208.qafpay.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rogoz208.qafpay.presentation.screen_auth.AuthScreen
import com.rogoz208.qafpay.presentation.screen_main_bottom_nav.MainBottomNavigationScreen
import com.rogoz208.qafpay.presentation.ui.theme.QafPayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            QafPayTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.AuthScreen
                ) {
                    composable<Screen.AuthScreen> {
                        AuthScreen(navController = navController)
                    }
                    composable<Screen.MainBottomNavigationScreen> {
                        MainBottomNavigationScreen(navController = navController)
                    }
                }
            }
        }
    }
}