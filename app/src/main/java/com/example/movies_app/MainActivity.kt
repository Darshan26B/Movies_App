package com.example.movies_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    var adapter = MoviesAdapter()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CallApi()
    }

    private fun CallApi() {
        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getUpcomingMovies().enqueue(object : retrofit2.Callback<MoviesModel> {
            override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {

                if (response.isSuccessful) {
                    var upcomingList = response.body()?.results
                    adapter.setListing(upcomingList)
                    binding.rcvMovies.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rcvMovies.adapter=adapter
                }

            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {

            }

        })
    }
}