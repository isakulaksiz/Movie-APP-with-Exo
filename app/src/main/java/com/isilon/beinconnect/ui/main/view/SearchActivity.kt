package com.isilon.beinconnect.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.isilon.beinconnect.R
import com.isilon.beinconnect.data.api.ApiHelper
import com.isilon.beinconnect.data.api.ApiServiceImpl
import com.isilon.beinconnect.data.model.Result
import com.isilon.beinconnect.ui.base.ViewModelFactory
import com.isilon.beinconnect.ui.main.adapter.SearchAdapter
import com.isilon.beinconnect.ui.main.viewmodel.BeinConnectViewModel
import com.isilon.beinconnect.utils.Constants
import com.isilon.beinconnect.utils.Status

class SearchActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter

    private lateinit var searchViewModel: BeinConnectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchView = findViewById(R.id.search_view)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.searchRecyclerView)

        setUpUI()
        setUpViewModel()
        setUpObserver()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.getMoviesFromTitle(Constants.API_KEY, query)
                    println("queryTest"+ query)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchViewModel.getMoviesFromTitle(Constants.API_KEY, newText)
                    println("queryTest"+ newText)
                }

                return false
            }
        })
    }

    private fun setUpObserver() {
        searchViewModel.data.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { data -> renderList(data.results) }
                    Log.e("STATUS/OK", it.toString())
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Log.e("STATUS/ERR", it.toString())
                }
            }
        })
    }

    private fun renderList(data: List<Result>) {
        val list = ArrayList<Result>()
        list.addAll(data)
        adapter.changeData(list)
    }

    private fun setUpViewModel() {
        searchViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        )
            .get(BeinConnectViewModel::class.java)
    }

    private fun setUpUI() {
        recyclerView.layoutManager = GridLayoutManager(this,2,
            GridLayoutManager.VERTICAL,false)
        adapter = SearchAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerView.adapter = adapter
    }

    // search movie ex api req
    //https://api.themoviedb.org/3/search/movie/?api_key=92b975410b217a6ca13099b35bf4be46&page=1&query=fist fighter
}