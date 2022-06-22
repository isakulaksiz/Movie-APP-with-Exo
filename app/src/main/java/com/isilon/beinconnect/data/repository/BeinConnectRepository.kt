package com.isilon.beinconnect.data.repository

import android.util.Log
import com.isilon.beinconnect.data.api.ApiHelper
import com.isilon.beinconnect.data.model.Model
import io.reactivex.Single

class BeinConnectRepository(private val apiHelper: ApiHelper) {
    fun searchMoviesFromQuery(api_key: String, page: Int): Single<Model>? {
        Log.e("queryRepository",page.toString())

        return apiHelper.searchMoviesFromQuery(api_key, page)
    }
}