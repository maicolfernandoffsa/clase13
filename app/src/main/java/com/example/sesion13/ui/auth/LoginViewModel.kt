package com.example.sesion13.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sesion13.data.model.Usuario
import com.example.sesion13.data.repository.AuthRepository
import com.example.sesion13.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: LoginViewModel
): ViewModel() {

    private val _register = MutableLiveData<UiState<String>>()
        val register: LiveData<UiState<String>>
            get() = _register

    private val _login = MutableLiveData<UiState<String>>()
    val login: LiveData<UiState<String>>
        get() = _login

    private val _rc = MutableLiveData<UiState<String>>()
    val rc: LiveData<UiState<String>>
        get() = _rc

    fun getSession(result: (Usuario?) -> Unit){
        repository.getSession(result)
    }
}