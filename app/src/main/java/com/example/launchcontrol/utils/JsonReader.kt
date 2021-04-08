package com.example.launchcontrol.utils

import android.content.Context
import com.example.launchcontrol.entities.jsonentities.ISSlist
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException

class JsonReader(private val file: Int, val context: Context) {
    
    private val inputStream: InputStream = context.resources.openRawResource(file)

    fun read(): ISSlist {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        try {
            do {
                val row = reader.readLine() ?: break
                stringBuilder.append(row)
            }while (true)
        } catch (e: IOException) {
            throw RuntimeException(e)
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        return Gson().fromJson<ISSlist>(stringBuilder.toString(), ISSlist::class.java)
    }
}
