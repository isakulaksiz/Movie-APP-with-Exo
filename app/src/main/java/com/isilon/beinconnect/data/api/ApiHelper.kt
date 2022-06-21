package com.isilon.beinconnect.data.api

import android.util.Log
import com.isilon.beinconnect.data.model.Model
import io.reactivex.Single

class ApiHelper(private val apiService: ApiService) {
    fun searchMoviesFromQuery(page: Int): Single<Model>?{
        Log.e("queryApiHelper", page.toString())
        return apiService.searchMoviesFromQuery(page)
    }
}