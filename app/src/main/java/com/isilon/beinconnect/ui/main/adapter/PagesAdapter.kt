package com.isilon.beinconnect.ui.main.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.isilon.beinconnect.ui.main.view.DescriptionFragment
import com.isilon.beinconnect.ui.main.view.EnglishMovieFragment
import com.isilon.beinconnect.ui.main.view.MainActivity
import com.isilon.beinconnect.ui.main.view.TurkishMovieFragment

class PagesAdapter
    (
    var myContext: Context, var fm: FragmentManager, var totalTabs: Int
) {
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

    fun getCount(): Int {
        return totalTabs
    }
}