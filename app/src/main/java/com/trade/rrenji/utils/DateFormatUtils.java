package com.trade.rrenji.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy年MM月dd日");
        java.util.Date dt = new Date(mDateTime.getTime());
        String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        System.out.println(sDateTime);
        return  sDateTime;
//        return TimeSince.getFormattedDateString(date.getTime() / 1000, false, SBKApplication.mContext);
    }
    /**
     * 将字符串转为时间戳
     * @param dateString
     * @return
     */
    public static String getStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateFormat.toString();
    }

}
