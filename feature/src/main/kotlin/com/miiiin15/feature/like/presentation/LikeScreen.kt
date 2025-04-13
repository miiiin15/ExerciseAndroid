package com.miiiin15.feature.like.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.miiiin15.feature.home.domain.model.LocalItem
import com.miiiin15.feature.home.presentation.component.LocalLazyGrid

@Composable
fun LikeScreen(
    onBackClick: () -> Unit,
    viewModel: LikeViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.processIntent(LikeIntent.Init)
    }
    LikeContent(
        likedList = viewState.value.likedList,
        isEmpty = viewState.value.isEmpty,
        onDeleteLike = {viewModel.processIntent(LikeIntent.DeleteLike(it))}
    )
}

@Composable
fun LikeContent(
    likedList: List<LocalItem>,
    isEmpty: Boolean,
    onDeleteLike: (String) -> Unit
){
    if(isEmpty){
        Text(text = "좋아요한 아이템이 없습니다.")
    } else {
        LocalLazyGrid(
            localItem = likedList,
            onLikedButtonClick = { newState, item ->
                    onDeleteLike(item!!.mapx)
            }
        )
    }
}