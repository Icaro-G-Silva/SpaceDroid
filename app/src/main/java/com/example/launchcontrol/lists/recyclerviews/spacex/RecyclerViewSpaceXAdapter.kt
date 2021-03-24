package com.example.launchcontrol.lists.recyclerviews.spacex

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.launchcontrol.R
import com.example.launchcontrol.SpaceXContentActivity
import com.example.launchcontrol.retrofit.entities.Launches

class RecyclerViewSpaceXAdapter(private val dataset: List<Launches>): RecyclerView.Adapter<RecyclerViewSpaceXAdapter.SpaceXViewHolder>() {

    class SpaceXViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(launch: Launches) {
            val missionName: TextView = itemView.findViewById(R.id.title_menu)
            val missionIcon: ImageView = itemView.findViewById(R.id.image_menu)
            val button: Button = itemView.findViewById(R.id.button_menu)

            missionName.text = launch.missionName

            Glide.with(itemView.context).load(R.drawable.not_found).into(missionIcon)
            launch.links.missionPatch?.let {
                Glide.with(itemView.context).load(it).into(missionIcon)
            }

            button.setOnClickListener {
                val intent = Intent(itemView.context, SpaceXContentActivity::class.java)
                intent.putExtra("launch", launch)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceXViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return SpaceXViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpaceXViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}