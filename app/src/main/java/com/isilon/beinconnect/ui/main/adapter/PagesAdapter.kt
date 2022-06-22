package com.isilon.beinconnect.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.isilon.beinconnect.R
import com.isilon.beinconnect.ui.main.view.DescriptionFragment
import com.isilon.beinconnect.ui.main.view.EnglishMovieFragment
import com.isilon.beinconnect.ui.main.view.TurkishMovieFragment
import java.util.*


class PagesAdapter(var myContext: Context, var fm: FragmentManager, var totalTabs: Int): PagerAdapter()  {
    private lateinit var mLayoutInflater: LayoutInflater
    init {
        mLayoutInflater =
            myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return EnglishMovieFragment()
            }
            1 -> {
                return TurkishMovieFragment()
            }
            2 -> {
                return DescriptionFragment()
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        getItem(position)
        val itemView: View = mLayoutInflater.inflate(R.layout.activity_main, container, false)
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}