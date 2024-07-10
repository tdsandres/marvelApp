package com.example.myapplication.data

import com.example.myapplication.model.Character

class FavoritoRepository {
    private val favoritoDs = FavoritoDataSource()
    suspend fun getFavCharacters(userEmail: String): List<Int> {
        return favoritoDs.getFavCharacters(userEmail)
    }
    suspend fun save(id: Int, userEmail : String){
        FavoritoDataSource().save(id,userEmail)
    }
}