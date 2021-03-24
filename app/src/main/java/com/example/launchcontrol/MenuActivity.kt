package com.example.launchcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.entities.Menu
import com.example.launchcontrol.lists.recyclerviews.menu.RecyclerViewMenuAdapter


class MenuActivity : AppCompatActivity() {

    private val menuList: List<Menu> = createMenu()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Menu"
        setContentView(R.layout.activity_menu)

        val recycler: RecyclerView = findViewById(R.id.recyclerViewMenu)

        recycler.adapter = RecyclerViewMenuAdapter(menuList)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.setHasFixedSize(false)

    }

    private fun createMenu(): List<Menu> {
        return arrayListOf(
                Menu("Delta-V Calculator", R.drawable.delta_v),
                Menu("TWR Calculator", R.drawable.twr),
                Menu("SpaceX Launches", R.drawable.spacex_launches),
                Menu("ISS Wiki", R.drawable.iss_icon)
        )
    }
}
