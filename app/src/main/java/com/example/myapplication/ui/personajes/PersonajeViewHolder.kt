package com.example.myapplication.ui.personajes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Character
import com.squareup.picasso.Picasso

class PersonajeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val characterName: TextView = view.findViewById(R.id.txtNombrePersonajeVH)
    private val characterThumbnail: ImageView = view.findViewById(R.id.imagePersonajeVH)

    fun bind(character: Character) {
        characterName.text = character.name
        Picasso.get().load(character.thumbnail.path + "." + character.thumbnail.extension).into(characterThumbnail)
    }
}
