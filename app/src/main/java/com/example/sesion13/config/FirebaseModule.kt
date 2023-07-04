package com.example.sesion13.config

import com.example.sesion13.util.FirebaseStorageConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import  com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Provides
import javax.inject.Singleton

object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabaseIntance(): FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStoreIntance(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthIntance(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorageIntance(): StorageReference{
        return FirebaseStorage.getInstance().getReference(FirebaseStorageConstants.ROOT_DIRECTORY)
    }

}