package com.example.movieapp.presentation.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.common.ViewModelFactory
import com.example.movieapp.common.setRatingStars
import com.example.movieapp.common.toString
import com.example.movieapp.model.Actor
import com.example.movieapp.model.Movie
import com.example.movieapp.presentation.BaseFragment
import com.example.movieapp.presentation.MovieFragmentsEnum

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {
    private lateinit var movie: Movie
    private lateinit var adapter: ActorsAdapter
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels { ViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            setMovie()
            setRecyclerView(this)
            setRatingStars(movie.rating, this)
            setBackButtonClickListener(this, listener)
            setMovieDetailsView(this)
        }
    }
    private fun setMovie() {
        movieDetailsViewModel.actorsStream.observe(
            this.viewLifecycleOwner,
            this::updateActorsAdapter
        )
        movie = sharedViewModel.run { movie ?: emptyMovie }
        movie.let { movieDetailsViewModel.loadActors(it.id) }
    }

    private fun setRecyclerView(view: View) {
        val rv = view.findViewById<RecyclerView>(R.id.rv_movie_details)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        adapter = ActorsAdapter()
        adapter.setClickListener(listener)
        rv.adapter = adapter
        rv.layoutManager = layoutManager
        rv.addItemDecoration(ActorItemDecoration(spacingInPixels))
        // Optimizations
        rv.hasFixedSize()
    }

    private fun updateActorsAdapter(actors: List<Actor>?) {
        if (actors != null) {
            adapter.updateActors(actors)
        }
    }

    private fun setBackButtonClickListener(view: View, listener: TransactionFragmentClicks?) {
        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            listener?.addFragment(MovieFragmentsEnum.MovieList.className)
        }
    }

    private fun setMovieDetailsView(view: View) {
        val movieImage: ImageView = view.findViewById(R.id.iv_poster_background)
        movie.let {
            Glide.with(view.context)
                .load(it.detailImageUrl)
                .centerInside()
                .into(movieImage)
            view.findViewById<TextView>(R.id.tv_movie_name).text = it.title
            view.findViewById<TextView>(R.id.tv_movie_pg).text =
                view.resources.getString(R.string.tv_plus13, it.pgAge)
            view.findViewById<TextView>(R.id.tv_movie_genre).text = it.genres.toString()
            view.findViewById<TextView>(R.id.tv_review).text =
                view.resources.getString(R.string.tv_review, it.reviewCount)
            view.findViewById<TextView>(R.id.tv_storytext).text = it.storyLine
            //Set cast textview for empty actors
            if (it.actors.isNullOrEmpty()) {
                val castView = view.findViewById<TextView>(R.id.tv_cast)
                castView.text = getString(R.string.no_actors_in_this_movie)
            }
        }
    }
}

