package com.example.movieapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.model.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListAdapter(context: Context) :
    RecyclerView.Adapter<MovieListViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var listener: TransactionFragmentClicks? = null
    private var movies: List<Movie>? = null
    private val adapterContext = Dispatchers.Default + SupervisorJob()
    private var adapterSuperScope = createScope(adapterContext)

    private val superExceptionHandler = CoroutineExceptionHandler { canceledContext, exception ->
        val isActive = adapterSuperScope.isActive
        Log.e(TAG, "SuperExceptionHandler [Scope active:$isActive, canceledContext:$canceledContext]")
        adapterSuperScope.launch {logExceptionSuspend("superExceptionHandler", exception)}
    }
    companion object{
        const val TAG = "MovieDetailsAdapter"
        val EMPTY_MOVIE_DETAILS = Movie(0,0,null,null,0,0,false,0,null,null,null,null)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(inflater.inflate(R.layout.viewholder_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies?.size?:0
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bindMovieDetails(getItem(position))
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

    private fun getItem(position: Int): Movie {
       return movies?.get(position)?: EMPTY_MOVIE_DETAILS

    }

    fun loadMovies() {
        adapterSuperScope.launch(superExceptionHandler) {
            loadMoviesSuspend()
        }
    }
    private suspend fun loadMoviesSuspend() = withContext(Dispatchers.Default){
        movies= TestDataSource().movieDetailsList
    }

    private suspend fun logExceptionSuspend(who: String, throwable: Throwable) = withContext(Dispatchers.Default) {
        logException(who, throwable)
    }

    private fun logException(who: String, throwable: Throwable) {
        Log.e(TAG, "$who::Failed", throwable)
    }
}