package com.miiiin15.feature.like.presentation

import com.miiiin15.base.presentation.viewModel.Intent
import com.miiiin15.base.presentation.viewModel.SingleEvent
import com.miiiin15.base.presentation.viewModel.ViewState
import com.miiiin15.feature.home.domain.model.LocalItem


interface LikeIntent : Intent{
   data object Init : LikeIntent
    data class DeleteLike(val mapx:String ): LikeIntent
}


data class LikeViewState(
    val isLoading:Boolean,
    val likedList: List<LocalItem>
) : ViewState {
    val isEmpty: Boolean get() = likedList.isEmpty() && !isLoading

    companion object {
        fun initial() = LikeViewState(
            isLoading = false,
            likedList = emptyList()
        )
    }
}


sealed interface LikeSingleEvent : SingleEvent{
    data class ShowAlert(val message:String): LikeSingleEvent
}