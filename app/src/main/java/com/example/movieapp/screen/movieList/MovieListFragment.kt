package com.example.movieapp.screen.movieList

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.common.SharedViewModel
import com.example.movieapp.common.ViewModelFactory
import com.example.movieapp.model.Movie

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    var listener: TransactionFragmentClicks? = null
    private val sharedViewModel: SharedViewModel by activityViewModels { ViewModelFactory() }
    companion object {
        const val TAG: String = "FragmentMovieList"
        private const val FRAGMENT_ID = "fragment_id"
        fun newInstance(id: String): MovieListFragment {
            val fragment = MovieListFragment()
            val args = Bundle()
            args.putString(FRAGMENT_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateListener()
        setMovieListRecycler(view)
    }

    private fun setMovieListRecycler(view: View) {
        val currentOrientation = resources.configuration.orientation
        val rv = view.findViewById<RecyclerView>(R.id.rv_movie_list)
        var spanCount = 2
        if (currentOrientation != Configuration.ORIENTATION_PORTRAIT) spanCount = 4
        val layoutManager =
            GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        rv.adapter = setMovieListAdapter()
        rv.layoutManager = layoutManager
        rv.addItemDecoration(EqualSpacingItemDecoration(spacingInPixels, currentOrientation))
    }

    private fun setMovieListAdapter(): MovieListAdapter {
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
