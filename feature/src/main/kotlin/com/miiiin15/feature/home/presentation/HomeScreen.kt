package com.miiiin15.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.miiiin15.feature.home.domain.model.LocalItem
import com.miiiin15.feature.home.presentation.component.LocalLazyGrid


@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onDetailClick: () -> Unit,
    onClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val scrollState = rememberLazyGridState()

    LaunchedEffect(scrollState) {
        snapshotFlow {scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index}
            .collect{
                if(it !=null && it >= viewState.value.result.size -2 && !viewState.value.isLoading){
                    viewModel.processIntent(HomeIntent.LoadNext)
                }
            }
    }

    HomeContent(
        scrollState =scrollState,
        keyword = viewState.value.keyword,
        isEmpty = viewState.value.isEmpty,
        result = viewState.value.result,
        onKeywordChange = { viewModel.processIntent(HomeIntent.Editing(it)) },
        onSearchClick = { viewModel.processIntent(HomeIntent.Search) },
        onLikedButtonClick = { newState, item ->
            viewModel.processIntent(
                HomeIntent.Like(
                    newState,
                    item!!
                )
            )
        }
    )
}

@Composable
fun HomeContent(
    scrollState: LazyGridState,
    keyword: String,
    isEmpty: Boolean,
    result: List<LocalItem>,
    onKeywordChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onLikedButtonClick: (Boolean, LocalItem?) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = keyword,
                onValueChange = onKeywordChange,
                placeholder = { Text("검색어를 입력하세요") },
            )
            Button(onClick = onSearchClick) { Text("검색") }
        }
        if (isEmpty) {
            Text("검색어 없음")
        } else {
            LocalLazyGrid(
                localItem = result,
                onLikedButtonClick = onLikedButtonClick,
                scrollState = scrollState
            )
        }

    }
}