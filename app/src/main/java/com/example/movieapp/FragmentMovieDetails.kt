package com.example.movieapp

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.model.Movie

class FragmentMovieDetails : Fragment() {
    var uuid: String? = null
    var listener: TransactionFragmentClicks? = null
    var movie: Movie? = null

    companion object {
        const val TAG: String = "FragmentMovieDetails"
        private const val FRAGMENT_ID = "fragment_id"
        private const val FRAGMENT_MOVIE = "data"
        fun newInstance(id: String): FragmentMovieDetails {
            val fragment = FragmentMovieDetails()
            val args = Bundle()
            args.putString(FRAGMENT_ID, id)
            fragment.arguments = args
            return fragment
        }

        //We have float rating from 0 to 10 and just 5 stars at reviews
        fun setRatingStars(rating: Int, view: View) {
            val ratingStar1: ImageView = view.findViewById(R.id.iv_rating_star_1)
            val ratingStar2: ImageView = view.findViewById(R.id.iv_rating_star_2)
            val ratingStar3: ImageView = view.findViewById(R.id.iv_rating_star_3)
            val ratingStar4: ImageView = view.findViewById(R.id.iv_rating_star_4)
            val ratingStar5: ImageView = view.findViewById(R.id.iv_rating_star_5)
            val stars =
                listOf(ratingStar1, ratingStar2, ratingStar3, ratingStar4, ratingStar5)
            val fullRedStarsCount = (rating / 2)
            val fullGreyStarsCount = (rating / 2 - fullRedStarsCount)
            val halfStarCount = rating - 2 * fullRedStarsCount - 2 * fullGreyStarsCount

            stars.forEachIndexed { idx, star ->
                when {
                    idx < fullRedStarsCount -> star.setStarToImageView(R.drawable.ic_star_red)
                    idx == fullRedStarsCount && halfStarCount >= 1 -> star.setStarToImageView(R.drawable.ic_star_half)
                    else -> star.setStarToImageView(R.drawable.ic_star_grey)
                }
            }
        }

        private fun ImageView.setStarToImageView(id: Int) {
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, id, null))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            movie =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getParcelable(MainActivity.FRAGMENT_MOVIE, Movie::class.java)
                } else {
                    it.get(MainActivity.FRAGMENT_MOVIE)
                } as Movie?
        }

        val rv = view.findViewById<RecyclerView>(R.id.rv_movie_details)
        val adapter = MovieDetailsAdapter(view.context, movie!!)
        adapter.setClickListener(listener!!)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        rv.adapter = adapter
        rv.hasFixedSize()
        rv.layoutManager = layoutManager
        rv.addItemDecoration(CastItemDecoration(spacingInPixels))
        view.apply {
            movie?.rating?.let { setRatingStars(it, this) }
            setBackButtonClickListener(view, listener)
            setMovieDetailsView(view)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(FRAGMENT_MOVIE, movie)
        super.onSaveInstanceState(outState)
    }

    private fun setBackButtonClickListener(view: View, listener: TransactionFragmentClicks?) {
        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            listener?.addFragment("FragmentMovieList", null)
        }
    }

    private fun setMovieDetailsView(view: View) {
        val movieImage: ImageView = view.findViewById(R.id.iv_poster_background)
        movie?.let {
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

class CastItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
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