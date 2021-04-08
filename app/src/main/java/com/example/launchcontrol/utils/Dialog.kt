package com.example.launchcontrol.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Dialog(private val message: String, private val title: String, private val context: Context) {
    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { _, _ ->
//            Toast.makeText(context, android.R.string.ok, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}