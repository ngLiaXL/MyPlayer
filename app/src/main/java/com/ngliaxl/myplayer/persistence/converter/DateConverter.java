package com.ngliaxl.myplayer.persistence.converter;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date revertDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long converterDate(Date value) {
        return value == null ? null : value.getTime();
    }
}

