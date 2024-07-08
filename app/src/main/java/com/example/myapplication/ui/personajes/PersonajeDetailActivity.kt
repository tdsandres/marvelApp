package com.example.myapplication.ui.personajes

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class PersonajeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personaje_detail)

        val characterName = intent.getStringExtra("CHARACTER_NAME")
        val characterDescription = intent.getStringExtra("CHARACTER_DESCRIPTION")
        val characterThumbnail = intent.getStringExtra("CHARACTER_THUMBNAIL")

        val characterNameTextView = findViewById<TextView>(R.id.characterNameTextView)
        val characterDescriptionTextView = findViewById<TextView>(R.id.characterDescriptionTextView)
        val characterThumbnailImageView = findViewById<ImageView>(R.id.characterThumbnailImageView)

        characterNameTextView.text = characterName
        characterDescriptionTextView.text = characterDescription
        Picasso.get().load(characterThumbnail).into(characterThumbnailImageView)
    }
}
