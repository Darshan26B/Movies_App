package com.example.movies_app

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.MoviesLayoutBinding

class MoviesAdapter : Adapter<MoviesAdapter.Moviesholder>() {

    lateinit var upcomingList: List<ResultsItem?>
    lateinit var context:Context

    class Moviesholder(itemView: MoviesLayoutBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Moviesholder {
        context =parent.context
        var binding =MoviesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Moviesholder(binding)
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    override fun onBindViewHolder(holder: Moviesholder, position: Int) {
        holder.binding.apply {
            upcomingList.get(position)?.apply {
                Glide.with(context).load(ApiClient.Image_BASE_URL + posterPath).into(MoviesPoster)
                txttitle.text = originalTitle
                language.text = originalLanguage
                txtpopularity.text = popularity.toString()
                txtoverview.text = overview


            }
        }
    }

    fun setListing(upcomingList: List<ResultsItem?>?) {
        this.upcomingList = upcomingList as List<ResultsItem?>
    }
}