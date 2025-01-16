package com.example.data.offline.converters

import androidx.room.TypeConverter
import com.example.domain.entity.License
import com.example.domain.entity.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromOwner(owner: Owner): String {
        return gson.toJson(owner)
    }

    @TypeConverter
    fun toOwner(value: String): Owner {
        return gson.fromJson(value, Owner::class.java)
    }

    @TypeConverter
    fun fromLicense(license: License): String {
        return gson.toJson(license)
    }

    @TypeConverter
    fun toLicense(value: String): License {
        return gson.fromJson(value, License::class.java)
    }
    @TypeConverter
    fun fromMirrorUrl(mirrorUrl: Any?): String? {
        return mirrorUrl?.toString()
    }

    @TypeConverter
    fun toMirrorUrl(mirrorUrl: String?): Any? {
        return mirrorUrl
    }
}
