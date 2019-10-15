package com.serverless.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import lombok.val;

/**
 * 日付ユーティリティ
 */
public final class DateUtils {
    

    private DateUtils(){}

    public static Date toDate(long time){
        val calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.getTime();
    }

    public  static Date toDate(LocalDate date){
       val time = toTimeInMillis(date);
       return toDate(time);
    }

    public static Date toDate(LocalDateTime datetime){
        val zone = ZoneId.systemDefault();
        val zonedDateTime = ZonedDateTime.of(datetime, zone);
        val instant = zonedDateTime.toInstant();
        return  Date.from(instant);
    }

    public static Date toLocalDatetime(long time){
        return toDate(time);
    }

    public static long toTimeInMillis(Date value){
        val calendar = Calendar.getInstance();
        calendar.setTime(value);
        return calendar.getTimeInMillis();
    }

    public static long toTimeInMillis(LocalDateTime value){
        val date = toDate(value);
       
        return toTimeInMillis(date);
    }

    public static long toTimeInMillis(LocalDate value){
        val date = Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return toTimeInMillis(date);
    }
}