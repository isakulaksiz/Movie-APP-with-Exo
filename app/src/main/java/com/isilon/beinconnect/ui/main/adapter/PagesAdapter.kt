package com.isilon.beinconnect.ui.main.adapter

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.isilon.beinconnect.ui.main.view.DescriptionFragment
import com.isilon.beinconnect.ui.main.view.MainFragment
import com.isilon.beinconnect.ui.main.view.TurkishMovieFragment


class PagesAdapter(var myContext: Context, var fm: FragmentManager, internal var totalTabs: Int): FragmentPagerAdapter(fm)  {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return MainFragment()
            }
            1 -> {
                return TurkishMovieFragment()
            }
            2 -> {
                return DescriptionFragment()

            }
            else -> return MainFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}