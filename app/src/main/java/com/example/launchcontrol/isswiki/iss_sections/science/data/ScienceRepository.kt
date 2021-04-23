package com.example.launchcontrol.isswiki.iss_sections.science.data

import com.example.launchcontrol.R
import com.example.launchcontrol.isswiki.iss_sections.science.domain.entities.ISSlist
import com.example.launchcontrol.isswiki.iss_sections.science.data.expandable_list.ISSExpandableListData
import com.example.launchcontrol.generals.utils.JsonReader

class ScienceRepository(private val jsonReader: JsonReader) {

    fun getISSList(): ISSlist {
        return jsonReader.read(R.raw.iss_experiments)
    }

    fun getExpandableList(issList: ISSlist): Pair<HashMap<String, List<String>>, List<String>> {
        val expandableListDetail: HashMap<String, List<String>> = ISSExpandableListData(issList).getData()
        val expandableListTitle: List<String> = ArrayList<String>(expandableListDetail.keys)
        return Pair(expandableListDetail, expandableListTitle)
    }

}