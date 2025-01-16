package com.example.capitecassessment.presenation.ui.auth.repo

import com.example.capitecassessment.util.await
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class NewAccountRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    suspend fun CreateNewAccountWithEmail(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }
}