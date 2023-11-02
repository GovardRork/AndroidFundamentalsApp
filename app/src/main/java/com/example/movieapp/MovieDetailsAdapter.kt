package com.example.movieapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.model.Actor
import com.example.movieapp.model.Movie

class MovieDetailsAdapter(private var context: Context, private var movieDetails: Movie) :
    RecyclerView.Adapter<ActorViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var listener: TransactionFragmentClicks? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val itemView = inflater.inflate(R.layout.viewholder_actor, parent, false)
        val rowItemCount = 4 // Assuming you want 4 items in a row
        val spacingDp = 8 // Default spacing for this views
        val rvSpacingDp = 16
        val totalSpacingDp =
            2 * rvSpacingDp + 3 * spacingDp //Margins: start + end from recyclerview + spacing between 3 itemViews

        // Calculate the desired width for each item in the row in DP
        val screenWidthInDp =
            context.resources.displayMetrics.run { widthPixels / density } - totalSpacingDp
        val itemWidthInDp = screenWidthInDp / rowItemCount

        // Set the width for each item
        val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.width = context.resources
            .displayMetrics.run { itemWidthInDp * density }
            .toInt()
        itemView.layoutParams = layoutParams
        return ActorViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieDetails.actors?.size?:0
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bindActor(getItem(position))
        //TODO clicklistener for ActorDetails
//        holder.itemView.findViewById<ImageView>(R.id.iv_actor_avatar).setOnClickListener{
//            listener?.addFragment("FragmentMovieList", null)
//        }
    }

    fun setClickListener(listener: TransactionFragmentClicks) {
        this.listener = listener
    }

    private fun getItem(position: Int): Actor? = movieDetails.actors?.get(position)
}