package com.example.movies_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies_app.Adapter.FragmentAdapter
import com.example.movies_app.Fragment.Now_Playing_Fragment
import com.example.movies_app.Fragment.PopularFragment
import com.example.movies_app.Fragment.TopReted_Fragment
import com.example.movies_app.Fragment.Upcoming_Movies_Fragment
import com.example.movies_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding

    var title = arrayOf("Now Playing","Popular","Top Rated","Upcoming")
    var Fragment = arrayOf(
        Now_Playing_Fragment(),
        PopularFragment(),
        TopReted_Fragment(),
        Upcoming_Movies_Fragment()
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fragmentMovies.adapter = FragmentAdapter(supportFragmentManager,Fragment,title)
        binding.TLMovies.setupWithViewPager(binding.fragmentMovies)



    }

}