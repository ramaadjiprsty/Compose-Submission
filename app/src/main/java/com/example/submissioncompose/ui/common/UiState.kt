package com.example.submissioncompose.ui.common

sealed class UiState<out T: Any?> {
    data object Loading: UiState<Nothing>()
    data class Success<T: Any>(val data: T): UiState<T>()
    data class Error(val error: Throwable): UiState<Nothing>()

}