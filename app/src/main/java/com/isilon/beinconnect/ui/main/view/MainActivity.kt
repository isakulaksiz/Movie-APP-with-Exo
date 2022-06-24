package com.isilon.beinconnect.ui.main.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.androidnetworking.AndroidNetworking
import com.google.android.material.tabs.TabLayout
import com.isilon.beinconnect.R
import com.isilon.beinconnect.ui.main.adapter.PagesAdapter

class MainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private lateinit var backButton: ImageView
    private lateinit var searchButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.isilon.beinconnect.R.layout.activity_main)

        tabLayout = findViewById(com.isilon.beinconnect.R.id.tabLayout)
        viewPager = findViewById(com.isilon.beinconnect.R.id.viewPager)
        backButton = findViewById(com.isilon.beinconnect.R.id.iv_backBtn)
        searchButton = findViewById(com.isilon.beinconnect.R.id.search_btn)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Yabancı Film"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Yerli Film"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Betimleme"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL


        val adapter = PagesAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager?.setAdapter(adapter)

        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

        backButton.setOnClickListener {
            backPressedBtn()
        }

        searchButton.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)

        }


    }


    private fun backPressedBtn() {
        onBackPressed()
    }
}
