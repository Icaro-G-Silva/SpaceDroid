package com.example.launchcontrol.spacex.data

import com.example.launchcontrol.retrofit.RetroFitConfig
import com.example.launchcontrol.retrofit.entities.Launches
import retrofit2.Call
import retrofit2.Callback

class LaunchesRepository {

    fun getLaunches(callback: Callback<List<Launches>>, launchYearContent: String) {
        val call: Call<List<Launches>> = RetroFitConfig().getLaunchesService().getLaunches(launchYearContent)
        call.enqueue(callback)
    }

}