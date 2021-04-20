package com.example.launchcontrol.isswiki.iss_sections.science

import com.example.launchcontrol.R
import com.example.launchcontrol.entities.jsonentities.ISSlist
import com.example.launchcontrol.lists.expandable.issscience.ISSExpandableListData
import com.example.launchcontrol.utils.JsonReader

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