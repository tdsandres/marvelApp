package com.example.myapplication.ui.personajes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.example.myapplication.R

class PersonajesActivity : AppCompatActivity() {
    private lateinit var viewModel: PersonajesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personajes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(PersonajesViewModel::class.java)
        viewModel.init()

        initRecyclerView()
        initSearchView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.personajesRv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PersonajeAdapter(this, viewModel)
        recyclerView.adapter = adapter

        viewModel.characters.observe(this) { characters ->
            adapter.update(characters)
        }
    }

    private fun initSearchView() {
        val searchView = findViewById<SearchView>(R.id.charactersSv)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchCharacters(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: implement if you want to search as the user types
                return false
            }
        })
    }
}
