package com.miiiin15.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miiiin15.feature.home.navigation.HomeRoute

class MainNavigator(val navController: NavHostController) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = HomeRoute.route

    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigateToDetail() {
        navController.navigate(HomeRoute.detail)
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}