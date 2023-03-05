package com.example.jawayadmin.models

import Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieDbApi {
    @GET("movie/{movie_id}")
    fun getFilm(
        @Path("movie_id") movie_id: Int?,
        @Query("api_key") apikey: String?
    ): Call<Film?>?
}