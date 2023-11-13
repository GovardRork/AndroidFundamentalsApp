package com.example.movieapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.movieapp.TransactionClickListener
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.common.SharedViewModel
import com.example.movieapp.common.ViewModelFactory

open class BaseFragment(fragmentMovieList: Int) :Fragment(fragmentMovieList),TransactionClickListener {
    lateinit var listener: TransactionFragmentClicks
    protected val sharedViewModel: SharedViewModel by activityViewModels { ViewModelFactory() }
    override fun setTransactionClickListener(listener: TransactionFragmentClicks) {
        this.listener = listener
    }
}