package com.example.sesion13.util

sealed class UiState<out T>{
    object Loading: UiState<Nothing>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Faile(val error: String?): UiState<Nothing>()

}