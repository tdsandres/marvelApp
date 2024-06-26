package com.example.myapplication.data

import com.example.myapplication.model.ApiResponse
import com.example.myapplication.model.Character
import com.example.myapplication.model.Comic
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Path

interface MarvelAPI {
    @GET("/v1/public/characters")
    suspend fun getCharacters(): Response<ApiResponse<Character>>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId : Int): Response<ApiResponse<Character>>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(@Path("chracterId") characterId: String) : Response<ApiResponse<Comic>>

    @GET("/v1/public/comics")
    suspend fun getComics() : Response<ApiResponse<Comic>>
}