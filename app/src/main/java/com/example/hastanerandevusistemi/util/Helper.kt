package com.example.hastanerandevusistemi.util

import android.content.Context
import com.example.hastanerandevusistemi.domain.model.City
import com.google.gson.Gson
import java.io.IOException

class Helper {

    fun getJsonDataFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}