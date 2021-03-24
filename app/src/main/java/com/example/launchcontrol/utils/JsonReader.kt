package com.example.launchcontrol.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
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

        Log.d(null, stringBuilder.toString())

        return Gson().fromJson<ISSlist>(stringBuilder.toString(), ISSlist::class.java)
    }
}

data class ISSlist(
        @SerializedName("experiments")
        val experiments: List<ISSitem>
)

data class ISSitem(
        @SerializedName("title")
        val title: String,

        @SerializedName("items")
        val items: List<ISSitemContent>
)

data class ISSitemContent(
        @SerializedName("item")
        val item: String
)