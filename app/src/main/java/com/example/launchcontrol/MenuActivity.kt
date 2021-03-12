package com.example.launchcontrol

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MenuActivity : AppCompatActivity() {
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

//    @SuppressLint("ClickableViewAccessibility")
//    private fun generateListeners(view: View, intent: Intent) {
//        view.setOnTouchListener { v, event ->
//            when (event?.action) {
//                MotionEvent.ACTION_DOWN -> view.setBackgroundResource(R.color.buttons)
//                MotionEvent.ACTION_UP -> view.setBackgroundResource(R.color.transparent)
//            }
//            v?.onTouchEvent(event) ?: true
//        }
//
//        view.setOnClickListener {
//            startActivity(intent)
//        }
//    }
}