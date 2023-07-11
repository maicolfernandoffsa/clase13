package com.example.sesion13.data.model

import java.util.Date

data class Nota(
    var id: String = "",
    val titulo: String = "",
    val usuario_id: String = "",
    val descripción: String = "",
    val fecha: Date = Date()
)