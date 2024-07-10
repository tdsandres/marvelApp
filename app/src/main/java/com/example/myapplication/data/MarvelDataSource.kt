package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.model.Character
import com.example.myapplication.model.Comic
import com.example.myapplication.model.Thumbnail
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelDataSource {

    private val PUBLIC_KEY = "bb5f5339d78067c6568d2ad3cc2f3777"
    //private val PRIVATE_KEY = "918ccdcc4d038d389f1476eefc5846b950ad4125"
    private val TS = "1"
    private val HASH = "6906841bb8c3289bada4ec3ab0994247"
    private val API_BASE_URL = "https://gateway.marvel.com:443"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(TS, PUBLIC_KEY, HASH))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api = retrofit.create(MarvelAPI::class.java)

    suspend fun getCharacters(nameStartsWith: String? = "a"): List<Character> {
        Log.d("MarvelAPI", "Characters Datasource Get")
        try {
            val response = api.getCharacters(nameStartsWith = nameStartsWith)
            if (response.isSuccessful) {
                val characters = response.body()?.data?.results ?: emptyList()
                Log.d("MarvelAPI", "Characters received: $characters")
                return characters
            } else {
                Log.e("MarvelAPI", "Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("MarvelAPI", "Error fetching characters", e)
        }
        return emptyList()
    }

    suspend fun getCharacterById(characterId: Int) : Character? {
        try {
            val response = api.getCharacterById(characterId)
            if(response.isSuccessful){
                val character = response.body()?.data?.results ?: emptyList()
                Log.d("MarvelAPI", "CharactersById Datasource Get $character")
                return character[0]
            }
            else{
                throw Error()
            }
        }
        catch (e :Exception){
            Log.e("MarvelAPI", "Error fetching characterById", e)
            return null
        }
    }

    suspend fun getComics(): List<Comic> {
        Log.d("MarvelAPI", "Comics Datasource Get")
        try {
            val response = api.getComics()
            if (response.isSuccessful) {
                val comics = response.body()?.data?.results ?: emptyList()
                Log.d("MarvelAPI", "Comics received: $comics")
                return comics
            } else {
                Log.e("MarvelAPI", "Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("MarvelAPI", "Error fetching comics", e)
        }
        return emptyList()
    }
}
