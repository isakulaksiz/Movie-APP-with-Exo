package com.isilon.beinconnect.ui.main.view

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.androidnetworking.AndroidNetworking
import com.google.android.material.tabs.TabLayout
import com.isilon.beinconnect.R
import com.isilon.beinconnect.ui.main.adapter.PagesAdapter
import com.isilon.beinconnect.ui.main.adapter.SearchAdapter
import com.isilon.beinconnect.ui.main.adapter.SearchFragment

class MainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private lateinit var backButton: ImageView
    private lateinit var searchButton: ImageView
    private lateinit var linFragment: LinearLayout
    companion object{
        var isShowSearcDetail: Boolean = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.isilon.beinconnect.R.layout.activity_main)
        tabLayout = findViewById(com.isilon.beinconnect.R.id.tabLayout)
        viewPager = findViewById(com.isilon.beinconnect.R.id.viewPager)
        backButton = findViewById(com.isilon.beinconnect.R.id.iv_backBtn)
        searchButton = findViewById(com.isilon.beinconnect.R.id.search_btn)
        linFragment = findViewById(R.id.ln_fragment)

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
            //val intent = Intent(this,SearchActivity::class.java)
            //startActivity(intent)
            linFragment.visibility = View.GONE

            isShowSearcDetail = true
            //startCountTimer()



            try {
                Handler().postDelayed({
                    linFragment.visibility = View.VISIBLE
                },5000)
            }catch (e: Exception){

            }



            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.searchFragmentContainerView, SearchFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }



    }

    private fun startCountTimer() {
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                    linFragment.visibility = View.VISIBLE
            }
        }.start()
    }



    private fun backPressedBtn() {
        onBackPressed()
    }
}
