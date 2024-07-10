package com.example.myapplication.ui.favoritos

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritosViewHolder(view : View, private val viewModel: FavoritosViewModel) : RecyclerView.ViewHolder(view) {
    private var id : Int = 0
    private val characterName: TextView = view.findViewById(R.id.txtNombrePersonajeVH)
    private val characterThumbnail: ImageView = view.findViewById(R.id.imagePersonajeVH)
    private val favoritoBtn: Button = view.findViewById(R.id.favoritoBtn)


    fun bind(id : Int){
        this.id = id
        CoroutineScope(Dispatchers.Main).launch {
            val character = viewModel.fetchCharacterById(id)
            Log.d("TPO-LOG","a ver que es ${character!!.name.toString()}")
            characterName.text = character.name
            Picasso.get().load(character.thumbnail.path + "." + character.thumbnail.extension).into(characterThumbnail)
            favoritoBtn.setOnClickListener {
                viewModel.saveFavorite(id)
                val lista = viewModel.charactersId.value?.toMutableList() ?: mutableListOf()
                lista.remove(id)
                viewModel.charactersId.value = lista
                Log.d("TPO-LOG", "Lista actualizada: $lista")
            }

        }
    }
}
