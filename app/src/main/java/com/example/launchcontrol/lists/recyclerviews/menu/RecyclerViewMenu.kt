package com.example.launchcontrol.lists.recyclerviews.menu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.*
import com.example.launchcontrol.entities.Menu

class RecyclerViewMenuAdapter(val dataSet: List<Menu>): RecyclerView.Adapter<RecyclerViewMenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Menu, intent: Intent) {
            val textTitle: TextView = itemView.findViewById(R.id.title_menu)
            textTitle.text = item.title

            val imageIcon: ImageView = itemView.findViewById(R.id.image_menu)
            imageIcon.setImageResource(item.icon)

            val button: Button = itemView.findViewById(R.id.button_menu)

            button.setOnClickListener {
                startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val intent: Intent = when(position) {
            0 -> Intent(holder.itemView.context, DeltaVActivity::class.java)
            1 -> Intent(holder.itemView.context, TwrActivity::class.java)
            2 -> Intent(holder.itemView.context, SpaceXActivity::class.java)
            3 -> Intent(holder.itemView.context, ISSWikiActivity::class.java)
            else -> Intent()
        }
        holder.bind(dataSet[position], intent)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}