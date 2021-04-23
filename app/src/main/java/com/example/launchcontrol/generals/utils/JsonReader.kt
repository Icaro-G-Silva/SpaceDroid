package com.example.launchcontrol.generals.utils

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class JsonReader(private val context: Context) {
    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> read(file: Int): T {
        val inputStream: InputStream = context.resources.openRawResource(file)

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

        return Gson().fromJson<T>(stringBuilder.toString(), T::class.java)
    }
}
