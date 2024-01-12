package com.example.cookingmaster

sealed class UiState<out T> {
    class Success<T>(val value: T) : UiState<T>()
    class Failure(val message: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}

fun <T> Result<T>.toUiState() : UiState<T> =
    if(this.isSuccess) this.getOrNull()?.let { UiState.Success(it) }
        ?: UiState.Failure("Data was null")
    else UiState.Failure(this.exceptionOrNull()?.message ?: "Very weird problem")