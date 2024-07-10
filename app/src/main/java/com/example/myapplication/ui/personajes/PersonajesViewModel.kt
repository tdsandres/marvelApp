package com.example.myapplication.ui.personajes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FavoritoRepository
import com.example.myapplication.data.MarvelRepository
import com.example.myapplication.model.Character
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext

class PersonajesViewModel : ViewModel() {
    private val user = FirebaseAuth.getInstance().currentUser!!.email.toString()
    private val marvelRepo = MarvelRepository()
    private val favoritoRepo = FavoritoRepository()
    var characters: MutableLiveData<List<Character>> = MutableLiveData()

    private val coroutineContext: CoroutineContext = newSingleThreadContext("chars")
    private val scope = CoroutineScope(coroutineContext)

    fun fetchCharacters(nameStartsWith: String? = "a") {
        scope.launch {
            try {
                val result = marvelRepo.getCharacters(nameStartsWith)
                characters.postValue(result)
                Log.d("TPO-LOG", "Fetch Correcto $characters")
            } catch (e: Exception) {
                Log.e("TPO-LOG", "Error fetching characters", e)
            }
        }
    }

    fun searchCharacters(query: String) {
        scope.launch {
            try {
                val result = marvelRepo.getCharacters(nameStartsWith = query)
                characters.postValue(result)
                Log.d("TPO-LOG", "Búsqueda correcta: $query")
            } catch (e: Exception) {
                Log.e("TPO-LOG", "Error en la búsqueda: $query", e)
            }
        }
    }

    fun saveFavorite(id : Int){
        viewModelScope.launch {
            try {
                favoritoRepo.save(id,user)
            }
            catch (e : Exception){
                Log.e("TPO-MARVEL", "Error al guardar el favorito $e")
            }
        }

    }

    fun init() {
        fetchCharacters()
    }
}
