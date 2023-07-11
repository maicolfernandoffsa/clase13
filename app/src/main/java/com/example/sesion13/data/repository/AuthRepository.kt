package com.example.sesion13.data.repository

import com.example.sesion13.data.model.Usuario
import com.example.sesion13.util.UiState

interface AuthRepository {

    fun registerUsuario(correo: String, contrasena: String, usuario: Usuario, result: (UiState<String>) -> Unit)
    fun actualizarUsuario(usuario: Usuario, result: (UiState<String>) -> Unit)
    fun loginUsuario (correo: String, contrasena: String, result: (UiState<String>) -> Unit)
    fun recuperarContrasena( correo: String, result: (UiState<String>) -> Unit)
    fun cerrarSesion(result: () -> Unit)
    fun AlmacenamientoSesion(id: String, result: (Usuario?) -> Unit)
    fun getSesion(result: (Usuario?) -> Unit)
}