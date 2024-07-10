package com.example.myapplication.ui.favoritos

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritosViewHolder(view : View, private val viewModel: FavoritosViewModel) : RecyclerView.ViewHolder(view) {
    private var id : Int = 0


    fun bind(id : Int){
        this.id = id
        CoroutineScope(Dispatchers.Main).launch {
            val character = viewModel.getCharacterById(id)
           character.let {
               Log.d("TPO-LOG","a ver que es ${it}")
           }
        }
    }
}
