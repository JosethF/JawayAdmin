package com.example.jawayadmin

import Film
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jawayadmin.models.MyMoviesAdapter
import com.google.firebase.database.*

class MyMovies : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var filmRecyclerview : RecyclerView
    private lateinit var  filmArrayList : ArrayList<Film>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_movies)

        filmRecyclerview = findViewById(R.id.recyclerMyMovies)
        filmRecyclerview.layoutManager = LinearLayoutManager(this)
        filmRecyclerview.setHasFixedSize(true)

        filmArrayList = arrayListOf<Film>()
        getFilmData()
    }
    private fun getFilmData() {

        dbref = FirebaseDatabase.getInstance().getReference("films")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val film = userSnapshot.getValue(Film::class.java)
                        filmArrayList.add(film!!)
                    }
                    filmRecyclerview.adapter = MyMoviesAdapter(filmArrayList,applicationContext)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
