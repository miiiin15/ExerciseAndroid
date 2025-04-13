package com.miiiin15.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.miiiin15.feature.home.presentation.LikeScreen

fun NavGraphBuilder.likeNavGraph(
    onBackClick: () -> Unit
) {
    composable(route = LikeRoute.route) {
        LikeScreen(
            onBackClick = onBackClick
        )
    }
}

object LikeRoute {
    const val route = "like"
}