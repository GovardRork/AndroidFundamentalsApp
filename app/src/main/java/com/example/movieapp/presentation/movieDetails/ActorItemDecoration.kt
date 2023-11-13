package com.example.movieapp.presentation.movieDetails

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ActorItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
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