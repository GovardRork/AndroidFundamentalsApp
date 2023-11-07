package com.example.movieapp.screen.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.common.SharedViewModel
import com.example.movieapp.common.ViewModelFactory
import com.example.movieapp.common.setRatingStars
import com.example.movieapp.common.toString
import com.example.movieapp.model.Actor
import com.example.movieapp.model.Movie

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    var listener: TransactionFragmentClicks? = null
    private lateinit var movie: Movie
    private val movieDetailsViewModel:MovieDetailsViewModel by viewModels {ViewModelFactory()}
    private val sharedViewModel:SharedViewModel by activityViewModels{ViewModelFactory()}
    private val adapter = ActorsAdapter()


    companion object {
        const val TAG: String = "FragmentMovieDetails"
        private const val FRAGMENT_ID = "fragment_id"
        fun newInstance(id: String): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putString(FRAGMENT_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsViewModel.actorsStream.observe(this.viewLifecycleOwner,this::updateActorsAdapter)
        movie = sharedViewModel.run { movie?: emptyMovie }
        movie.let { movieDetailsViewModel.loadActors(it.id) }

        val rv = view.findViewById<RecyclerView>(R.id.rv_movie_details)

        adapter.setClickListener(listener!!)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        rv.adapter = adapter
        rv.hasFixedSize()
        rv.layoutManager = layoutManager
        rv.addItemDecoration(ActorItemDecoration(spacingInPixels))
        view.apply {
            setRatingStars(movie.rating, this)
            setBackButtonClickListener(view, listener)
            setMovieDetailsView(view)
        }
    }

    private fun updateActorsAdapter(actors: List<Actor>?) {
        if (actors != null) {
            adapter.updateActors(actors)
        }
    }

    private fun setBackButtonClickListener(view: View, listener: TransactionFragmentClicks?) {
        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            listener?.addFragment("FragmentMovieList")
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

