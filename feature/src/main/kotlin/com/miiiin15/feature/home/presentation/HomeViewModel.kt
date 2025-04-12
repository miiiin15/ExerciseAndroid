package com.miiiin15.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.miiiin15.base.domain.result.ResponseResult
import com.miiiin15.base.local.pref.PrefUtil
import com.miiiin15.base.presentation.viewModel.BaseViewMode
import com.miiiin15.feature.home.data.mapper.toLocal
import com.miiiin15.feature.home.domain.usecase.SearchLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchLocalUseCase: SearchLocalUseCase,
    private val prefUtil: PrefUtil
) : BaseViewMode<HomeIntent, HomeViewState, HomeSingleEvent>(HomeViewState.Initial()) {

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Initial -> {

            }

            is HomeIntent.Editing -> {
                _viewState.value = _viewState.value.copy(keyword = intent.keyword)
            }

            is HomeIntent.Search -> {
                _viewState.value = _viewState.value.copy(isLoading = true)
                searchLocal()
            }


            is HomeIntent.Like -> {
                val targetPk = intent.item.mapx
                val updateList = _viewState.value.result.map {
                    if (it.mapx == targetPk) {
                        if (intent.newState) {
                            prefUtil.addLikedLocalItem(it.toLocal())
                        } else {
                            prefUtil.removeLikedLocalItem(it.mapx)
                        }
                        it.copy(isLiked = intent.newState)
                    } else it
                }
                _viewState.value = _viewState.value.copy(result = updateList)
            }
        }
    }

    // HomeViewModel.kt
    private fun searchLocal() {
        val keyword = _viewState.value.keyword
        viewModelScope.launch {
            when (val result = searchLocalUseCase(keyword, 40)) {
                is ResponseResult.Success -> {
                    val likedList = prefUtil.likedLocalList.orEmpty().map { it.mapx }.toSet()

                    val items = result.value.map { item ->
                        item.copy(isLiked = likedList.contains(item.mapx))
                    }
                    _viewState.value = _viewState.value.copy(
                        isLoading = false,
                        result = items,
                        keyword = keyword
                    )
                }

                is ResponseResult.Failure -> {
                    _viewState.value = _viewState.value.copy(isLoading = false)
                    _singleEvent.emit(
                        HomeSingleEvent.ShowToast(
                            result.throwable?.message ?: "검색 실패"
                        )
                    )
                }
            }
        }
    }
}