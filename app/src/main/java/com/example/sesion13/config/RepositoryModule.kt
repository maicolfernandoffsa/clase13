package com.example.sesion13.config

import android.content.SharedPreferences
import com.example.sesion13.data.repository.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent ::class)
@Module

object RepositoryModule {

    @Provides
    @Singleton

    fun provideNotaRepository(
        database: FirebaseFirestore,
        storageReference: StorageReference
    ):NotaRepository{
        return NotaRepositoryImp(database, storageReference)
    }

}