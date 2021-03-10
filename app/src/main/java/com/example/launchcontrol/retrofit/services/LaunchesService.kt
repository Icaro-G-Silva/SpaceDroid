package com.example.launchcontrol.retrofit.services

import com.example.launchcontrol.retrofit.entities.Launches
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchesService {
    @GET("launches")
    fun getLaunches(@Query("launch_year") launchYear: String): Call<List<Launches>>
}