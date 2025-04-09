package com.miiiin15.feature.home.presentation

import androidx.lifecycle.ViewModel
import com.miiiin15.feature.home.data.remote.HomeApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeApiService: HomeApiService
) : ViewModel() {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun test() {
        scope.launch {
            try {
                val result = homeApiService.searchLocal("kill", 10)
                println("🔵result = $result")
            } catch (e: Exception) {
                println("❌에러 발생: ${e.message}")
            }
        }
    }
}