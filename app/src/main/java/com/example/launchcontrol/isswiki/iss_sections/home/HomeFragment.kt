package com.example.launchcontrol.isswiki.iss_sections.home

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
import com.example.launchcontrol.lists.recyclerviews.isshome.RecyclerViewISSHomeAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val title: TextView = root.findViewById(R.id.iss_home_title)
        val subtitlePurpose: TextView = root.findViewById(R.id.iss_home_subtitle_purpose)
        val contentPurpose: TextView = root.findViewById(R.id.iss_home_content_purpose)
        val subtitleInfos: TextView = root.findViewById(R.id.iss_home_subtitle_Infos)
        val recyclerViewList: RecyclerView = root.findViewById(R.id.iss_home_recyclerViewList)

        homeViewModel.title.observe(viewLifecycleOwner, Observer {
            title.text = getString(it)
        })
        homeViewModel.subtitlePurpose.observe(viewLifecycleOwner, Observer {
            subtitlePurpose.text = getString(it)
        })
        homeViewModel.purposeContent.observe(viewLifecycleOwner, Observer {
            contentPurpose.text = getString(it)
        })
        homeViewModel.subtitleInfos.observe(viewLifecycleOwner, Observer {
            subtitleInfos.text = getString(it)
        })
        homeViewModel.listItems.observe(viewLifecycleOwner, Observer {
            recyclerViewList.adapter = RecyclerViewISSHomeAdapter(it)
            recyclerViewList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewList.setHasFixedSize(true)
        })

        return root
    }
}