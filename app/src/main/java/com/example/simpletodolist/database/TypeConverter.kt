package com.example.simpletodolist.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TypeConverter {
    @TypeConverter
    fun listToJson(value: List<String>?) = GsonBuilder().serializeNulls().create().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}