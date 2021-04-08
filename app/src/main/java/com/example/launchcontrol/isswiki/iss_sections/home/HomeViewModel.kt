package com.example.launchcontrol.isswiki.iss_sections.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.launchcontrol.R
import com.example.launchcontrol.entities.ListText
import com.example.launchcontrol.utils.GetStringResource

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val myStr = GetStringResource(application.baseContext)

    private val _title = MutableLiveData<Int>().apply {
        value = R.string.iss_home_title
    }

    private val _subtitlePurpose = MutableLiveData<Int>().apply {
        value = R.string.iss_home_subtitle_purpose
    }

    private val _purposeContent = MutableLiveData<Int>().apply {
        value = R.string.iss_home_content_purpose
    }

    private val _subtitleInfos = MutableLiveData<Int>().apply {
        value = R.string.iss_home_subtitle_infos
    }

    private val _list = MutableLiveData<List<ListText>>().apply {
        value = arrayListOf(
                ListText(myStr.getStringRes(R.string.iss_home_list_orbitalvelocity), myStr.getStringRes(R.string.iss_home_list_orbitalvelocitycontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_orbitalperiod), myStr.getStringRes(R.string.iss_home_list_orbitalperiodcontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_orbitsperday), myStr.getStringRes(R.string.iss_home_list_orbitsperdaycontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_orbitalinclination), myStr.getStringRes(R.string.iss_home_list_orbitalinclinationcontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_apogee), myStr.getStringRes(R.string.iss_home_list_apogeecontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_perigee), myStr.getStringRes(R.string.iss_home_list_perigeecontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_mass), myStr.getStringRes(R.string.iss_home_list_masscontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_length), myStr.getStringRes(R.string.iss_home_list_lengthcontent)),
                ListText(myStr.getStringRes(R.string.iss_home_list_width), myStr.getStringRes(R.string.iss_home_list_widthcontent))
        )
    }

    val title: LiveData<Int> = _title
    val subtitlePurpose: LiveData<Int> = _subtitlePurpose
    val purposeContent: LiveData<Int> = _purposeContent
    val subtitleInfos: LiveData<Int> = _subtitleInfos
    val listItems: LiveData<List<ListText>> = _list
}