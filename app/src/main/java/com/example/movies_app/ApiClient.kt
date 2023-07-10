package com.example.movies_app

import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {


    companion object {

        var BASE_URL = "https://api.themoviedb.org/3/movie/"
        var Image_BASE_URL = "https://image.tmdb.org/t/p/w500"
        lateinit var retrofit: Retrofit
        var YOUR_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjRjYjY5YjY0ZWFlYmFjMWI2YzkzYjc0MzI5ZWI2YSIsInN1YiI6IjY0YWE3Mjk4M2UyZWM4MDBjYmNkZGUzOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KBcynMjpJEq69_AvgGFXgYP-qUag6KgLoTIz3TlM2N0"


        fun getApiClient(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor { Chain ->
                    val request = Chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer ${YOUR_TOKEN}").build()
                    Chain.proceed(request)

                }.build())
                .build()

            return retrofit
        }

    }
}