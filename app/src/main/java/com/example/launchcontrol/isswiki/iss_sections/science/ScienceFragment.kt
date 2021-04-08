package com.example.launchcontrol.isswiki.iss_sections.science

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.launchcontrol.R
import com.example.launchcontrol.entities.jsonentities.ISSlist
import com.example.launchcontrol.lists.expandable.issscience.ISSExpandableListAdapter
import com.example.launchcontrol.lists.expandable.issscience.ISSExpandableListData
import com.example.launchcontrol.utils.Dialog
import com.example.launchcontrol.utils.JsonReader

class ScienceFragment: Fragment() {
    private lateinit var scienceViewModel: ScienceViewModel

    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListDetail: HashMap<String, List<String>>
    private lateinit var expandableListAdapter: BaseExpandableListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        scienceViewModel = ViewModelProvider(this).get(ScienceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_science, container, false)

        handleExpandableList(root)

        val textView: TextView = root.findViewById(R.id.iss_science_title)
        scienceViewModel.title.observe(viewLifecycleOwner, Observer {
            textView.text = getString(it)
        })

        return root
    }

    private fun handleExpandableList(fragment: View) {
        val jsonData = getJsonData(fragment.context)

        expandableListView = fragment.findViewById(R.id.iss_science_expandablelist)
        expandableListDetail = ISSExpandableListData(jsonData).getData()
        expandableListTitle = ArrayList<String>(expandableListDetail.keys)
        expandableListAdapter = ISSExpandableListAdapter(fragment.context, expandableListTitle, expandableListDetail)

        expandableListView.setAdapter(expandableListAdapter)
        setChildClickListener(fragment.context)
    }

    private fun getJsonData(context: Context): ISSlist {
        return JsonReader(R.raw.iss_experiments, context).read()
    }

    private fun setChildClickListener(context: Context) {
        expandableListView.setOnChildClickListener(object: ExpandableListView.OnChildClickListener {
            override fun onChildClick(parent: ExpandableListView?, v: View?, groupPosition: Int, childPosition: Int, id: Long): Boolean {
                Dialog(expandableListDetail[expandableListTitle[groupPosition]]!![childPosition], expandableListTitle[groupPosition], context).show()
                return false
            }
        })
    }

}