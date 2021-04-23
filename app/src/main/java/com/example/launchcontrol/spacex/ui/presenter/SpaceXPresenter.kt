package com.example.launchcontrol.spacex.ui.presenter

import android.annotation.SuppressLint
import com.example.launchcontrol.R
import com.example.launchcontrol.spacex.domain.entities.Launches
import com.example.launchcontrol.spacex.domain.enums.SpaceXEnum
import com.example.launchcontrol.spacex.data.LaunchesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SpaceXPresenter(private var activity: ISpaceXContract.ISpaceXActivity?, private val launchRepository: LaunchesRepository): ISpaceXContract.ISpaceXPresenter {

    private val yearPattern: String = SpaceXEnum.YearPattern.YEAR_PATTERN.yearPattern
    private val initialYear: Int = SpaceXEnum.InitialYear.INITIAL_YEAR.year

    private val date: Date = Calendar.getInstance().time
    @SuppressLint("SimpleDateFormat")
    private val actualYear: String = SimpleDateFormat(yearPattern).format(date)
    private lateinit var launchYearContent: String

    private val launches: MutableList<Launches> = ArrayList()

    override fun launchYearListener(launchYearText: String) {
        if(launchYearText.isNotEmpty() && launchYearText.length == initialYear.toString().length) {
            if(launchYearText.toInt() >= initialYear && launchYearText.toInt() < actualYear.toInt()) {
                launchYearContent = launchYearText
                getAPIinfo()
            } else {
                activity?.showDialog(R.string.mismatch_year_error_message, R.string.mismatch_year_error_title)
            }
        }
    }

    private fun getAPIinfo() {
        toggleRequestVisibility(false)
        launchRepository.getLaunches(object: Callback<List<Launches>> {
            override fun onResponse(call: Call<List<Launches>>?, response: Response<List<Launches>>?) {
                if(response?.isSuccessful!!) {
                    toggleRequestVisibility(true)
                    launches.clear()
                    response.body().forEach { launch -> launches.add(launch) }
                    renderRecyclerTrigger()
                }
            }

            override fun onFailure(call: Call<List<Launches>>?, t: Throwable?) {
                activity?.setLoadingInvisible()
                activity?.showDialog(R.string.api_generics_error_message, R.string.api_generics_error_title)
            }
        }, launchYearContent)
    }

    private fun toggleRequestVisibility(contentVisibility: Boolean) {
        if(contentVisibility) activity?.setContentVisible() else activity?.setContentInvisible()
    }

    private fun renderRecyclerTrigger() {
        activity?.renderRecycler(launches)
    }

    override fun destroy() {
        activity = null
    }

}