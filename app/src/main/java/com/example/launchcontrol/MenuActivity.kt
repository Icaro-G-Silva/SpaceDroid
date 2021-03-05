package com.example.launchcontrol

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.Group

class MenuActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val groupDeltaV: Group = findViewById(R.id.list_items_deltav)
        generateListeners(groupDeltaV, Intent(this, DeltaVActivity::class.java))

        val groupTWR: Group = findViewById(R.id.list_items_twr)
        generateListeners(groupTWR, Intent(this, TwrActivity::class.java))

        val groupSpaceX: Group = findViewById(R.id.list_items_spacex)
        generateListeners(groupSpaceX, Intent(this, SpaceXActivity::class.java))

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun generateListeners(view: View, intent: Intent) {
        view.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> view.setBackgroundResource(R.color.buttons)
                MotionEvent.ACTION_UP -> view.setBackgroundResource(R.color.transparent)
            }
            v?.onTouchEvent(event) ?: true
        }

        view.setOnClickListener {
            startActivity(intent)
        }
    }
}