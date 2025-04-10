package com.miiiin15.feature.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onDetailClick: () -> Unit,
    onClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val singleEvent = viewModel.singleEvent.collectAsState(initial = null)

    singleEvent.value?.let { event ->
        when (event) {
            is HomeSingleEvent.ShowToast -> {
                println("❌ event.message: ${event.message}")
            }
        }
    }

    Column {
        TextField(
            value = viewState.value.keyword,
            onValueChange = { keyword ->
                viewModel.processIntent(HomeIntent.Editing(keyword))
            },
            label = { Text("검색어 입력") }
        )
        Text(viewState.value.result.joinToString(", ") { it.title }.ifEmpty { "검색어 없음" })
        Button(onClick = onDetailClick) {
            Text("상세로 이동")
        }
        Button(onClick = {
            viewModel.processIntent(HomeIntent.Search)
        }) {
            Text("검색")
        }
    }
}