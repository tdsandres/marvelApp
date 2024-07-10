package com.example.myapplication.ui.favoritos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.ui.personajes.PersonajeViewHolder


class FavoritosAdapter(
    private val context: Context,
    private val viewModel: FavoritosViewModel
) : Adapter<FavoritosViewHolder>(){
    var items : List<Int> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_personaje, parent, false)
        return FavoritosViewHolder(view, viewModel)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun update(items : List<Int>) {
        this.items = items
        notifyDataSetChanged()
    }

}