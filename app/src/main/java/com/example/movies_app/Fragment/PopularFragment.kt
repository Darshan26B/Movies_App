package com.example.movies_app.Fragment

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
import com.example.movies_app.databinding.FragmentPopularfragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : Fragment() {

    lateinit var binding: FragmentPopularfragmentBinding
    var page = 1
    var adapter = MoviesAdapter()
    var list = ArrayList<ResultsItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularfragmentBinding.inflate(layoutInflater)

        binding.popularNested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                PopularMovieApi(page)
            }
        })
        PopularMovieApi(page)
        return binding.root
    }
    private fun PopularMovieApi(page: Int) {
        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getPopularmovies(page).enqueue(object : Callback<MoviesModel> {
            override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {

                if (response.isSuccessful) {
                    var Popular =response.body()?.results

                    list.addAll(Popular as ArrayList<ResultsItem>)

                    adapter.setListing(list)
                    binding.rcvPopularMovies.layoutManager = LinearLayoutManager(context)
                    binding.rcvPopularMovies.adapter = adapter
                }
            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {

            }

        })
    }



}