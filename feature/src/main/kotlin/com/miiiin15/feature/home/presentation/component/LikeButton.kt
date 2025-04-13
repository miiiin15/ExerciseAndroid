package com.miiiin15.feature.home.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import com.miiiin15.feature.home.domain.model.LocalItem

@Composable
fun LikeButton(
    isLikedInitial: Boolean = false,
) {
    var isLiked by remember { mutableStateOf(isLikedInitial) }

    IconButton(onClick = {
        isLiked = !isLiked
    }) {
        Icon(
            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (isLiked) "좋아요 취소" else "좋아요",
            tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}