package com.example.movieapp

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movieapp.model.Movie
import java.util.UUID

class MainActivity : AppCompatActivity(), TransactionFragmentClicks {
    companion object {
        private const val FRAGMENT_ID = "fragment_id"
        const val FRAGMENT_MOVIE = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment("FragmentMovieList", null)
        } else {
            //TODO:adapt this piece of code for much movie details
            supportFragmentManager.fragments.last().apply {
                addFragment(this.getFragmentTag(), null)
            }
        }
    }

    private fun createNewFragment(fragmentName: String): Fragment {
        val uuid = UUID.randomUUID().toString()
        val fragment: Fragment = when (fragmentName) {
            "FragmentMovieDetails" -> FragmentMovieDetails.newInstance(uuid)
            "FragmentMovieList" -> FragmentMovieList.newInstance(uuid)
            else -> throw IllegalArgumentException("Fragment name is unknown: $fragmentName")
        }
        fragment.apply {
            setTransactionClickListener(this@MainActivity)
            setFragmentUUID(uuid)
        }
        return fragment
    }

    override fun addFragment(fragmentName: String, transferData: Bundle?) {
        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                transferData?.getParcelable(FRAGMENT_MOVIE, Movie::class.java)
            } else {
                transferData?.get(FRAGMENT_MOVIE)
            } as Movie?

        var fragment = supportFragmentManager.findFragmentByTag(fragmentName)
        if (fragment == null) {
            fragment = when (fragmentName) {
                "FragmentMovieDetails" -> createNewFragment(fragmentName)
                "FragmentMovieList" -> createNewFragment(fragmentName)
                else -> throw IllegalArgumentException("Fragment name is unknown: $fragmentName")
            }
            fragment.apply {
                if (fragment is FragmentMovieDetails)
                    fragment.movie = movie
                supportFragmentManager.beginTransaction()
                    .add(R.id.fl_main, this, fragmentName)
                    .commit()
            }
        } else {
            fragment.apply {
                if (fragment is FragmentMovieDetails)
                    fragment.movie = movie
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
    fun addFragment(fragmentName: String, transferData: Bundle?)
}

fun Fragment.getFragmentTag(): String {
    return when (this) {
        is FragmentMovieDetails -> "FragmentMovieDetails"
        is FragmentMovieList -> "FragmentMovieList"
        else -> "UnknownTag"
    }
}

fun Fragment.getFragmentUUID(): String {
    val id = when (this) {
        is FragmentMovieDetails -> this.uuid
        is FragmentMovieList -> this.uuid
        else -> "UnknownId"
    }
    return id ?: "ID is null"
}

fun Fragment.setFragmentUUID(fragmentUUID: String) {
    when (this) {
        is FragmentMovieDetails -> uuid = fragmentUUID
        is FragmentMovieList -> uuid = fragmentUUID
    }
}

fun Fragment.setTransactionClickListener(l: TransactionFragmentClicks) {
    when (this) {
        is FragmentMovieDetails -> listener = l
        is FragmentMovieList -> listener = l
    }
}
