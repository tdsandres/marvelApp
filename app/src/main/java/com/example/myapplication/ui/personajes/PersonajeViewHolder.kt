package com.example.myapplication.ui.personajes

import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Character
import com.squareup.picasso.Picasso

class PersonajeViewHolder(view: View,private val listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {
    private val characterName: TextView = view.findViewById(R.id.txtNombrePersonajeVH)
    private val characterThumbnail: ImageView = view.findViewById(R.id.imagePersonajeVH)
    val characterId : MutableLiveData<Int> = MutableLiveData()
    private val favoritoBtn: Button = view.findViewById(R.id.favoritoBtn)

    fun bind(character: Character) {
        characterName.text = character.name
        Picasso.get().load(character.thumbnail.path + "." + character.thumbnail.extension).into(characterThumbnail)
    }

    init {
        favoritoBtn.setOnClickListener{
            characterId.value.let {
            Log.d("TPO-MARVEL" ,"ID personaje $it")
            }
        }
    }
}
