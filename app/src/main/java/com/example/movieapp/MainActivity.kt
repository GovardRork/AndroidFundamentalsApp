package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movieapp.common.getFragmentClassName
import com.example.movieapp.common.setTransactionClickListener
import com.example.movieapp.presentation.MovieFragmentFactory
import com.example.movieapp.presentation.MovieFragmentsEnum

class MainActivity : AppCompatActivity(), TransactionFragmentClicks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.fragmentFactory = MovieFragmentFactory()

        if (savedInstanceState == null)
            addFragment(MovieFragmentsEnum.MovieList.className)
        else
            supportFragmentManager.fragments.last().apply {
                addFragment(getFragmentClassName())
            }
    }

    private fun createNewFragment(fragmentName: String): Fragment {
        val fragment = supportFragmentManager.fragmentFactory
            .instantiate(ClassLoader.getSystemClassLoader(),fragmentName)
        fragment.setTransactionClickListener(this@MainActivity)
        return fragment
    }

    override fun addFragment(fragmentName: String) {
        var fragment = supportFragmentManager.findFragmentByTag(fragmentName)
        if (fragment == null) {
            // Create new fragment if it not exists
            fragment = createNewFragment(fragmentName)
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
interface TransactionClickListener {
    fun setTransactionClickListener(listener: TransactionFragmentClicks)
}

