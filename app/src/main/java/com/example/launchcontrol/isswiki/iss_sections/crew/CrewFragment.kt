package com.example.launchcontrol.isswiki.iss_sections.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.R
import com.example.launchcontrol.lists.recyclerviews.isscrew.RecyclerViewISSCrewAdapter

class CrewFragment: Fragment() {

    private lateinit var crewViewModel: CrewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        crewViewModel = ViewModelProvider(this).get(CrewViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_crew, container, false)

        val textView: TextView = root.findViewById(R.id.iss_crew_title)
        crewViewModel.title.observe(viewLifecycleOwner, Observer {
            textView.text = getString(it)
        })

        val recyclerView: RecyclerView = root.findViewById(R.id.iss_crew_recycler)
        crewViewModel.crewList.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = RecyclerViewISSCrewAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.setHasFixedSize(true)
        })

        return root
    }

}