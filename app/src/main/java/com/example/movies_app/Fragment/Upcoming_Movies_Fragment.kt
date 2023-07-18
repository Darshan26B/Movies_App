package com.example.movies_app.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies_app.Adapter.MoviesAdapter
import com.example.movies_app.ApiClient
import com.example.movies_app.ApiInterface
 import com.example.movies_app.MoviesModel
import com.example.movies_app.ResultsItem
import com.example.movies_app.databinding.FragmentUpcomingMoviesBinding
import retrofit2.Call
import retrofit2.Response

class Upcoming_Movies_Fragment : Fragment() {

    var page = 1
    var adapter = MoviesAdapter()
    var list = ArrayList<ResultsItem>()
    lateinit var binding: FragmentUpcomingMoviesBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingMoviesBinding.inflate(layoutInflater)

        binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                page ++
                CallApi(page)
            }
        })

             CallApi(page)
        return binding.root
    }

    private fun CallApi(page:Int) {
        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getMovies(page).enqueue(object : retrofit2.Callback<MoviesModel> {
            override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {

                if (response.isSuccessful) {

                    var upcomingList = response.body()?.results
                    list.addAll(upcomingList as ArrayList<ResultsItem>)


                    adapter.setListing(list)
                    binding.rcvUpcomingMovies.layoutManager = LinearLayoutManager(context)
                    binding.rcvUpcomingMovies.adapter = adapter
                }

            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {

            }

        })
    }


}