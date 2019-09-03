package com.example.roomdbflow.database.converters

import androidx.room.TypeConverter
import org.joda.time.DateTime

class DateConverter {

    @TypeConverter
    fun fromString(value: String?): DateTime? {
        return if (value == null) null else DateTime.parse(value)
    }

    @TypeConverter
    fun fromDatetime(value: DateTime?): String? {
        return value?.toString()
    }
}