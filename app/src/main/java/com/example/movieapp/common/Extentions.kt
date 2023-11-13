package com.example.movieapp.common

import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.TransactionClickListener
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.model.Genre
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

fun List<Genre>?.toString():String{
    val sb = StringBuilder()
    this?.let {
        for(i in it.indices){
            sb.append(it[i].name)
            if(i<it.size-1)
                sb.append(", ")
        }
    }
    return sb.toString()
}
//Create new scope
fun createScope(coroutineContext: CoroutineContext) = CoroutineScope(coroutineContext)

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

fun Fragment.getFragmentClassName(): String {
    return this::class.java.name
}
fun Fragment.setTransactionClickListener(listener: TransactionFragmentClicks) {
    (this as TransactionClickListener).setTransactionClickListener(listener)
}
