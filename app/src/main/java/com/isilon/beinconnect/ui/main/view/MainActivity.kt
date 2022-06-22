package com.isilon.beinconnect.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    private lateinit var mainViewModel: BeinConnectViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(applicationContext)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        setUpUI()
        setUpViewModel()
        setUpObserver()
        mainViewModel.fetchMovies(Constants.API_KEY,Constants.FIRST_PAGE)
    }

    private fun setUpObserver() {
        mainViewModel.data.observe(this, Observer {
            when (it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { data -> renderList(data.results) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING ->{
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(BeinConnectViewModel::class.java)
    }

    private fun setUpUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )

        recyclerView.adapter = adapter

    }

    private fun renderList(data: List<Result>) {
        adapter.addData(data)
        adapter.notifyDataSetChanged()
    }
}