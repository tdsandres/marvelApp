package com.example.myapplication.data

import com.example.myapplication.model.ApiResponse
import com.example.myapplication.model.Character
import com.example.myapplication.model.Comic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("nameStartsWith") nameStartsWith: String?,
    ): Response<ApiResponse<Character>>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: Int): Response<ApiResponse<Character>>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(@Path("characterId") characterId: String): Response<ApiResponse<Comic>>

    @GET("/v1/public/comics")
    suspend fun getComics(): Response<ApiResponse<Comic>>
}
