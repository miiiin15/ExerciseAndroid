package com.miiiin15.feature.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.miiiin15.base.presentation.viewModel.component.ProvideSnaBarHostState
import com.miiiin15.feature.home.navigation.homeNavGraph
import com.miiiin15.feature.like.navigation.likeNavGraph
import com.miiiin15.feature.main.navigation.MainNavigator
import com.miiiin15.feature.main.navigation.rememberMainNavigator

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val navController = navigator.navController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val snackBarHostState = remember { SnackbarHostState() }

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Like
    )

    Scaffold(
        content = { innerPadding ->
            ProvideSnaBarHostState(snackBarHostState = snackBarHostState) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    NavHost(
                        navController = navigator.navController,
                        startDestination = navigator.startDestination,
                    ) {
                        homeNavGraph(
                            padding = innerPadding,
                            onDetailClick = { navigator.navigateToDetail() },
                            onBackClick = { navigator.popBackStack() }
                        )
                        likeNavGraph(
                            onBackClick = { navigator.popBackStack() }
                        )
                    }
                }
            }
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}