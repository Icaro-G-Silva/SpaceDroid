package com.example.launchcontrol.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesReduced(private val context: Context) {
    fun <T> setPreference(key: String, value: T) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val editPrefs = sharedPreferences.edit()
        when(value) {
            is Int -> editPrefs.putInt(key, value as Int)
            is String -> editPrefs.putString(key, value as String)
            is Set<*> -> editPrefs.putStringSet(key, value as Set<String?>)
            is Float -> editPrefs.putFloat(key, value as Float)
            is Boolean -> editPrefs.putBoolean(key, value as Boolean)
        }
        editPrefs.apply()
    }

    fun <T> getPreference(key: String, defaultValue: T): T {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return when(defaultValue) {
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is String,null -> sharedPreferences.getString(key, defaultValue.toString()) as T
            is Set<*> -> sharedPreferences.getStringSet(key, defaultValue as Set<String?>) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            else -> throw IllegalArgumentException("Type mismatch")
        }
    }
}