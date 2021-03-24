package com.example.launchcontrol.ui.science

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.launchcontrol.R
import com.example.launchcontrol.utils.GetStringResource

class ScienceViewModel(application: Application) : AndroidViewModel(application) {
    private val myStr = GetStringResource(application.baseContext)

    private val _title = MutableLiveData<String>().apply {
        value = myStr.getStringRes(R.string.iss_science_title)
    }

    val title: LiveData<String> = _title
}