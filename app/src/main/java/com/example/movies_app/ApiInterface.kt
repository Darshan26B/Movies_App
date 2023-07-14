package com.example.movies_app

 import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("upcoming")
    fun getMovies(
        @Query("page")page:Int
    ) :Call<MoviesModel>

    @GET("now_Playing")

    fun getPlayingMovies(
        @Query("page")page : Int
    ) : Call<MoviesModel>

    @GET("popular")

    fun getPopularmovies(
        @Query("page") page: Int
    ): Call<MoviesModel>

    @GET("Top_Rate")
    fun getTopRate(
        @Query("page")page:Int
    ) : Call<MoviesModel>


}