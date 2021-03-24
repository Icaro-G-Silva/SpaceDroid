package com.example.launchcontrol.ui.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.launchcontrol.R

class CrewFragment: Fragment() {

    private lateinit var crewViewModel: CrewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        crewViewModel = ViewModelProvider(this).get(CrewViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_crew, container, false)

        val textView: TextView = root.findViewById(R.id.text_crew)
        crewViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

}