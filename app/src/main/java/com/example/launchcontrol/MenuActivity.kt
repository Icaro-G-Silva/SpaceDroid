package com.example.launchcontrol

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.Group

class MenuActivity : AppCompatActivity() {

    val menuList: List<Menu> = createMenu()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val buttonDeltaV: Button = findViewById(R.id.buttonDeltaV)
        generateListeners(buttonDeltaV, Intent(this, DeltaVActivity::class.java))

        val buttonTWR: Button = findViewById(R.id.buttonTWR)
        generateListeners(buttonTWR, Intent(this, TwrActivity::class.java))

        val buttonSpaceX: Button = findViewById(R.id.buttonSpaceX)
        generateListeners(buttonSpaceX, Intent(this, SpaceXActivity::class.java))

    }

    private fun generateListeners(view: View, intent: Intent) {
        view.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun createMenu(): List<Menu> {
        return arrayListOf(
                Menu("Delta-V Calculator", R.drawable.delta_v),
                Menu("TWR Calculator", R.drawable.twr),
                Menu("SpaceX Launches", R.drawable.spacex_launches)
        )
    }

}

data class Menu(
        val title: String,
        val icon: Int
)