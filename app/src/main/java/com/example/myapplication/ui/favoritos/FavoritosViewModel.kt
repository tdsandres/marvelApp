package com.example.myapplication.ui.favoritos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FavoritoRepository
import com.example.myapplication.data.MarvelRepository
import com.example.myapplication.model.Character
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext

class FavoritosViewModel : ViewModel(){
    private val user = FirebaseAuth.getInstance().currentUser!!.email.toString()
    private val favRepo = FavoritoRepository()
    private val marvelRepo = MarvelRepository()
    var charactersId: MutableLiveData<List<Int>> = MutableLiveData()

    private val coroutineContext: CoroutineContext = newSingleThreadContext("chars")
    private val scope = CoroutineScope(coroutineContext)

    fun fetchCharacters() {
        scope.launch {
            try {
                val result = favRepo.getFavCharacters(user)
                charactersId.postValue(result)
                Log.d("TPO-LOG", "Fetch Correcto $result")
            } catch (e: Exception) {
                Log.e("TPO-LOG", "Error fetching characters", e)
            }
        }
    }

    fun getCharacterById(id : Int){
        viewModelScope.launch {
            try {
                Log.d("TPO-LOG","Favorito correctamente obtenido $id")
                marvelRepo.getCharacterById(id)
            }
            catch (e : Exception){
                Log.e("TPO-LOG","Ocurrio un error al traer los personajes favoritos $e")
            }
        }
    }

    init{
        fetchCharacters()
    }
}