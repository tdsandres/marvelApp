package com.example.myapplication.data

import com.example.myapplication.model.Character
import com.example.myapplication.model.Comic

class MarvelRepository {
    private val marvelDs = MarvelDataSource()

// MarvelRepository.kt

    suspend fun getCharacters(nameStartsWith: String? = null): List<Character> {
        return marvelDs.getCharacters(nameStartsWith)
    }


    suspend fun getCharacterById(characterId: Int): List<Character>{
        return marvelDs.getCharacterById(characterId)
    }

    suspend fun getComics() : List<Comic>{
        return marvelDs.getComics()
    }
}