package com.miiiin15.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miiiin15.base.domain.result.ResponseResult
import com.miiiin15.feature.home.domain.usecase.SearchLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchLocalUseCase: SearchLocalUseCase
) : ViewModel() {
    fun test() {
        viewModelScope.launch {
            searchLocalUseCase("맘스터치").also {
                when(it){
                    is ResponseResult.Success->{
                    }
                    is ResponseResult.Failure -> {
                    }
                }
            }
        }
    }
}