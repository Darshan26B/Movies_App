package com.example.movies_app

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("upcoming")
    fun getUpcomingMovies() :Call<MoviesModel>
}