package com.isilon.beinconnect.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.isilon.beinconnect.R
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerAdapter(var context: Context, var images: ArrayList<String>) : PagerAdapter() {
    //Layout Inflater
    private var mLayoutInflater: LayoutInflater
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater.inflate(R.layout.item, container, false)
        val imageView = itemView.findViewById<View>(R.id.imageViewMain) as ImageView
        Glide.with(context)
            .load(images[position])
            .into(imageView)
        //imageView.setImageResource(images[position])
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
    init {
        images = images
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}