package com.example.sesion13.data.repository

import android.content.SharedPreferences
import com.example.sesion13.data.model.Usuario
import com.example.sesion13.util.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.example.sesion13.util.FireStoreCollection
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class AuthRepositoryImp (
    val auth: FirebaseAuth,
    val database: FirebaseFirestore,
    val appPreferences: SharedPreferences,
    val gson: Gson
        ) :AuthRepository {

    override fun registerUsuario(
        correo: String,
        contrasena: String,
        usuario: Usuario,
        result: (UiState<String>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    usuario.id = it.result.user?.uid ?: ""
                    actualizarUsuario(usuario) { state ->
                        when (state) {
                            is UiState.Success -> {
                                AlmacenamientoSesion(id = it.result.user?.uid ?: "") {
                                    if (it == null) {
                                        result.invoke(UiState.Faile("Usuario Registrado con éxito, pero falló el almacenamiento del token"))
                                    } else {
                                        result.invoke(UiState.Success("Usuario Registrado con exito"))
                                    }
                                }
                            }

                            is UiState.Faile -> {
                                result.invoke(UiState.Faile(state.error))
                            }

                            else -> {}
                        }
                    }
                } else {
                                try {
                                    throw it.exception ?: java.lang.Exception("Autenticación Inválida")
                                }
                                catch (e: FirebaseAuthWeakPasswordException){
                                    result.invoke(UiState.Faile("Autentivación fallida, contraseña no sebe ser menor a 6 caracteres"))
                                }
                                catch (e: FirebaseAuthInvalidCredentialsException){
                                    result.invoke(UiState.Faile("Autentivación fallida, correo inválido"))
                                }
                                catch (e: FirebaseAuthUserCollisionException){
                                    result.invoke(UiState.Faile("Autentivación fallida, Correo ya existe"))
                                }

                            }

            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Faile(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun actualizarUsuario(usuario: Usuario, result: (UiState<String>) -> Unit) {

        val document = database.collection(FireStoreCollection.USUARIO).document(usuario.id)
        document
            .set(usuario)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Actualización exitosa")
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

    override fun loginUsuario(
        correo: String,
        contrasena: String,
        result: (UiState<String>) -> Unit
    ) {
       auth.signInWithEmailAndPassword(correo, contrasena)
           .addOnCompleteListener { tarea ->
               if (tarea.isSuccessful){
                   AlmacenamientoSesion(id= tarea.result.user?.uid ?: ""){
                       if(it == null){
                           result.invoke(UiState.Faile("Falló almacenamiento de token"))
                       }
                       else{
                           result.invoke(UiState.Success("Login Exitoso"))
                       }
                   }
               }

           }
           .addOnFailureListener {
               result.invoke(UiState.Faile("Autenticación Fallida, correo o contraseña son incorrectos"))
           }

    }

    override fun recuperarContrasena(correo: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun cerrarSesion(result: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun AlmacenamientoSesion(id: String, result: (Usuario?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getSesion(result: (Usuario?) -> Unit) {
        TODO("Not yet implemented")
    }
}