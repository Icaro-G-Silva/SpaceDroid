package com.example.launchcontrol.isswiki.iss_sections.science.ui.presenter

interface ScienceContract {
    interface ScienceViewModel {
        fun getExpandableListData(): Pair<HashMap<String, List<String>>, List<String>>
    }
}