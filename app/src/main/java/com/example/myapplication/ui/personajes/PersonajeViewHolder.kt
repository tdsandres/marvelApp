package com.example.myapplication.ui.personajes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Character
import com.squareup.picasso.Picasso

class PersonajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.txtNombrePersonajeVH)
    private val imageView: ImageView = itemView.findViewById(R.id.imagePersonajeVH)

    fun bind(character: Character) {
        nameTextView.text = character.name
        Picasso.get()
            .load("${character.thumbnail.path}.${character.thumbnail.extension}")
            .into(imageView)
    }
}