package com.example.androidfundamentalsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMovieDetails: Fragment() {
    val fragmentName: String = "FragmentMovieDetails"
    var uuid:String? = null
    var listener: TransactionFragmentClicks? = null
    companion object{
        const val FRAGMENT_ID = "fragment_id"
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
        view.apply {
            findViewById<TextView>(R.id.tv_back).setOnClickListener {
                listener?.addFragment("FragmentMovieList")
            }
        }
    }
}