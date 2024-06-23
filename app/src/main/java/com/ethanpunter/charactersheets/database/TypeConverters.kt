package com.ethanpunter.charactersheets.database

import android.graphics.Point
import androidx.room.TypeConverter
import com.google.gson.Gson

class TypeConverters {
    @TypeConverter
    fun fromPointToString(point: Point): String = "${point.x},${point.y}"

    @TypeConverter
    fun fromStringToPoint(point: String): Point {
        val points = point.split(",")
        return Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]))
    }

    @TypeConverter
    fun fromJsonToStringList(value: String): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun fromStringListToJson(list: List<String>): String {
        return Gson().toJson(list)
    }
}