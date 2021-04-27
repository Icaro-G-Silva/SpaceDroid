package com.example.launchcontrol.spacex.data

import android.util.Log
import com.example.launchcontrol.spacex.domain.entities.Launches
import com.example.launchcontrol.spacex.data.spacex_retrofit.services.LaunchesService
import com.example.launchcontrol.generals.utils.SharedPreferencesReduced
import retrofit2.Call
import retrofit2.Callback

class LaunchesRepository(private val sharedPreferences: SharedPreferencesReduced, private val retrofit: LaunchesService) {

    fun getLaunches(callback: Callback<List<Launches>>, launchYearContent: String) {
        val launchYearPref = getLaunchYearOfPref(launchYearContent)
        if(launchYearPref.isEmpty()) {
            setLaunchYearOnPref(launchYearContent)
        } else {
//            Log.d("Year already searched", launchYearPref)
        }
        val call: Call<List<Launches>> = retrofit.getLaunches(launchYearContent)
        call.enqueue(callback)
    }

    private fun setLaunchYearOnPref(launchYear: String) = sharedPreferences.setPreference(launchYear, launchYear)
    private fun getLaunchYearOfPref(launchYear: String): String = sharedPreferences.getPreference(launchYear, "")
}