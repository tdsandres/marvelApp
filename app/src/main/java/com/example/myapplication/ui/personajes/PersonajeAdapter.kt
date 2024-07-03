package com.example.myapplication.ui.personajes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Character
import com.squareup.picasso.Picasso

class PersonajeAdapter(
    private val context: Context,
    private val viewModel: PersonajesViewModel
) : RecyclerView.Adapter<PersonajeViewHolder>() {

    private var characters: List<Character> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_personaje, parent, false)
        return PersonajeViewHolder(view)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    fun update(lista: List<Character>) {
        characters = lista
        notifyDataSetChanged()
    }
}
