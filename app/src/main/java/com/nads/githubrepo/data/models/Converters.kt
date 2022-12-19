package com.nads.githubrepo.data.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter // note this annotation
    fun fromBuiltByList(builtBy: List<BuiltBy?>?): String? {
        if (builtBy == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<BuiltBy?>?>() {}.getType()
        return gson.toJson(builtBy, type)
    }

    @TypeConverter // note this annotation
    fun toBuiltByList(builtByListString: String?): List<BuiltBy?>? {
        if (builtByListString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<BuiltBy?>?>() {}.type
        return gson.fromJson(builtByListString, type)
    }



}