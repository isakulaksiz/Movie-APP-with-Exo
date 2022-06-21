package com.isilon.beinconnect.data.api

import android.util.Log
import com.isilon.beinconnect.data.model.Model
import com.isilon.beinconnect.utils.Constants
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl: ApiService {
    override fun searchMoviesFromQuery(limit: Int): Single<Model> {
        return Rx2AndroidNetworking.get(Constants.BASE_URL)
            .addQueryParameter("page", limit.toString())
            .build()
            .getObjectSingle(Model::class.java)
    }
}