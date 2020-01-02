package com.cas.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: xianglong
 * @date: 2019/10/10 14:13
 * @version: V1.0
 * @review: xiang_long
 */
public class TimeUtil {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN2 = "yyyy/MM/dd HH:mm:ss";
    private static final String PATTERN3 = "yyyy:MM:dd HH:mm:ss";

    private static final String YEAR = "yyyy";

    /**
     * LocalDateTime 转格式
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    private static String getDateTimeFormatter(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String format = dateTimeFormatter.format(localDateTime);
        return format;
    }

    /**
     * 默认时间格式
     *
     * @param localDateTime
     * @return
     */
    public static String getDefaultDateTime(LocalDateTime localDateTime) {
        return getDateTimeFormatter(localDateTime, PATTERN);
    }

    /**
     * 获取当前的年份
     * @param localDateTime
     * @return
     */
    public static String getDefaultYear(LocalDateTime localDateTime) {
        return getDateTimeFormatter(localDateTime, YEAR);
    }

    /**
     * localDateTime转date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * date转localDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String getLocalDate() {
        return LocalDate.now().toString();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getLocalTime() {
        return LocalTime.now().toString();
    }

    /**
     * 获取当前时间戳
     */
    public static long getCurrentTimeMills() {
        return System.currentTimeMillis();
    }

}
