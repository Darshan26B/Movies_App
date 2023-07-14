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
import com.example.movies_app.databinding.FragmentTopRetedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopReted_Fragment : Fragment() {

    var page = 1
    var adapter = MoviesAdapter()
    lateinit var binding: FragmentTopRetedBinding
    var list = ArrayList<ResultsItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentTopRetedBinding.inflate(layoutInflater)

        binding.TopRateNested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                TopRatedApi(page)
            }
        })


        TopRatedApi(page)
        return binding.root
    }

    private fun TopRatedApi(page: Int) {
        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getTopRate(page).enqueue(object : Callback<MoviesModel> {
            override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {

                if (response.isSuccessful) {
                    var TopReted_List =response.body()?.results

                    list.addAll(TopReted_List as ArrayList<ResultsItem>)

                    adapter.setListing(list)
                    binding.rcvTopRatedMovies.layoutManager = LinearLayoutManager(context)
                    binding.rcvTopRatedMovies.adapter = adapter
                }
            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {

            }

        })
    }


}