package com.example.launchcontrol.isswiki.iss_sections.home.domain.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.R
import com.example.launchcontrol.isswiki.iss_sections.home.domain.entities.ListText

class RecyclerViewISSHomeAdapter(val dataset: List<ListText>): RecyclerView.Adapter<RecyclerViewISSHomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ListText) {
            val titleView: TextView = itemView.findViewById(R.id.itemTitle)
            val contentView: TextView = itemView.findViewById(R.id.itemContent)

            titleView.text = item.title
            contentView.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_itemlist, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}