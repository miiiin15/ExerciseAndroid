package com.miiiin15.feature.like.presentation

import com.miiiin15.base.local.pref.PrefUtil
import com.miiiin15.base.presentation.viewModel.BaseViewModel
import com.miiiin15.feature.home.data.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val prefUtil: PrefUtil
) : BaseViewModel<LikeIntent, LikeViewState, LikeSingleEvent>(
    LikeViewState.initial()
) {

    override fun processIntent(intent: LikeIntent) {
        when (intent) {
            is LikeIntent.Init -> {
                _viewState.value = _viewState.value.copy(
                    likedList = prefUtil.likedLocalList.orEmpty().map { it.toDomain() }
                )
            }

            is LikeIntent.DeleteLike -> {
                val updateList = _viewState.value.likedList.filterNot { it.mapx == intent.mapx }
                prefUtil.removeLikedLocalItem(intent.mapx)
                _viewState.value = _viewState.value.copy(likedList = updateList)
            }
        }
    }

}