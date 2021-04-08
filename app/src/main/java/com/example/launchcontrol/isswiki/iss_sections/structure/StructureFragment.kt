package com.example.launchcontrol.isswiki.iss_sections.structure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.launchcontrol.R

class StructureFragment : Fragment() {

    private lateinit var structureViewModel: StructureViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        structureViewModel = ViewModelProvider(this).get(StructureViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_structure, container, false)
        val textView: TextView = root.findViewById(R.id.iss_struct_title)
        structureViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = getString(it)
        })
        return root
    }
}