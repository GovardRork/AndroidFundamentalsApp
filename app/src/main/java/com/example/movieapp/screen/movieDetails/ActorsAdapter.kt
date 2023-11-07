package com.example.movieapp.screen.movieDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.TransactionFragmentClicks
import com.example.movieapp.model.Actor

class ActorsAdapter:RecyclerView.Adapter<ActorViewHolder>() {
    private var listener: TransactionFragmentClicks? = null
    private var actors:List<Actor>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.viewholder_actor, parent, false)
        setItemWidth(itemView)
        return ActorViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return actors?.size?:0
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bindActor(getItem(position))
        //TODO clicklistener for ActorDetails
//        holder.itemView.findViewById<ImageView>(R.id.iv_actor_avatar).setOnClickListener{
//            listener?.addFragment("FragmentMovieList", null)
//        }
    }
    private fun calculateWidthDP(itemView:View):Float{
        val rowItemCount = 4 // Assuming you want 4 items in a row
        val spacingDp = 8 // Default spacing for this views
        val rvSpacingDp = 16
        val totalSpacingDp = 2 * rvSpacingDp + 3 * spacingDp //Margins: start + end from recyclerview + spacing between 3 itemViews

        // Calculate the desired width for each item in the row in DP
        val screenWidthInDp =
            itemView.context.resources.displayMetrics.run { widthPixels / density } - totalSpacingDp
        return screenWidthInDp / rowItemCount
    }

    // Set the width for each item
    private fun setItemWidth(itemView:View){
        val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.width = itemView.context.resources
            .displayMetrics.run { calculateWidthDP(itemView) * density }
            .toInt()
        itemView.layoutParams = layoutParams
    }

    fun setClickListener(listener: TransactionFragmentClicks) {
        this.listener = listener
    }
    fun updateActors(data:List<Actor>){
        actors = data
    }

    private fun getItem(position: Int): Actor? = actors?.get(position)
}