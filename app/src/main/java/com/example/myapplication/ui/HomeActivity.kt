package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.ui.favoritos.FavoritosActivity
import com.example.myapplication.ui.personajes.PersonajesActivity


import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var emailTextView: TextView
    private lateinit var providerTextView: TextView
    private lateinit var logOutButton: Button
    private lateinit var personajesButton : Button
    private lateinit var favoritosButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa las vistas aquí
        emailTextView = findViewById(R.id.emailTextView)
        logOutButton = findViewById(R.id.logOutButton)
        personajesButton = findViewById(R.id.personajesButton)
        favoritosButton = findViewById(R.id.favoritosButton)

        val bundle = intent.extras
        val email = bundle?.getString("email")

        setup(email ?: "")
    }

    private fun setup(email: String) {
        title = "Inicio"

        emailTextView.text = "Hola, " + email

        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressedDispatcher.onBackPressed()
        }
        personajesButton.setOnClickListener {
            showPersonajes()
        }
        favoritosButton.setOnClickListener {
            showFavoritos()
        }
    }

    private fun showPersonajes(){
        val personajesIntent = Intent(this, PersonajesActivity::class.java)
        startActivity(personajesIntent)
    }
    private fun showFavoritos(){
        val favoritosIntent = Intent(this, FavoritosActivity::class.java)
        startActivity(favoritosIntent)
    }
}