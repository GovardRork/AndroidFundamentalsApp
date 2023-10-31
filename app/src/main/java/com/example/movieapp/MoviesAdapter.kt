package com.example.movieapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(context: Context, private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var listener: TransactionFragmentClicks? = null
    companion object{
        const val TAG = "MoviesAdapter"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(inflater.inflate(R.layout.viewholder_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(getItem(position))
        holder.itemView.findViewById<ImageView>(R.id.iv_movieListBackground).setOnClickListener {
            listener?.addFragment("FragmentMovieDetails", bundleOf(Pair("data", getItem(position))))
        }
    }

    fun setClickListener(listener: TransactionFragmentClicks?) {
        if(listener==null)
            Log.e(TAG,"setClickListener: listener is null")
        else
            this.listener = listener
    }

    private fun getItem(position: Int): Movie = movies[position]
}