package com.rogoz208.qafpay.presentation.bottom_nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rogoz208.qafpay.presentation.AccountsScreen
import com.rogoz208.qafpay.presentation.Screen
import com.rogoz208.qafpay.presentation.TransactionsScreen
import com.rogoz208.qafpay.presentation.screen_profile.ProfileScreen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val screen: Screen
)

@Composable
fun MainBottomNavigationScreen() {

    val items = listOf(
        BottomNavigationItem(
            title = "Accounts",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            screen = Screen.AccountsScreen
        ), BottomNavigationItem(
            title = "Transactions",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            screen = Screen.TransactionsScreen
        ), BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            screen = Screen.ProfileScreen
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.screen) {
                                launchSingleTop = true
                            }
                        },
                        label = { Text(text = item.title) },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                }, contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screen.AccountsScreen) {
            composable<Screen.AccountsScreen> {
                AccountsScreen(navController = navController)
            }
            composable<Screen.TransactionsScreen> {
                TransactionsScreen(navController = navController)
            }
            composable<Screen.ProfileScreen> {
                ProfileScreen(navController = navController)
            }
        }
    }
}