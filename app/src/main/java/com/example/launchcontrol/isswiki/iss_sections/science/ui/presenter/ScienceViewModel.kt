package com.example.launchcontrol.isswiki.iss_sections.science.ui.presenter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.launchcontrol.R
import com.example.launchcontrol.isswiki.iss_sections.science.domain.entities.ISSlist
import com.example.launchcontrol.isswiki.iss_sections.science.data.ScienceRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ScienceViewModel(application: Application) : AndroidViewModel(application), ScienceContract.ScienceViewModel, KoinComponent {
    private val scienceRepository: ScienceRepository by inject()

    private val _title = MutableLiveData<Int>().apply {
        value = R.string.iss_science_title
    }

    override fun getExpandableListData(): Pair<HashMap<String, List<String>>, List<String>> {
        val issList: ISSlist = scienceRepository.getISSList()
        return scienceRepository.getExpandableList(issList)
    }

    val title: LiveData<Int> = _title
}