package com.example.movieapp.screen.movieList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.common.createScope
import com.example.movieapp.data.TestDataSource
import com.example.movieapp.model.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListAdapter:
    RecyclerView.Adapter<MovieListViewHolder>() {
    private var listener: TransactionFragmentClicks? = null
    private var movies: List<Movie>? = null
    private val adapterSuperScope = createScope(Dispatchers.Default + SupervisorJob())
    private val _mutableSelectedMovie = MutableLiveData<Movie?>()
    val selectedMovie = _mutableSelectedMovie

    private val superExceptionHandler = CoroutineExceptionHandler { canceledContext, exception ->
        val isActive = adapterSuperScope.isActive
        Log.e(TAG, "SuperExceptionHandler [Scope active:$isActive, canceledContext:$canceledContext]")
        adapterSuperScope.launch {logExceptionSuspend("superExceptionHandler", exception)}
    }
    companion object{
        const val TAG = "MovieDetailsAdapter"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MovieListViewHolder(inflater.inflate(R.layout.viewholder_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies?.size?:0
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bindMovie(getItem(position))
        holder.itemView.findViewById<ImageView>(R.id.iv_movieListBackground).setOnClickListener {
            _mutableSelectedMovie.postValue(getItem(position))
            listener?.addFragment("FragmentMovieDetails")
        }
    }

    fun setClickListener(listener: TransactionFragmentClicks?) {
        if(listener==null)
            Log.e(TAG,"setClickListener: listener is null")
        else
            this.listener = listener
    }

    private fun getItem(position: Int): Movie? {
       return movies?.get(position)

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