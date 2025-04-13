package com.miiiin15.feature.like.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.miiiin15.feature.like.presentation.LikeScreen

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