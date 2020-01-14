package com.ilm.org.wift.model;

import androidx.room.TypeConverter;
import java.util.Calendar;
import java.util.Date;


public class Converters {
    @TypeConverter
    public static Calendar fromTimestamp(Long value) {
        Calendar cal =  Calendar.getInstance();
        cal.setTime(new Date(value));
        return value == null ? null : cal;

    }

    @TypeConverter
    public static Long dateToTimestamp(Calendar cal) {
        return cal == null ? null : cal.getTime().getTime();
    }
}
