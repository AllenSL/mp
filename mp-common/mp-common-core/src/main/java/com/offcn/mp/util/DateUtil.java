package com.offcn.mp.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ansonglin
 * @className DateUtils
 * @description 日期操作工具类
 * @date 2019/11/14 17:43
 * @cersion 1.0
 **/
public class DateUtil {

    /**
     * 获取当天零时零分零秒时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getInitDateByDay() {
        long current = System.currentTimeMillis();
        long tmp = TimeZone.getDefault().getRawOffset();
        long zeroT = (current + tmp) / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( zeroT );
    }

    /**
     * 获取当天零时零分零秒时间戳
     *
     * @return Long 时间戳 1585324800000
     */
    public static Long getCurrentTimeMills() {
        //当前时间的时间戳
        long current = System.currentTimeMillis();//当前时间毫秒数
        long tmp = TimeZone.getDefault().getRawOffset();
        return (current + tmp) / (1000 * 3600 * 24) * (1000 * 3600 * 24) - tmp;
    }

    /**
     * 获取昨天零时零分零秒时间戳
     *
     * @return Long 时间戳 1585324800000
     */
    public static Long getYesterdayTimeMills() {
        //昨天时间的时间戳
        long tmp = TimeZone.getDefault().getRawOffset();
        long time = System.currentTimeMillis() + tmp - 1000 * 60 * 60 * 24;
        return time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }

    /**
     * 获取明天零时零分零秒时间戳
     *
     * @return Long 时间戳 1585324800000
     */
    public static Long getTomorrowTimeMills() {
        //明天时间的时间戳
        long tmp = TimeZone.getDefault().getRawOffset();
        long time = System.currentTimeMillis() + tmp + 1000 * 60 * 60 * 24;
        return time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }

    /**
     * 获取当天剩余时间 10位时间戳
     *
     * @return 距离第二天零分零秒时间
     */
    public static Long getTodatSurPlusDate() {
        Long tomorrowTimeMills = getTomorrowTimeMills();
        Long currentTimeMillis = System.currentTimeMillis();
        return (tomorrowTimeMills - currentTimeMillis) / 1000;
    }

    /**
     * 获取指定天数之前的那天的零分零秒时间戳
     */
    public static Long getPreDayZeroDate(int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.set( calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONDAY ), calendar.get( Calendar.DAY_OF_MONTH ), 0, 0, 0 );
        calendar.set( Calendar.DAY_OF_WEEK, Calendar.MONDAY );
        calendar.add( Calendar.DATE, -dayNum );
        return (calendar.getTime().getTime() / 1000);
    }


    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime( date1 );

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime( date2 );
        int day1 = cal1.get( Calendar.DAY_OF_YEAR );
        int day2 = cal2.get( Calendar.DAY_OF_YEAR );

        int year1 = cal1.get( Calendar.YEAR );
        int year2 = cal2.get( Calendar.YEAR );
        if (year1 != year2) {
            //不同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            //同一年
            return day2 - day1;
        }
    }

    public static Long getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime( d );
        }
        calendar.set( calendar.get( Calendar.YEAR ),
                calendar.get( Calendar.MONTH ),
                calendar.get( Calendar.DAY_OF_MONTH ), 0, 0, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
        return calendar.getTimeInMillis();
    }

    public static Long getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime( d );
        }

        calendar.set( calendar.get( Calendar.YEAR ),
                calendar.get( Calendar.MONTH ),
                calendar.get( Calendar.DAY_OF_MONTH ),
                23, 59, 59 );
        calendar.set( Calendar.MILLISECOND, 999 );

        return calendar.getTimeInMillis();
    }

    public static Long getMonthStartTime(Date d) {
        Instant instant = d.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant( instant, zoneId );
        LocalDateTime firstDay = localDateTime.with( TemporalAdjusters.firstDayOfMonth() ).with( LocalTime.MIN );
        Date dates = Date.from( firstDay.atZone( ZoneId.systemDefault() ).toInstant() );
        return dates.getTime();
    }

    /**
     * 获取指定天数之前的那天的零分零秒时间戳 毫秒
     */
    public static Long getPreDayDate(int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.set( calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ), calendar.get( Calendar.DAY_OF_MONTH ), 0, 0, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
        calendar.add( Calendar.DATE, -dayNum );
        return calendar.getTimeInMillis();
    }

    /**
     * 获取明年的本月月末时间
     *
     * @return
     */
    public static Date getNextYearThisMonthEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set( calendar.get( Calendar.YEAR ) + 1,
                calendar.get( Calendar.MONTH ),
                calendar.getActualMaximum( Calendar.DAY_OF_MONTH ),
                23, 59, 59
        );
        calendar.set( Calendar.MILLISECOND, 0 );
        return calendar.getTime();
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat( pattern );
            return df.format( date );
        }
        return null;
    }

    /**
     * 获取当前年份 如：2020
     *
     * @return
     */
    public static Integer getCurrentYear() {
        return Integer.parseInt( format( new Date(), "yyyy" ) );
    }

    public static Integer getDayOfWeek(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant( instant, zoneId );
        return localDateTime.getDayOfWeek().getValue();
    }

    /**
     * 获取时间 年,月,日
     *
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {
        LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        return year;
    }

    /**
     * 获取时间 年,月,日
     *
     * @param date
     * @return
     */
    public static Integer getMonth(Date date) {
        LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        int month = localDate.getMonthValue();
        return month;
    }

    /**
     * 获取时间 年,月,日
     *
     * @param date
     * @return
     */
    public static Integer getDayofMonth(Date date) {
        LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        int day = localDate.getDayOfMonth();
        return day;
    }

    public static Long currentTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println( DateUtil.format( new Date(), "yyyy/MM/dd" ) );
    }


}
   