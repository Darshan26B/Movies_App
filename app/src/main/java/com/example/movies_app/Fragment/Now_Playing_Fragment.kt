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
import com.example.movies_app.databinding.FragmentNowPlayingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Now_Playing_Fragment : Fragment() {

    lateinit var binding: FragmentNowPlayingBinding
    var page = 1
    var adapter = MoviesAdapter()
    var list = ArrayList<ResultsItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentNowPlayingBinding.inflate(layoutInflater)

        binding.NowPlayingNested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                Now_Playing(page)
            }
        })

        Now_Playing(page)
        return binding.root
    }

    private fun Now_Playing(page: Int) {
        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getPlayingMovies(page).enqueue(object : Callback<MoviesModel> {
            override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {

                if (response.isSuccessful) {
                    var Now_Playing =response.body()?.results

                    list.addAll(Now_Playing as ArrayList<ResultsItem>)

                    adapter.setListing(list)
                    binding.rcvNowPlayingMovies.layoutManager = LinearLayoutManager(context)
                    binding.rcvNowPlayingMovies.adapter = adapter
                }
            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {

            }

        })
    }



}