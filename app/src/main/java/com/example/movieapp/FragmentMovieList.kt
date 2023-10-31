package com.example.movieapp

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMovieList : Fragment() {
    var uuid: String? = null
    var listener: TransactionFragmentClicks? = null
    companion object {
        const val fragmentName: String = "FragmentMovieList"
        private const val FRAGMENT_ID = "fragment_id"
        fun newInstance(id: String): FragmentMovieList {
            val fragment = FragmentMovieList()
            val args = Bundle()
            args.putString(FRAGMENT_ID, id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentOrientation = resources.configuration.orientation
        val rv = view.findViewById<RecyclerView>(R.id.rv_movie_list)
        val adapter = MoviesAdapter(rv?.context!!,TestDataSource().MovieList)
        adapter.setClickListener(listener)
        var spanCount = 2
        if(currentOrientation != Configuration.ORIENTATION_PORTRAIT) spanCount = 4
        val layoutManager = GridLayoutManager(requireContext(),spanCount,RecyclerView.VERTICAL,false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        rv.adapter = adapter
        rv.layoutManager = layoutManager
        rv.addItemDecoration(EqualSpacingItemDecoration(spacingInPixels,currentOrientation))
    }
}

class EqualSpacingItemDecoration(private var spacing: Int,private val orientation:Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = spacing
        outRect.bottom = 0

        val position = parent.getChildAdapterPosition(view)
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Portrait
            // Apply spacing to each row, every row can contains 2 items
            when (position % 2) {
                0 -> {
                    outRect.left = 0
                    outRect.right = spacing/2
                }

                1 -> {

                    outRect.left = 0
                    outRect.right = 0
                }
            }
        }
        else {
            // LandScape
            // Apply spacing to each row, every row can contains 4 items
            when (position % 4) {
                0 -> {
                    outRect.left = spacing * 2
                    outRect.right = spacing/2
                }

                1 -> {

                    outRect.left = spacing/2
                    outRect.right = spacing/2
                }

                2 -> {
                    outRect.left = -spacing
                    outRect.right = 0
                }

                3 -> {
                    outRect.left = (-spacing*2.5).toInt()
                    outRect.right = 0
                }
            }
        }
    }
}
