package com.example.myapplication.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MarvelRepository
import com.example.myapplication.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    val marvelRepo : MarvelRepository = MarvelRepository()

    var characters: MutableLiveData<ArrayList<Character>> = MutableLiveData<ArrayList<Character>>()
    private val repository = MarvelRepository()


    private val coroutineContext: CoroutineContext = newSingleThreadContext("chars")
    private val scope = CoroutineScope(coroutineContext)

    fun init(context: Context) {
        viewModelScope.launch {
            val characters = marvelRepo.getCharacters()
            val character = marvelRepo.getCharacterById(1011334)
            if (characters.isNotEmpty()) {
                // Procesa los personajes como se requiere
                val firstCharacter = characters[0]
                // Haz algo con firstCharacter
            } else {
                // Maneja el caso de lista vacía
                // Puede ser mostrando un mensaje en la UI, log, etc.
            }
        }
    }
}