package com.example.launchcontrol.isswiki.iss_sections.science.ui

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
import com.example.launchcontrol.isswiki.iss_sections.science.ui.presenter.ScienceViewModel
import com.example.launchcontrol.isswiki.iss_sections.science.domain.expandable_list.ISSExpandableListAdapter
import com.example.launchcontrol.generals.utils.Dialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ScienceFragment: Fragment() {
    private val scienceViewModel: ScienceViewModel by viewModel{parametersOf(this@ScienceFragment)}
    private lateinit var expandableListView: ExpandableListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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