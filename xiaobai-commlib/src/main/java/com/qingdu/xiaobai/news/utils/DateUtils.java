package com.qingdu.xiaobai.news.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * author: YanBin
 * date: 2019-12-02
 * desc: 日期处理相关工具
 */
public class DateUtils {

    /**
     * 计算两个日期间隔天数
     *
     * @param day1
     * @param day2
     * @return
     */
    public static String getTwoDay(String day1, String day2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(day1);
            Date mydate = myFormatter.parse(day2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return String.valueOf(day);
    }

    /**
     * 计算两个日期间隔天数
     *
     * @param futureDate
     * @param curDate
     * @return
     */
    public static int getTwoDay(Date futureDate, Date curDate) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            day = (futureDate.getTime() - curDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return -1;
        }
        return (int)day;
    }

    /**
     * 根据String转成日历
     *
     * @param strDate 2019-01-01
     * @return
     */
    public static Calendar getCalByString(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar queryCal = Calendar.getInstance();
        Date queryDate = new Date();
        try {
            // 将字符串yyyy-MM-dd 转换成Date，并set进Calendar
            if (strDate != null && strDate.length() > 0) {
                queryDate = format.parse(strDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        queryCal.setTime(queryDate);
        return queryCal;
    }
}
