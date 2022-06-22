package com.isilon.beinconnect.ui.main.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.androidnetworking.AndroidNetworking
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.isilon.beinconnect.ui.main.adapter.PagesAdapter


class MainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.isilon.beinconnect.R.layout.activity_main)

        tabLayout = findViewById(com.isilon.beinconnect.R.id.tabLayout)
        viewPager = findViewById(com.isilon.beinconnect.R.id.viewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Yabancı Film"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Yerli Film"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Betimleme"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL


        val adapter = PagesAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        //viewPager?.setAdapter(adapter)

        viewPager?.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        AndroidNetworking.initialize(applicationContext)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }
}