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

class SearchAdapter(private val data: ArrayList<Result>): RecyclerView.Adapter<SearchAdapter.DataViewHolder>() {

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

            Glide.with(imageViewAvatar.context)
                .load("http://image.tmdb.org/t/p/w185/"+data.poster_path)
                .into(imageViewAvatar)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        return SearchAdapter.DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        Log.d("SearchCount", data.size.toString())
        return data.size
    }

    fun addData(list: List<Result>){
        data.addAll(list)
    }

    fun changeData(newList: ArrayList<Result>){
        this.data.clear()
        this.data.addAll(newList)
        notifyDataSetChanged()
    }
}