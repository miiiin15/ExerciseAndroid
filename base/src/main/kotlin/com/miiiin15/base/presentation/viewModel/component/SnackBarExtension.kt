package com.miiiin15.base.presentation.viewModel.component

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalSnackBarState = compositionLocalOf<SnackbarHostState> { error("스넥바 제공 안됨") }

@Composable
fun ProvideSnaBarHostState(
    snackBarHostState: SnackbarHostState,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSnackBarState provides snackBarHostState,
        content = content
    )
}
