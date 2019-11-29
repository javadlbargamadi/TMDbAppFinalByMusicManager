package com.example.tmdbappfinalbymusicmanager

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListDataConverter {

    @TypeConverter
    fun fromListToString(listOfStrings: List<String>?): String? {
        if (listOfStrings == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(listOfStrings, type)
    }

    @TypeConverter
    fun fromStringToList(stringsToBeListed: String?): List<String>? {
        if (stringsToBeListed == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson<List<String>>(stringsToBeListed, type)
    }

}

