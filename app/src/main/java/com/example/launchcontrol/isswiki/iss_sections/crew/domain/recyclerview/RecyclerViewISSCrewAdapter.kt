package com.example.launchcontrol.isswiki.iss_sections.crew.domain.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.R
import com.example.launchcontrol.generals.entities.Menu

class RecyclerViewISSCrewAdapter(private val dataset: List<Menu>): RecyclerView.Adapter<RecyclerViewISSCrewAdapter.ISSCrewViewHolder>() {

    class ISSCrewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Menu) {
            val nameView: TextView = itemView.findViewById(R.id.iss_crew_item_name)
            val imageView: ImageView = itemView.findViewById(R.id.iss_crew_item_image)

            nameView.text = item.title
            imageView.setImageResource(item.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ISSCrewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.iss_crew_item, parent, false)
        return ISSCrewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ISSCrewViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}