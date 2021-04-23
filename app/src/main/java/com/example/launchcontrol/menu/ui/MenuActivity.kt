package com.example.launchcontrol.menu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.R
import com.example.launchcontrol.generals.entities.Menu
import com.example.launchcontrol.menu.domain.recyclerview.RecyclerViewMenuAdapter

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportActionBar?.title = getString(R.string.main_menu_title)
        val menuList: List<Menu> = createMenu()

        val recycler: RecyclerView = findViewById(R.id.recyclerViewMenu)
        recycler.adapter = RecyclerViewMenuAdapter(menuList)
        recycler.setHasFixedSize(false)
    }

    private fun createMenu(): List<Menu> {
        return arrayListOf(
                Menu(getString(R.string.main_menu_deltav), R.drawable.delta_v),
                Menu(getString(R.string.main_menu_twr), R.drawable.twr),
                Menu(getString(R.string.main_menu_spacex), R.drawable.spacex_launches),
                Menu(getString(R.string.main_menu_iss), R.drawable.iss_icon)
        )
    }
}
