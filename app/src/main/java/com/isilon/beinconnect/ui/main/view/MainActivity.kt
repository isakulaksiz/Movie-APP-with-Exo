package com.isilon.beinconnect.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.isilon.beinconnect.R
import com.isilon.beinconnect.data.api.ApiHelper
import com.isilon.beinconnect.data.api.ApiServiceImpl
import com.isilon.beinconnect.data.model.Result
import com.isilon.beinconnect.ui.base.ViewModelFactory
import com.isilon.beinconnect.ui.main.adapter.MainAdapter
import com.isilon.beinconnect.ui.main.viewmodel.BeinConnectViewModel
import com.isilon.beinconnect.utils.Constants
import com.isilon.beinconnect.utils.Status

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(applicationContext)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }
}