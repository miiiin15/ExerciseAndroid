package com.miiiin15.feature.home.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {


    @GET("local.json?")
    suspend fun searchLocal(
        @Query("query") keyword: String,
        @Query("display") display: Int = 10
    ): Any

}