package com.ethanpunter.charactersheets.database

import android.graphics.Point
import androidx.room.TypeConverter

class TypeConverters {
    @TypeConverter
    fun fromPointToString(point: Point): String = "${point.x},${point.y}"
    @TypeConverter
    fun fromStringToPoint(point: String): Point {
        val points = point.split(",")
        return Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]))
    }
}