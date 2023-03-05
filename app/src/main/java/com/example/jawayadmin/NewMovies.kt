package com.example.jawayadmin

import Film
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jawayadmin.models.TheMovieDbApi
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewMovies : AppCompatActivity() {

    private var apiKey: String = "82f221b0284a2e84d8f263435edb4e6a"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_movies)

        var title: TextView = findViewById(R.id.titleFilm)
        var img: ImageView = findViewById(R.id.imgFilm)
        var date: TextView = findViewById(R.id.dateFilm)
        var overview: TextView = findViewById(R.id.overview)
        var genres: TextView = findViewById(R.id.genreDate)
        var dropBtn: Button = findViewById(R.id.dropFilm)
        var saveBtn: Button = findViewById(R.id.saveFilm)



        var rnd = (0..1000).random()
        callingAPI(rnd,title,img,date,overview,genres)

        dropBtn.setOnClickListener(){
            rnd = (0..1000).random()
            callingAPI(rnd, title, img, date, overview, genres)
        }

        //saveBtn.setOnClickListener(){ startActivity(intent) }
    }

    private fun callingAPI(
        rnd: Int?,
        title: TextView,
        img: ImageView,
        date: TextView,
        overview: TextView,
        genres: TextView
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiCall = retrofit.create(TheMovieDbApi::class.java)
        val call: Call<Film?>? = apiCall.getFilm(rnd,apiKey)

        call?.enqueue(object : Callback<Film?> {
            override fun onResponse(call: Call<Film?>, response: Response<Film?>) {

                title.text = response.body()?.title
                val posterPath = "https://image.tmdb.org/t/p/w200/"+response.body()?.poster_path
                Glide.with(applicationContext).load(posterPath).into(img)
                date.text = response.body()?.release_date
                overview.text = response.body()?.overview
                var genresList = response.body()?.genres
                var txtGnrs = ""
                for(i in 0 until genresList?.size!!)
                    txtGnrs = txtGnrs + genresList?.get(i!!)?.name!! + " "
                genres.text = txtGnrs

                var filmPrueba= Film()
                filmPrueba.id = response.body()?.id
                filmPrueba.title = response.body()?.title
                filmPrueba.poster_path = "https://image.tmdb.org/t/p/w200/"+response.body()?.poster_path
                filmPrueba.release_date = response.body()?.release_date
                filmPrueba.overview = response.body()?.overview
                filmPrueba.genres = response.body()?.genres
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("/films/"+response.body()?.id)
                myRef.setValue(filmPrueba)
                myRef.push()
            }
            override fun onFailure(call: Call<Film?>, t: Throwable) {}
        })
    }
}