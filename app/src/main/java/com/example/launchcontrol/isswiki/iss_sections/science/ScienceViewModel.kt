package com.example.launchcontrol.isswiki.iss_sections.science

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.launchcontrol.R

class ScienceViewModel: ViewModel() {

    private val _title = MutableLiveData<Int>().apply {
        value = R.string.iss_science_title
    }

    val title: LiveData<Int> = _title
}