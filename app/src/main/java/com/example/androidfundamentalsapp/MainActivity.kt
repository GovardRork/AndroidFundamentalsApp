package com.example.androidfundamentalsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.IllegalArgumentException
import java.util.UUID

class MainActivity : AppCompatActivity(),TransactionFragmentClicks {
    companion object{
        private const val FRAGMENT_ID = "fragment_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            addFragment("FragmentMovieList")
        }
        else{
            //TODO:adapt this piece of code for much movie details
            supportFragmentManager.fragments.last().apply {
                addFragment(this.getFragmentTag())
            }
        }
    }
    private fun createNewFragment(fragmentName:String):Fragment{
        val uuid = UUID.randomUUID().toString()
        val fragment:Fragment  = when(fragmentName){
            "FragmentMovieDetails"->FragmentMovieDetails.newInstance(uuid)
            "FragmentMovieList"->FragmentMovieList.newInstance(uuid)
            else -> throw IllegalArgumentException("Fragment name is unknown: $fragmentName")
        }
        fragment.apply {
            setTransactionClickListener(this@MainActivity)
            setFragmentUUID(uuid)
        }
        return fragment
    }

    override fun addFragment(fragmentName:String) {
        var fragment = supportFragmentManager.findFragmentByTag(fragmentName)
        if(fragment==null){
           fragment = when(fragmentName){
                "FragmentMovieDetails"->createNewFragment(fragmentName)
               "FragmentMovieList"->createNewFragment(fragmentName)
               else -> throw IllegalArgumentException("Fragment name is unknown: $fragmentName")
           }
            fragment.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fl_main,this,fragmentName)
                    .commit()
            }
        }
        else
            fragment.apply {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(fragment.id,this)
                    .commit()
                setTransactionClickListener(this@MainActivity)
            }
    }
}
interface TransactionFragmentClicks{
    fun addFragment(fragmentName:String)
}

fun Fragment.getFragmentTag():String {
    return when(this){
        is FragmentMovieDetails->"FragmentMovieDetails"
        is FragmentMovieList ->"FragmentMovieList"
        else->"UnknownTag"
    }
}
fun Fragment.getFragmentUUID():String {
     val id =  when(this){
        is FragmentMovieDetails->this.uuid
        is FragmentMovieList ->this.uuid
        else->"UnknownId"
    }
    return id?:"ID is null"
}
fun Fragment.setFragmentUUID(fragmentUUID:String){
    when(this){
        is FragmentMovieDetails->uuid = fragmentUUID
        is FragmentMovieList ->uuid = fragmentUUID
    }
}
fun Fragment.setTransactionClickListener(l: TransactionFragmentClicks){
    when(this){
        is FragmentMovieDetails->listener = l
        is FragmentMovieList ->listener = l
    }
}
