package com.example.sesion13.data.repository

import android.net.Uri
import com.example.sesion13.data.model.Nota
import com.example.sesion13.data.model.Usuario
import com.example.sesion13.util.UiState


interface NotaRepository {

    fun getNotas(usuario: Usuario?,result:(UiState<List<Nota>>) -> Unit  )
    fun agregarNota(nota: Nota, result:(UiState<Pair<Nota, String>>) -> Unit)
    fun actualizarNota(nota: Nota, result:(UiState<String>) -> Unit)
    fun EliminarNota(nota: Nota, result: (UiState<String>) -> Unit)

}