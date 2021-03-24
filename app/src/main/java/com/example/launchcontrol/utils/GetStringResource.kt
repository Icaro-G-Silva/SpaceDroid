package com.example.launchcontrol.utils

import android.content.Context

open class GetStringResource(private val context: Context) {
    fun getStringRes(resource: Int): String {
        return context.getString(resource)
    }
}