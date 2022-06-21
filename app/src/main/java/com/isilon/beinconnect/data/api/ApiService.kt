package com.isilon.beinconnect.data.api

import com.isilon.beinconnect.data.model.Model
import com.isilon.beinconnect.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/discover/movie/${Constants.API_KEY}")
    fun searchMoviesFromQuery(
        @Query("page") limit: Int
    ): Single<Model>
}