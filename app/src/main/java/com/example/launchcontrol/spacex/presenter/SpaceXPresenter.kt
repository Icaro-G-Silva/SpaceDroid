package com.example.launchcontrol.spacex.presenter

import android.annotation.SuppressLint
import com.example.launchcontrol.R
import com.example.launchcontrol.enums.SpaceXEnum
import com.example.launchcontrol.retrofit.RetroFitConfig
import com.example.launchcontrol.retrofit.entities.Launches
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SpaceXPresenter(private val activity: ISpaceXContract.ISpaceXActivity): ISpaceXContract.ISpaceXPresenter {
    private var contentVisibility: Boolean = false

    private val yearPattern: String = SpaceXEnum.YearPattern.YEAR_PATTERN.yearPattern
    private val initialYear: Int = SpaceXEnum.InitialYear.INITIAL_YEAR.year

    private val date: Date = Calendar.getInstance().time
    @SuppressLint("SimpleDateFormat")
    private val actualYear: String = SimpleDateFormat(yearPattern).format(date)
    private lateinit var launchYearContent: String

    private val launches: MutableList<Launches> = ArrayList()

    override fun launchYearListener(launchYearText: String) {
        if(launchYearText.isNotEmpty() && launchYearText.length == 4) {
            if(launchYearText.toInt() >= initialYear && launchYearText.toInt() < actualYear.toInt()) {
                launchYearContent = launchYearText
                getAPIinfo()
            } else {
                activity.showDialog(R.string.mismatch_year_error_message, R.string.mismatch_year_error_title)
            }
        }
    }

    override fun getAPIinfo() {
        toggleRequestVisibility()
        val call: Call<List<Launches>> = RetroFitConfig().getLaunchesService().getLaunches(launchYearContent)
        call.enqueue(object: Callback<List<Launches>> {
            override fun onResponse(call: Call<List<Launches>>?, response: Response<List<Launches>>?) {
                if(response?.isSuccessful!!) {
                    toggleRequestVisibility()
                    launches.clear()
                    response.body().forEach { launch -> launches.add(launch) }
                    renderRecyclerTrigger()
                }
            }

            override fun onFailure(call: Call<List<Launches>>?, t: Throwable?) {
                activity.setLoadingInvisible()
                activity.showDialog(R.string.api_generics_error_message, R.string.api_generics_error_title)
            }
        })
    }

    override fun toggleRequestVisibility() {
        if(contentVisibility) activity.setContentVisible() else activity.setContentInvisible()
        contentVisibility = !contentVisibility
    }

    override fun renderRecyclerTrigger() {
        activity.renderRecycler(launches)
    }

}