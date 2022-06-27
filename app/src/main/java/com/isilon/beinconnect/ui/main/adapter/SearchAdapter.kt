package com.isilon.beinconnect.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isilon.beinconnect.R
import com.isilon.beinconnect.data.model.Result
import com.isilon.beinconnect.ui.main.view.*

class SearchAdapter(private val data: ArrayList<Result>): RecyclerView.Adapter<SearchAdapter.DataViewHolder>() {

    class DataViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView
        val imageViewAvatar: ImageView
        init {
            title = view.findViewById(R.id.textViewTitle)
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar)
        }
        fun bind(data: Result){

            title.text = data.title
            Log.e("title", data.title)

            Glide.with(imageViewAvatar.context)
                .load("http://image.tmdb.org/t/p/w185/"+data.poster_path)
                .into(imageViewAvatar)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
        Log.e("SearchHolder", holder.toString())
        Log.e("SearchPosition", position.toString())
        holder.itemView.setOnClickListener {
            val result = data[position]

            val searchAction = SearchFragmentDirections.actionSearchFragmentToDetailFragment()
            searchAction.title = result.title
            searchAction.avatar = result.poster_path
            searchAction.adult = result.adult
            searchAction.overview = result.overview
            searchAction.originalLanguage = result.original_language
            searchAction.releaseDate = result.release_date

            Log.e("SActionAvatar",searchAction.avatar.toString())
            Log.e("SActionTitle",searchAction.title.toString())
            Log.e("SActionAdult",searchAction.adult.toString())
            Log.e("SActionOverview",searchAction.overview.toString())
            Log.e("SActionLang",searchAction.originalLanguage.toString())
            Log.e("SActionDate",searchAction.releaseDate.toString())

            try {
                //val navController = Navigation.findNavController(holder.itemView)
                //navController.navigate(searchAction)
                (holder.itemView.context as AppCompatActivity).findNavController(R.id.fragmentContainerView).navigate(searchAction)

            }catch (e: Exception){
                e.printStackTrace()
            }


        }
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