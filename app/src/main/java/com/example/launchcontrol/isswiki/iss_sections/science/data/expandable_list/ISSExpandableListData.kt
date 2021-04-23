package com.example.launchcontrol.isswiki.iss_sections.science.data.expandable_list

import com.example.launchcontrol.isswiki.iss_sections.science.domain.entities.ISSlist

class ISSExpandableListData(private val ISSlistData: ISSlist) {
    fun getData(): HashMap<String, List<String>> {
        val expandableListDetail: HashMap<String, List<String>> = HashMap()

        ISSlistData.experiments.forEach { experiment ->
            val experiments: MutableList<String> = ArrayList()
            experiment.items.forEach {experimentItem ->
                experiments.add(experimentItem.item)
            }
            expandableListDetail[experiment.title] = experiments
        }

        return expandableListDetail
    }
}