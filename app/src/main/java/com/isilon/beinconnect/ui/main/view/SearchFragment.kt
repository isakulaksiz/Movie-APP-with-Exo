package com.isilon.beinconnect.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
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
import com.isilon.beinconnect.databinding.FragmentSearchBinding
import com.isilon.beinconnect.ui.base.ViewModelFactory
import com.isilon.beinconnect.ui.main.adapter.SearchAdapter
import com.isilon.beinconnect.ui.main.viewmodel.BeinConnectViewModel
import com.isilon.beinconnect.utils.Constants
import com.isilon.beinconnect.utils.Status


class SearchFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var btnCancel: Button

    private lateinit var searchViewModel: BeinConnectViewModel
    private lateinit var binding: FragmentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchView = view.findViewById(R.id.search_view)
        progressBar = view.findViewById(R.id.progressBarSearch)
        recyclerView = view.findViewById(R.id.searchRecyclerView)
        btnCancel = view.findViewById(R.id.btn_cancel)

        setUpUI()
        setUpViewModel()
        setUpObserver()



        btnCancel.setOnClickListener {
            searchView.clearFocus()
            searchView.setQuery("", false)
            searchView.isIconified = true
        }
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
        searchViewModel.data.observe(requireActivity(), Observer {
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
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3,
            GridLayoutManager.HORIZONTAL,false)
        adapter = SearchAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerView.adapter = adapter
    }
}