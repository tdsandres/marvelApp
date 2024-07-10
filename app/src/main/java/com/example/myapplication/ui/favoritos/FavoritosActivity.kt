package com.example.myapplication.ui.favoritos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class FavoritosActivity : AppCompatActivity() {
    private lateinit var viewModel : FavoritosViewModel
    private lateinit var favoritosRv : RecyclerView
    private lateinit var adapter: FavoritosAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favoritos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[FavoritosViewModel::class.java]
        favoritosRv = findViewById(R.id.favoritosRv)
        favoritosRv.layoutManager = LinearLayoutManager(this)
        adapter = FavoritosAdapter(this,viewModel)
        favoritosRv.adapter = adapter
        viewModel.charactersId.observe(this){
            adapter.update(it)
        }
    }

}