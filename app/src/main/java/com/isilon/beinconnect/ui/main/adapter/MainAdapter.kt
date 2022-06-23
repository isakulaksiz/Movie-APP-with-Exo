package com.isilon.beinconnect.ui.main.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isilon.beinconnect.R
import com.isilon.beinconnect.data.model.Result
import com.isilon.beinconnect.ui.main.view.MainFragmentDirections


class MainAdapter(private val data: ArrayList<Result>): RecyclerView.Adapter<MainAdapter.DataViewHolder>()  {
    companion object{
        var mainMovieImg: ArrayList<String> = ArrayList()
    }
    private var mRecyclerView : RecyclerView? = null

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

            if(mainMovieImg.size<3)
                mainMovieImg.addAll(listOf("http://image.tmdb.org/t/p/w185/"+data.backdrop_path))
            Log.e("ViewPagerImg", mainMovieImg.toString())

            Glide.with(imageViewAvatar.context)
                .load("http://image.tmdb.org/t/p/w185/"+data.poster_path)
                .into(imageViewAvatar)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        return DataViewHolder(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null// to avoid memory leak
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])

        Log.e("holder", holder.toString())
        Log.e("position", position.toString())
        holder.itemView.setOnClickListener{
            val resultData = data[position]
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment()
            action.releaseDate = resultData.release_date
            action.avatar = resultData.poster_path
            action.adult = resultData.adult
            Log.e("ActionRelease",action.releaseDate.toString())
            Log.e("ActionAvatar",action.avatar.toString())
            Log.e("ActionAdult",action.adult.toString())

            var temp = "alperen"
            action.releaseDate = temp

            try {
                //val navController = Navigation.findNavController(holder.itemView)
                //navController.navigate(action)
                (holder.itemView.context as AppCompatActivity).findNavController(R.id.fragmentContainerView).navigate(action)
                //val navController = Navigation.findNavController(holder.itemView)
                //navController.navigate(action)
            }catch (e: Exception){
                e.printStackTrace()
            }

        }
    }

    override fun getItemCount(): Int {
        Log.d("count", data.size.toString())
        return data.size
    }
    fun addData(list: List<Result>){
        data.addAll(list)
    }

}