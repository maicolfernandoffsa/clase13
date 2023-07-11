package com.example.sesion13.data.repository

import com.example.sesion13.data.model.Nota
import com.example.sesion13.data.model.Usuario
import com.example.sesion13.util.FireStoreCollection
import com.example.sesion13.util.FireStoreDocumentField
import com.example.sesion13.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.StorageReference

class NotaRepositoryImp(
    val database: FirebaseFirestore,
    val storageReference: StorageReference
    ): NotaRepository {
    override fun getNotas(usuario: Usuario?, result: (UiState<List<Nota>>) -> Unit) {
        database.collection(FireStoreCollection.NOTA)
            .whereEqualTo(FireStoreDocumentField.USUARIO_ID, usuario?.id)
            .orderBy(FireStoreDocumentField.FECHA, Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener{
                val notas = arrayListOf<Nota>()
                for(document in it){
                    val nota = document.toObject(Nota::class.java)
                    notas.add(nota)
                }
                result.invoke(
                    UiState.Success(notas)
                )
            }
            .addOnFailureListener{
                result.invoke(
                    UiState.Faile(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun agregarNota(nota: Nota, result: (UiState<Pair<Nota, String>>) -> Unit) {
       val document = database.collection(FireStoreCollection.NOTA).document()
        nota.id = document.id
        document
            .set(nota)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(Pair(nota, "Nota a sido creada con exito"))
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Faile(
                        it.localizedMessage
                        )
                )
            }
    }

    override fun actualizarNota(nota: Nota, result: (UiState<String>) -> Unit) {
       val document = database.collection(FireStoreCollection.NOTA).document(nota.id)
        document
            .set(nota)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success( "Nota a sido actualizada con exito")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Faile(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun EliminarNota(nota: Nota, result: (UiState<String>) -> Unit) {

        database.collection(FireStoreCollection.NOTA).document(nota.id)
            .delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("Nota Eliminada con exito"))
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Faile(
                        it.message
                    )
                )
            }


    }
}