package com.trade.rrenji.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class DateFormatUtils {

    public static String getTimeSinceNow(String utcTime) {
        return getTimeSince(utcTime);
//        switch (getTimeDay(utcTime)) {
//            case 0:
//                return getTimeSince(utcTime);
//            case 1:
//                return "昨天" + translateUTCToDate(utcTime, DateFormatUtils.DateConstants.FORMAT_HOUR_MIN);
//            case 2:
//                return "前天" + translateUTCToDate(utcTime, DateFormatUtils.DateConstants.FORMAT_HOUR_MIN);
//            default:
//                return getYearTimeNow(utcTime);
//        }
    }

    /**
     * 获取时间间隔，几天前，几小时前
     *
     * @param utc
     * @return
     */
    private static String getTimeSince(String utc) {
        Date mDateTime = new Date(utc);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        java.util.Date dt = new Date(mDateTime.getTime());
        String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        System.out.println(sDateTime);
        return sDateTime;
//        return TimeSince.getFormattedDateString(date.getTime() / 1000, false, SBKApplication.mContext);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param dateString
     * @return
     */
    public static String getStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateFormat.toString();
    }

    public static String getTimeStringAutoShort2(Date srcDate, boolean mustIncludeTime) {
        String ret = "";
        try {
            GregorianCalendar gcCurrent = new GregorianCalendar();
            gcCurrent.setTime(new Date());
            int currentYear = gcCurrent.get(GregorianCalendar.YEAR);
            int currentMonth = gcCurrent.get(GregorianCalendar.MONTH) + 1;
            int currentDay = gcCurrent.get(GregorianCalendar.DAY_OF_MONTH);
            GregorianCalendar gcSrc = new GregorianCalendar();
            gcSrc.setTime(srcDate);
            int srcYear = gcSrc.get(GregorianCalendar.YEAR);
            int srcMonth = gcSrc.get(GregorianCalendar.MONTH) + 1;
            int srcDay = gcSrc.get(GregorianCalendar.DAY_OF_MONTH);
            // 要额外显示的时间分钟
            String timeExtraStr = (mustIncludeTime ? " " + getTimeString(srcDate, "HH:mm") : "");
            // 当年
            if (currentYear == srcYear) {
                long currentTimestamp = gcCurrent.getTimeInMillis();
                long srcTimestamp = gcSrc.getTimeInMillis();
                // 相差时间（单位：毫秒）
                long delta = (currentTimestamp - srcTimestamp);
                // 当天（月份和日期一致才是）
                if (currentMonth == srcMonth && currentDay == srcDay) {
                    // 时间相差60秒以内
                    if (delta < 60 * 1000)
                        ret = "刚刚";
                        // 否则当天其它时间段的，直接显示“时:分”的形式
                    else
                        ret = getTimeString(srcDate, "HH:mm");
                }
                // 当年 && 当天之外的时间（即昨天及以前的时间）
                else {
                    // 昨天（以“现在”的时候为基准-1天）
                    GregorianCalendar yesterdayDate = new GregorianCalendar();
                    yesterdayDate.add(GregorianCalendar.DAY_OF_MONTH, -1);
                    // 前天（以“现在”的时候为基准-2天）
                    GregorianCalendar beforeYesterdayDate = new GregorianCalendar();
                    beforeYesterdayDate.add(GregorianCalendar.DAY_OF_MONTH, -2);
                    // 用目标日期的“月”和“天”跟上方计算出来的“昨天”进行比较，是最为准确的（如果用时间戳差值
                    // 的形式，是不准确的，比如：现在时刻是2019年02月22日1:00、而srcDate是2019年02月21日23:00，
                    // 这两者间只相差2小时，直接用“delta/(3600 * 1000)” > 24小时来判断是否昨天，就完全是扯蛋的逻辑了）
                    if (srcMonth == (yesterdayDate.get(GregorianCalendar.MONTH) + 1)
                            && srcDay == yesterdayDate.get(GregorianCalendar.DAY_OF_MONTH)) {
                        ret = "昨天" + timeExtraStr;// -1d
                    }
                    // “前天”判断逻辑同上
                    else if (srcMonth == (beforeYesterdayDate.get(GregorianCalendar.MONTH) + 1)
                            && srcDay == beforeYesterdayDate.get(GregorianCalendar.DAY_OF_MONTH)) {
                        ret = "前天" + timeExtraStr;// -2d
                    } else {
                        // 跟当前时间相差的小时数
                        long deltaHour = (delta / (3600 * 1000));
                        // 如果小于 7*24小时就显示星期几
                        if (deltaHour < 7 * 24) {
                            String[] weekday = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
                            // 取出当前是星期几
                            String weedayDesc = weekday[gcSrc.get(GregorianCalendar.DAY_OF_WEEK) - 1];
                            ret = weedayDesc + timeExtraStr;
                        } else
                            ret = getTimeString(srcDate, "yyyy/M/d") + timeExtraStr;
                    }
                }
            } else
                ret = getTimeString(srcDate, "yyyy/M/d") + timeExtraStr;

        } catch (Exception e) {

            System.err.println("【DEBUG-getTimeStringAutoShort】计算出错：" + e.getMessage() + " 【NO】");
        }
        return ret;
    }

    /**
     * 返回指定pattern样的日期时间字符串。
     *
     * @param dt
     * @param pattern
     * @return 如果时间转换成功则返回结果，否则返回空字符串""
     * @author 即时通讯网([url = http : / / www.52im.net]http : / / www.52im.net[ / url])
     */

    public static String getTimeString(Date dt, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);//"yyyy-MM-dd HH:mm:ss"
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.format(dt);
        } catch (Exception e) {
            return "";
        }
    }
}
