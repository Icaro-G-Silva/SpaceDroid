package com.example.launchcontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var title: TextView? = null
    private lateinit var rocketView: ImageView
    private lateinit var launchBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.title)
        rocketView = findViewById(R.id.rocketGif)
        launchBtn = findViewById(R.id.launchButton)

        renderTitle("Welcome to the IFC")

        launchBtn.setOnClickListener {
            renderTitle("Liftoff!")
            Glide.with(this).load(R.drawable.rocket).asGif().into(rocketView)

            val intent = Intent(this, MenuActivity::class.java)
            GlobalScope.launch {
                delay(2800)
                startActivity(intent)
            }
        }
    }

    private fun renderTitle(titleText: String) {
        title?.text = titleText
    }
}