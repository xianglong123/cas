package com.cas.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 14:46 2020-02-08
 * @version: V1.0
 * @review:
 */
public class TimeUtilTest {


    /**
     * java 8 获取今天的日期
     */
    @Test
    public void time() {
        LocalDate date = LocalDate.now();
        System.out.println("今天的日期是：" + date);
    }


    /**
     * java 8 获取当前的年，月，日
     */
    @Test
    public void time2() {
//        LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.ofYearDay(2020, 128);
        System.out.println("年: " + today.getYear());
        System.out.println("月: " + today.getMonthValue());
        System.out.println("日: " + today.getDayOfMonth());
        System.out.println("美国的星期几: " + today.getDayOfWeek());
        System.out.println("一年的第几天: " + today.getDayOfYear());
    }

    /**
     * 判断两个日期是否相等
     */
    @Test
    public void time3() {
        LocalDate day1 = LocalDate.now();
        LocalDate day2 = LocalDate.of(2020, 2, 8);

        if (day1.equals(day2)) {
            System.out.println("日期相等!!!");
        } else {
            System.out.println("日期不相等!!!");
        }
    }

    /**
     * java 8 检查像生日这种周期性的事件
     */
    @Test
    public void time4() {
        LocalDate date1 = LocalDate.now();

        LocalDate date2 = LocalDate.of(2020, 1, 8);

        MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date1);

        if (currentMonthDay.equals(birthday)) {
            System.out.println("生日会");
        } else {
            System.out.println("不是生日会");
        }
    }

    /**
     * java 8 获取当前时间
     */
    @Test
    public void time5() {
        LocalTime time = LocalTime.now();
        System.out.println("当前时间" + time);
    }

    /**
     * 获取三小时后的时间
     */
    @Test
    public void time6() {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(3);
        LocalTime oldTime = time.minusHours(3);
        System.out.println("三个小时后的时间为: " + newTime);
        System.out.println("三个小时前的时间为: " + oldTime);
    }

    /**
     * java 8 如果计算一周后的日期
     */
    @Test
    public void time7() {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期为: " + today);
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为: " + nextWeek);
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期: " + previousYear);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期: " + nextYear);
    }

    /**
     * java 8的Clock的时钟类
     */
    @Test
    public void time8() {
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());

        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());
    }

    /**
     * 如何用java 判断日期是早于还是晚于另一个日期
     */
    @Test
    public void time9() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2020, 2, 9);
        if (tomorrow.isAfter(today)) {
            System.out.println("之后的日期：" + tomorrow);
        }

        LocalDate yesterday = today.minus(1, ChronoUnit.WEEKS);
        if (yesterday.isBefore(today)) {
            System.out.println("之前的日期：" + yesterday );
        }

    }


    /**
     * java 8 中处理时区
     */
    @Test
    public void time10() {
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, america);
        System.out.println("当前时区的时间：" + zonedDateTime);
    }

    /**
     * 如何表示信用卡到期这类固定日期，答案就在YearMonth
     */
    @Test
    public void time11() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.println("Days in month year %s: %d %n" + currentYearMonth + currentYearMonth.lengthOfMonth());

        YearMonth creditCardExpiry = YearMonth.of(2020, Month.FEBRUARY);
        System.out.println("your credit card expires on %s" + creditCardExpiry);
    }

    /**
     * 如何在java 8 中检查闰年
     */
    @Test
    public void time12() {
        LocalDate today = LocalDate.now();
        if(today.isLeapYear()) {
            System.out.println("闰年");
        } else {
            System.out.println("不是闰年");
        }
    }


    /**
     * 计算两个日期之间的天数和月数
     *
     *
     */
    @Test
    public void time13() {
        LocalDate today = LocalDate.now();

        LocalDate java8Release = LocalDate.of(2022, 4, 18);
        Period periodToNextJavaRelase = Period.between(today, java8Release);
        System.out.println("相差的月数：" + periodToNextJavaRelase.getMonths());
        System.out.println("相差的天数：" + periodToNextJavaRelase.getDays());
        System.out.println("相差的年数：" + periodToNextJavaRelase.getYears());
        //这个可以通过计算两个时间之间的天数，不能直接获得
    }

    /**
     * 获取当前的时间戳
     */
    @Test
    public void time14() {
        Instant timestamp = Instant.now();
        System.out.println("当前时间戳：" + timestamp.toEpochMilli());
    }

    /**
     * java 8 如何预定义的格式化工具解析或格式化日期
     */
    @Test
    public void time15() {
        String time = "20200208";
        LocalDate formatted = LocalDate.parse(time, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(time + "  格式化之后的日期：" + formatted);
    }

    /**
     * 字符串和日期互转
     */
    @Test
    public void time16() {
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转换字符串
        String str = date.format(formatter);
        System.out.println("日期转换成字符串： " + str);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDateTime date2 = LocalDateTime.parse(str, formatter1);
        System.out.println("字符串转日期：" +  date2);
    }

    /**
     * 获取 5 分钟之前的时间格式为：yyyyMMddHHmmss
     */
    @Test
    public void test17() {
        String s = LocalDateTime.now().minusMinutes(5).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(s);
    }

    /**
     * 时间比较
     */
    @Test
    public void test18() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = LocalDateTime.now().minusMinutes(2);
        if(now.isAfter(before)) {
            System.out.println("true");
        }
    }

    /**
     * String 转 LocalDate  再格式化成yyyyMMdd
     */
    @Test
    public void test19() {
        LocalDateTime beginDateTime = LocalDateTime.parse("2022-06-21 00:00:00.0", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0"));
        String yyyyMMdd = beginDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(yyyyMMdd);
    }


}
