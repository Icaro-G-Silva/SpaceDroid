package com.example.launchcontrol.isswiki.iss_sections.science

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
import com.example.launchcontrol.lists.expandable.issscience.ISSExpandableListAdapter
import com.example.launchcontrol.utils.Dialog

class ScienceFragment: Fragment() {
    private lateinit var scienceViewModel: ScienceViewModel
    private lateinit var expandableListView: ExpandableListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        scienceViewModel = ViewModelProvider(this).get(ScienceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_science, container, false)

        val textView: TextView = root.findViewById(R.id.iss_science_title)
        scienceViewModel.title.observe(viewLifecycleOwner, Observer {
            textView.text = getString(it)
        })

        handleExpandableList(root)

        return root
    }

    private fun handleExpandableList(fragment: View) {
        val expandableListData = scienceViewModel.getExpandableListData()
        val expandableListAdapter: BaseExpandableListAdapter = ISSExpandableListAdapter(fragment.context, expandableListData.second, expandableListData.first)

        expandableListView = fragment.findViewById(R.id.iss_science_expandablelist)
        expandableListView.setAdapter(expandableListAdapter)
        setChildClickListener(expandableListData.first, expandableListData.second)
    }

    private fun setChildClickListener(expandableListDetail: HashMap<String, List<String>>, expandableListTitle: List<String>) {
        expandableListView.setOnChildClickListener { parent, _, groupPosition, childPosition, _ ->
            Dialog(expandableListDetail[expandableListTitle[groupPosition]]!![childPosition], expandableListTitle[groupPosition], parent.context).show()
            false
        }
    }

}