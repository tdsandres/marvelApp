package com.example.myapplication.data

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await

class FavoritoDataSource {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getFavCharacters(userEmail: String): List<Int> {
        return try {
            val userDocRef = db.collection("users").document(userEmail)
            val snapshot = userDocRef.get().await()
            if (snapshot.exists()) {
                val favoritos = snapshot.get("favoritos") as? List<Number> ?: emptyList()
                Log.d("TPO-MARVEL", "Favoritos obtenidos: $favoritos")
                favoritos.map { it.toInt() }
            } else {
                Log.d("TPO-MARVEL", "El documento no existe para el usuario: $userEmail")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("TPO-MARVEL", "Ocurrió un error al obtener los favoritos $userEmail $e")
            emptyList()
        }
    }

    suspend fun save(id: Int, userEmail: String) {
        try {
            val userDocRef = db.collection("users").document(userEmail)
            val snapshot = userDocRef.get().await()
            if (snapshot.exists()) {
                val favoritos = snapshot.get("favoritos") as? MutableList<Number> ?: mutableListOf<Number>()
                Log.d("TPO-MARVEL", "Favoritos actuales: $favoritos")
                Log.d("TPO-MARVEL", "Tipo de dato de favoritos: ${favoritos.map { it.javaClass }}")
                if (favoritos.any { it.toInt() == id }) {
                    // Eliminar el ID si ya está en la lista
                    userDocRef.update("favoritos", FieldValue.arrayRemove(id)).await()
                    Log.d("TPO-MARVEL", "Id eliminado satisfactoriamente")

                } else {
                    // Agregar el ID si no está en la lista
                    userDocRef.update("favoritos", FieldValue.arrayUnion(id)).await()
                    Log.d("TPO-MARVEL", "Id guardado satisfactoriamente")
                }
            } else {
                // Crear el documento si no existe y agregar el ID a la lista de favoritos
                val userData = mapOf(
                    "email" to userEmail,
                    "favoritos" to listOf(id)
                )
                userDocRef.set(userData).await()
                Log.d("TPO-MARVEL", "Documento creado y id guardado satisfactoriamente")
            }
        } catch (e: Exception) {
            Log.e("TPO-MARVEL", "Ocurrió un error $id $userEmail $e")
        }
    }
}
