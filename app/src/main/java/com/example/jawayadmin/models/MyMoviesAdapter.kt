package com.example.jawayadmin.models

import Film
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jawayadmin.R

class MyMoviesAdapter(private val filmList: ArrayList<Film>, applicationContext: Context) : RecyclerView.Adapter<MyMoviesAdapter.MyViewHolder>() {

    var context = applicationContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_films,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = filmList[position]

        Glide.with(context).load(currentitem.poster_path).into(holder.imgFilm)
        holder.titleFilm.text = currentitem.title
        holder.overviewFilm.text = currentitem.overview

    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val imgFilm : ImageView = itemView.findViewById(R.id.itemImg)
        val titleFilm : TextView = itemView.findViewById(R.id.itemTitle)
        val overviewFilm : TextView = itemView.findViewById(R.id.itemOverview)

    }
}