package com.example.jawayadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AppHome : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_home)

        val btnNewMovies: Button = findViewById(R.id.newMovies)
        val btnMyMovies: Button = findViewById(R.id.myMovies)

        btnNewMovies.setOnClickListener(){ startActivity( Intent(this, NewMovies::class.java) ) }
        btnMyMovies.setOnClickListener(){ startActivity( Intent(this, MyMovies::class.java) ) }

    }
}