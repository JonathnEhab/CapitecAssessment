package com.example.capitecassessment.presenation.ui.auth.repo

import com.example.capitecassessment.util.await
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    suspend fun loginWithEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }
}