package com.example.launchcontrol.main.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.launchcontrol.menu.ui.MenuActivity
import com.example.launchcontrol.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var rocketView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.title)
        rocketView = findViewById(R.id.rocketGif)

        renderTitle(getString(R.string.liftoff))
        Glide.with(this).load(R.drawable.rocket).asGif().into(rocketView)

        val intent = Intent(this, MenuActivity::class.java)
        GlobalScope.launch {
            delay(2800)
            startActivity(intent)
        }

        rocketView.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun renderTitle(titleText: String) {
        title.text = titleText
    }
}