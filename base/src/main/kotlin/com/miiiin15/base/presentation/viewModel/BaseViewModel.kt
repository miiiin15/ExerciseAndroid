package com.miiiin15.base.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ViewState
interface Intent
interface SingleEvent

abstract class BaseViewMode<I : Intent, S : ViewState, E : SingleEvent>(initialState: S) : ViewModel() {

    protected val _viewState = MutableStateFlow(initialState)
    val viewState: StateFlow<S> = _viewState

    protected val _singleEvent = MutableSharedFlow<E>()
    val singleEvent: SharedFlow<E> = _singleEvent

    abstract fun processIntent(intent: I)
}