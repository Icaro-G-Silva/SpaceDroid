package com.example.launchcontrol.isswiki.iss_sections.structure.ui.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.launchcontrol.R

class StructureViewModel: ViewModel() {

    private val _title = MutableLiveData<Int>().apply {
        value = R.string.iss_struct_title
    }
    val text: LiveData<Int> = _title
}