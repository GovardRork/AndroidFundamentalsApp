package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movieapp.common.getFragmentTag
import com.example.movieapp.common.setTransactionClickListener
import com.example.movieapp.screen.movieDetails.MovieDetailsFragment
import com.example.movieapp.screen.movieList.MovieListFragment
import java.util.UUID

class MainActivity : AppCompatActivity(), TransactionFragmentClicks {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment("FragmentMovieList")
        } else {
            //TODO:adapt this piece of code for much movie details
            supportFragmentManager.fragments.last().apply {
                addFragment(getFragmentTag())
            }
        }
    }

    private fun createNewFragment(fragmentName: String): Fragment {
        val uuid = UUID.randomUUID().toString()
        val fragment: Fragment = when (fragmentName) {
            "FragmentMovieDetails" -> MovieDetailsFragment.newInstance(uuid)
            "FragmentMovieList" -> MovieListFragment.newInstance(uuid)
            else -> throw IllegalArgumentException("Fragment name is unknown: $fragmentName")
        }
        fragment.setTransactionClickListener(this@MainActivity)
        return fragment
    }

    override fun addFragment(fragmentName: String) {
        var fragment = supportFragmentManager.findFragmentByTag(fragmentName)
        if (fragment == null) {
            // Create new fragment if it not exists
            fragment = when (fragmentName) {
                "FragmentMovieDetails" -> createNewFragment(fragmentName)
                "FragmentMovieList" -> createNewFragment(fragmentName)
                else -> throw IllegalArgumentException("Fragment name is unknown: $fragmentName")
            }
            fragment.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fl_main, this, fragmentName)
                    .commit()
            }
        } else { // Replace existing fragment on the top of stack
            fragment.apply {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(fragment.id, this)
                    .commit()
                setTransactionClickListener(this@MainActivity)
            }
        }

    }
}

interface TransactionFragmentClicks {
    fun addFragment(fragmentName: String)
}


