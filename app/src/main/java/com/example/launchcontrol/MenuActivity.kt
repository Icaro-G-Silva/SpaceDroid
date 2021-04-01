package com.example.launchcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.launchcontrol.entities.Menu
import com.example.launchcontrol.lists.recyclerviews.menu.RecyclerViewMenuAdapter
import com.example.launchcontrol.utils.GetStringResource

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val myStr = GetStringResource(this)

        supportActionBar?.title = myStr.getStringRes(R.string.main_menu_title)
        val menuList: List<Menu> = createMenu(myStr)

        val recycler: RecyclerView = findViewById(R.id.recyclerViewMenu)

        recycler.adapter = RecyclerViewMenuAdapter(menuList)
        recycler.setHasFixedSize(false)

    }

    private fun createMenu(myStr: GetStringResource): List<Menu> {
        return arrayListOf(
                Menu(myStr.getStringRes(R.string.main_menu_deltav), R.drawable.delta_v),
                Menu(myStr.getStringRes(R.string.main_menu_twr), R.drawable.twr),
                Menu(myStr.getStringRes(R.string.main_menu_spacex), R.drawable.spacex_launches),
                Menu(myStr.getStringRes(R.string.main_menu_iss), R.drawable.iss_icon)
        )
    }
}
