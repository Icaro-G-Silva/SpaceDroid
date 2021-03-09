package com.example.launchcontrol.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchesService {
    @GET("launches")
    fun getLaunches(@Query("launch_year") launchYear: String): Call<List<Launches>>
}