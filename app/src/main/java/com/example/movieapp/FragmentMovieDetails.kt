package com.example.movieapp

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FragmentMovieDetails: Fragment() {
    var uuid:String? = null
    var listener: TransactionFragmentClicks? = null
    var movie:Movie? = null
    companion object{
        const val fragmentName: String = "FragmentMovieDetails"
        private const val FRAGMENT_ID = "fragment_id"
        private const val FRAGMENT_MOVIE = "data"
        fun newInstance(id: String): FragmentMovieDetails {
            val fragment = FragmentMovieDetails()
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
        return inflater.inflate(R.layout.fragment_movie_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let{
            movie =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getParcelable(MainActivity.FRAGMENT_MOVIE, Movie::class.java)
                } else {
                    it.get(MainActivity.FRAGMENT_MOVIE)
                } as Movie?
        }

        val rv = view.findViewById<RecyclerView>(R.id.rv_movie_details)
        val adapter = ActorsAdapter(rv?.context!!,movie!!)
        adapter.setClickListener(listener!!)
        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        rv.adapter = adapter
        rv.hasFixedSize()
        rv.layoutManager = layoutManager
        rv.addItemDecoration(CastItemDecoration(spacingInPixels))
        view.apply {
            setBackButtonClickListener(view,listener)
            setMovieDetailsView(view)
            }
        }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(FRAGMENT_MOVIE,movie)
        super.onSaveInstanceState(outState)
    }

    private fun setBackButtonClickListener(view: View, listener:TransactionFragmentClicks?) {
        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            listener?.addFragment("FragmentMovieList", null)
        }
    }
    private fun setMovieDetailsView(view: View){
        val movieImage: ImageView = view.findViewById(R.id.iv_poster_background)
        movie?.let {
            Glide.with(view.context)
                .load(it.image)
                .centerInside()
                .into(movieImage)
            view.findViewById<TextView>(R.id.tv_movie_name).text = it.name
            view.findViewById<TextView>(R.id.tv_movie_pg).text = view.resources.getString(R.string.tv_plus13,it.pg)
            view.findViewById<TextView>(R.id.tv_movie_genre).text = it.genre
            view.findViewById<TextView>(R.id.tv_review).text = view.resources.getString(R.string.tv_review, it.reviews)
            view.findViewById<TextView>(R.id.tv_storytext).text = it.story
        }
    }
    private fun setRatingStars(view: View){
        view.findViewById<TextView>(R.id.iv_rating_star_1)
    }

}
class CastItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = spacing / 4
        outRect.bottom = spacing / 4
        outRect.right = spacing / 4
        outRect.left = spacing / 4

        when (parent.getChildAdapterPosition(view)) {
            0 -> outRect.left = 0
        }
    }
}