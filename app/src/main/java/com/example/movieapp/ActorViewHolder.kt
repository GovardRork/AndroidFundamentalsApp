package com.example.movieapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ActorViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    private val actorAvatar: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
    private val actorName: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun bindActor(actor:Actor){
        val requestOptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(100))
        Glide.with(itemView)
            .load(actor.avatar)
            .apply(requestOptions)
//            .placeholder(R.drawable.loading_spinner)
            .into(actorAvatar)
        actorName.text = actor.name
    }


}