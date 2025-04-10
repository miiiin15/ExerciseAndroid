package com.miiiin15.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miiiin15.base.domain.result.ResponseResult
import com.miiiin15.base.presentation.viewModel.BaseViewMode
import com.miiiin15.feature.home.domain.usecase.SearchLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchLocalUseCase: SearchLocalUseCase
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
        }
    }

    // HomeViewModel.kt
    private fun searchLocal() {
        val keyword = _viewState.value.keyword
        viewModelScope.launch {
            when (val result = searchLocalUseCase(keyword, 40)) {
                is ResponseResult.Success -> {
                    _viewState.value = _viewState.value.copy(
                        isLoading = false,
                        result = result.value.sortedBy { it.mapx.toLong() },
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