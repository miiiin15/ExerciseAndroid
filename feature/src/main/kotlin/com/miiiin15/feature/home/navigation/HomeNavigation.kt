package com.miiiin15.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.miiiin15.feature.home.presentation.DetailScreen
import com.miiiin15.feature.home.presentation.HomeScreen

fun NavController.navigateToHome() {
    navigate(HomeRoute.route)
}

fun NavController.navigateToDetail() {
    navigate(HomeRoute.detail)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onDetailClick: () -> Unit,
    onBackClick:()->Unit
){
    composable(route= HomeRoute.route){
        HomeScreen(
            paddingValues = padding,
            onDetailClick = onDetailClick,
            onClick = onBackClick
        )
    }
    composable(route = HomeRoute.detail){
        DetailScreen()
    }
}

object HomeRoute{
    const val route = "home"
    const val detail = "home_detail"
}