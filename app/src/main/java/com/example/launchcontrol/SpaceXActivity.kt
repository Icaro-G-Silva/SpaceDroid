package com.example.launchcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SpaceXActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_x)
    }


//    private fun generateDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Delta-V")
//        builder.setMessage("ΔV = $deltaV")
//
//        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
//            Toast.makeText(applicationContext,
//                android.R.string.ok, Toast.LENGTH_SHORT).show()
//        }
//        builder.show()
//    }
}