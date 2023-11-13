package com.example.movieapp.presentation.movieList

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.presentation.BaseFragment

class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateListener()
        setRecyclerView(view)
    }

    private fun setRecyclerView(view: View) {
        val currentOrientation = resources.configuration.orientation
        val rv = view.findViewById<RecyclerView>(R.id.rv_movie_list)
        var spanCount = 2
        if (currentOrientation != Configuration.ORIENTATION_PORTRAIT) spanCount = 4
        val layoutManager =
            GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        rv.adapter = setAdapter()
        rv.layoutManager = layoutManager
        rv.addItemDecoration(EqualSpacingItemDecoration(spacingInPixels, currentOrientation))
    }

    private fun setAdapter(): MovieListAdapter {
        val adapter = MovieListAdapter()
        adapter.loadMovies()
        adapter.setClickListener(listener)
        adapter.selectedMovie.observe(this.viewLifecycleOwner, ::updateSelectedMovie)
        return adapter
    }

    private fun updateSelectedMovie(movie: Movie?) {
        sharedViewModel.movie = movie
    }
    private fun updateListener(){
        sharedViewModel.listener = listener
    }

    inner class EqualSpacingItemDecoration(private var spacing: Int, private val orientation: Int) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = spacing
            outRect.bottom = 0

            val position = parent.getChildAdapterPosition(view)
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Portrait
                // Apply spacing to each row, every row can contains 2 items
                when (position % 2) {
                    0 -> {
                        outRect.left = 0
                        outRect.right = spacing / 2
                    }

                    1 -> {

                        outRect.left = 0
                        outRect.right = 0
                    }
                }
            } else {
                // LandScape
                // Apply spacing to each row, every row can contains 4 items
                when (position % 4) {
                    0 -> {
                        outRect.left = spacing * 2
                        outRect.right = spacing / 2
                    }

                    1 -> {

                        outRect.left = spacing / 2
                        outRect.right = spacing / 2
                    }

                    2 -> {
                        outRect.left = -spacing
                        outRect.right = 0
                    }

                    3 -> {
                        outRect.left = (-spacing * 2.5).toInt()
                        outRect.right = 0
                    }
                }
            }
        }
    }
}
