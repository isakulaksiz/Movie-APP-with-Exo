package com.isilon.beinconnect.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isilon.beinconnect.R
import com.isilon.beinconnect.data.model.Result

class MainAdapter(private val data: ArrayList<Result>): RecyclerView.Adapter<MainAdapter.DataViewHolder>()  {
    companion object{
        var mainMovieImg: ArrayList<String> = ArrayList()
    }
    class DataViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView
        val imageViewAvatar: ImageView
        init {
            title = view.findViewById(R.id.textViewTitle)
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar)

        }
        fun bind(data: Result){

            title.text = data.original_title
            Log.e("title", data.original_title)

            //mainMovieImg.add("http://image.tmdb.org/t/p/w185/"+data.backdrop_path)
            //Log.e("temp", mainMovieImg.get(0))
            Glide.with(imageViewAvatar.context)
                .load("http://image.tmdb.org/t/p/w185/"+data.poster_path)
                .into(imageViewAvatar)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        return MainAdapter.DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.DataViewHolder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int {
        Log.d("count", data.size.toString())
        return data.size
    }
    fun addData(list: List<Result>){
        data.addAll(list)
    }


}