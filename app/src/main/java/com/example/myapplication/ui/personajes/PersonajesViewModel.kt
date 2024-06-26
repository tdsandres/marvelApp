package com.example.myapplication.ui.personajes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.MarvelRepository
import com.example.myapplication.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext

class PersonajesViewModel : ViewModel() {
    private val marvelRepo = MarvelRepository()
    var characters: MutableLiveData<List<Character>> = MutableLiveData<List<Character>>()



    private val coroutineContext: CoroutineContext = newSingleThreadContext("chars")
    private val scope = CoroutineScope(coroutineContext)

    fun fetchCharacters(){
        scope.launch {
            try {
                val result = marvelRepo.getCharacters()
                characters.postValue(result)
                Log.d("TPO-LOG", "Fetch Correcto ${characters}")
            } catch (e: Exception){
                Log.e("TPO-LOG",e.toString())
            }
        }
    }

    fun init(){
        fetchCharacters()

    }

}