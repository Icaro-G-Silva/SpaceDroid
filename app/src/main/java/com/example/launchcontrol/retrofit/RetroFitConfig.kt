package com.example.launchcontrol.retrofit

import com.example.launchcontrol.retrofit.entities.Launches
import com.example.launchcontrol.retrofit.services.LaunchesService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitConfig() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLaunchesService(): LaunchesService {
        return retrofit.create(LaunchesService::class.java)
    }
}