package com.example.launchcontrol.isswiki.iss_sections.crew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.launchcontrol.R
import com.example.launchcontrol.entities.Menu
import com.example.launchcontrol.utils.GetStringResource

class CrewViewModel(application: Application) : AndroidViewModel(application) {
    private val myStr = GetStringResource(application.baseContext)

    private val _title = MutableLiveData<Int>().apply {
        value = R.string.iss_crew_title
    }

    private val _crewList = MutableLiveData<List<Menu>>().apply {
        value = arrayListOf(
            Menu(myStr.getStringRes(R.string.iss_crew_katerubins), R.drawable.kate_rubins),
            Menu(myStr.getStringRes(R.string.iss_crew_victorglover), R.drawable.victor_glover),
            Menu(myStr.getStringRes(R.string.iss_crew_soichinoguchi), R.drawable.soichi_noguchi),
            Menu(myStr.getStringRes(R.string.iss_crew_sergeyryzhikov), R.drawable.sergey),
            Menu(myStr.getStringRes(R.string.iss_crew_michaelhopkins), R.drawable.michael_hopkins),
            Menu(myStr.getStringRes(R.string.iss_crew_shannonwalker), R.drawable.shannon_walker),
            Menu(myStr.getStringRes(R.string.iss_crew_sergeykud), R.drawable.sergey_kud)
        )
    }

    val title: LiveData<Int> = _title
    val crewList: LiveData<List<Menu>> = _crewList
}