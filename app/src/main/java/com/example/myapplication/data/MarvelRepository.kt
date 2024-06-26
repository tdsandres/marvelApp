package com.example.myapplication.data

import com.example.myapplication.model.Character
import com.example.myapplication.model.Comic

class MarvelRepository {
    private val marvelDs = MarvelDataSource()

    suspend fun getCharacters() : List<Character>{
        return marvelDs.getCharacters()
    }

    suspend fun getCharacterById(characterId: Int) : Character?{
        return marvelDs.getCharacterById(characterId)
    }

    suspend fun getComics() : List<Comic>{
        return marvelDs.getComics()
    }
}