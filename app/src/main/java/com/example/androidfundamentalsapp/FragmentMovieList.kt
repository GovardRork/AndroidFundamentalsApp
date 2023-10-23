package com.example.androidfundamentalsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMovieList : Fragment() {
    val fragmentName: String = "FragmentMovieList"
    var uuid: String? = null
    var listener: TransactionFragmentClicks? = null
    companion object {
        const val FRAGMENT_ID = "fragment_id"
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
        view.apply {
            findViewById<ImageView>(R.id.tv_movieListBackground).setOnClickListener {
                listener?.addFragment("FragmentMovieDetails")
            }
        }
    }
}