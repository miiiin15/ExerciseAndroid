package com.miiiin15.feature.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.miiiin15.feature.home.navigation.homeNavGraph
import com.miiiin15.feature.main.navigation.MainNavigator
import com.miiiin15.feature.main.navigation.rememberMainNavigator

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        content = { innerPadding ->
            Box (
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ){
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination,
                ) {
                    homeNavGraph(
                        padding = innerPadding,
                        onDetailClick = { navigator.navigateToDetail() },
                        onBackClick = { navigator.popBackStack() }
                    )
                }
            }
        }
    )
}