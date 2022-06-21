package com.isilon.beinconnect.data.api

import com.isilon.beinconnect.data.model.Model
import io.reactivex.Single

class ApiHelper(private val apiService: ApiService) {
    fun searchMoviesFromQuery(page: Int): Single<Model>?{
        return apiService.searchMoviesFromQuery(page)
    }
}