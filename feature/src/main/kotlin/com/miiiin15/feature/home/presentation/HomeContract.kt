package com.miiiin15.feature.home.presentation

import com.miiiin15.base.presentation.viewModel.Intent
import com.miiiin15.base.presentation.viewModel.SingleEvent
import com.miiiin15.base.presentation.viewModel.ViewState
import com.miiiin15.feature.home.domain.model.LocalItem

interface HomeIntent : Intent {
    data object Initial : HomeIntent
    data class Editing(val keyword: String) : HomeIntent
    data object Search : HomeIntent
    data class Like(val newState: Boolean, val item: LocalItem) : HomeIntent
}

data class HomeViewState(
    val keyword: String,
    val isLoading: Boolean,
    val result: List<LocalItem>
) : ViewState {
    val isEmpty: Boolean get() = result.isEmpty() && !isLoading

    companion object {
        fun Initial() = HomeViewState(
            keyword = "",
            isLoading = false,
            result = emptyList()
        )
    }
}

sealed interface HomeSingleEvent : SingleEvent {
    data class ShowToast(val message: String) : HomeSingleEvent
}

