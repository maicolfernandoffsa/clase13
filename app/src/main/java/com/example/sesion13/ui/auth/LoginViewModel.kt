package com.example.sesion13.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sesion13.data.model.Usuario
import com.example.sesion13.data.repository.AuthRepository
import com.example.sesion13.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: AuthRepository
): ViewModel() {

    private val _register = MutableLiveData<UiState<String>>()
        val register: LiveData<UiState<String>>
            get() = _register

    private val _login = MutableLiveData<UiState<String>>()
    val login: LiveData<UiState<String>>
        get() = _login

    private val _recuperarContrasena = MutableLiveData<UiState<String>>()
    val rc: LiveData<UiState<String>>
        get() = _recuperarContrasena

    fun register(
        correo: String,
        contrasena: String,
        usuario: Usuario
    ){
        _register.value = UiState.Loading
        repository.registerUsuario(
            correo = correo,
            contrasena = contrasena,
            usuario = usuario
        ){_register.value = it}
    }

    fun login(
     correo: String,
     contrasena: String
    ){
        _login.value = UiState.Loading
        repository.loginUsuario(
            correo,
            contrasena
        ){
            _login.value = it
        }
        }

    fun recuperarContrasena(correo: String){
        _recuperarContrasena.value = UiState.Loading
        repository.recuperarContrasena(correo){
            _recuperarContrasena.value = it
        }
    }

    fun cerrarSesion(result: () -> Unit){
        repository.cerrarSesion(result)
    }

    fun getSession(result: (Usuario?) -> Unit){
        repository.getSesion(result)
    }
}