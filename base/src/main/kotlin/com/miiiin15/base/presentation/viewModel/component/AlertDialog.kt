package com.miiiin15.base.presentation.viewModel.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect

data class AlertData(
    val title: String = "알림",
    val message: String,
    val buttonText: String = "확인",
    val onDismiss: (() -> Unit)? = null,
    val onClick: (() -> Unit)? = null
)


object AlertManager {
    private val _alertFlow = MutableSharedFlow<AlertData>(extraBufferCapacity = 1)
    val alertFlow: SharedFlow<AlertData> get() = _alertFlow.asSharedFlow()

    fun show(
        title: String = "알림",
        message: String,
        buttonText: String = "확인",
        onDismiss: (() -> Unit)? = null,
        onClick: (() -> Unit)? = null
    ) {
        _alertFlow.tryEmit(AlertData(title, message, buttonText, onDismiss, onClick))
    }
}

@Composable
fun AlertHost() {
    var currentAlert by remember { mutableStateOf<AlertData?>(null) }

    LaunchedEffect(Unit) {
        AlertManager.alertFlow.collect { alert ->
            currentAlert = alert
        }
    }

    currentAlert?.let { alert ->
        AlertDialog(
            onDismissRequest = {
                currentAlert = null
                alert.onDismiss?.invoke()
            },
            title = { Text(alert.title) },
            text = { Text(alert.message) },
            confirmButton = {
                Button(onClick = {
                    currentAlert = null
                    alert.onClick?.invoke()
                }) {
                    Text(alert.buttonText)
                }
            }
        )
    }
}