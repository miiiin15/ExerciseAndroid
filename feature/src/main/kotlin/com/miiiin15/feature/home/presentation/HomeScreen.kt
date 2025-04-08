package com.miiiin15.feature.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onDetailClick: () -> Unit,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = "홈임",
        )
        Button(onClick = onDetailClick) {
            Text("상세로 이동")
        }
    }
}