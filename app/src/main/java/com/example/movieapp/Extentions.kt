package com.example.movieapp

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

fun createScope(coroutineContext: CoroutineContext) = CoroutineScope(coroutineContext)
