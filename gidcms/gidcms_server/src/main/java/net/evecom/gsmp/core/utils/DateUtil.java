/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 描述 时间工具类
 *
 * @author Nick Lv
 * @created 2016 /12/27 9:44
 */
public class DateUtil {
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    /**
     * The constant REGEX_DATE_FORMAT_YYYY_MM_DD_HH_MM_SS. 严格的yyyy-MM-dd
     * HH:mm:ss日期时间字符串正则表达式
     */
    private final static String REGEX_DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])"
            + "-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]"
            + "|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\\s+([01][0-9]|2[0-3]):[0-5]"
            + "[0-9]:[0-5][0-9]$";

    /**
     * 严格的yyyy-MM-dd HH:mm:ss日期时间字符串
     */
    private final static String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * The constant REGEX_DATE_FORMAT_YYYY_MM_DD. 严格的yyyy-MM-dd日期字符串正则表达式
     */
    private final static String REGEX_DATE_FORMAT_YYYY_MM_DD = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])"
            + "-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]"
            + "|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";

    /**
     * 严格的yyyy-MM-dd日期字符串
     */
    private final static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 描述 获取当前年份.
     *
     * @return the current year
     * @author Nick Lv
     * @created 2016 /12/27 09:47:06
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 描述 获取当前月份.
     *
     * @return the current month
     * @author Nick Lv
     * @created 2016 /12/27 09:47:06
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 描述 是否是闰年.
     *
     * @param year the year
     * @return the boolean
     * @author Nick Lv
     * @created 2016 /12/27 09:47:07 Is leap year boolean.
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 描述 字符串转时间.
     *
     * @param strDate the str date
     * @param pattern the pattern
     * @return the date
     * @author Nick Lv
     * @created 2016 /12/27 09:48:59 Format date.
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date str2date = null;
        try {
            str2date = formatter.parse(strDate);
        } catch (ParseException e) {
            LOGGER.error("解析异常", e);
        }
        return str2date;
    }

    /**
     * 描述 时间转字符串.
     *
     * @param dateDate the date date
     * @param pattern  the pattern
     * @return the string
     * @author Nick Lv
     * @created 2016 /12/27 09:51:24 Date to str string.
     */
    public static String format(Date dateDate, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 描述 两个时间的间隔天数.
     *
     * @param beginDate the begin date yyyy-MM-dd
     * @param endDate   the end date yyyy-MM-dd
     * @return the days between
     * @author Nick Lv
     * @created 2016 /12/27 09:53:20
     */
    public static int getDaysBetween(String beginDate, String endDate) {
        Date bDate = parse(beginDate, DATE_FORMAT_YYYY_MM_DD);
        Date eDate = parse(endDate, DATE_FORMAT_YYYY_MM_DD);
        Calendar d1 = new GregorianCalendar();
        Calendar d2 = new GregorianCalendar();
        if (bDate.before(eDate)) {
            d1.setTime(bDate);
            d2.setTime(eDate);
        } else {
            d1.setTime(eDate);
            d2.setTime(bDate);
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        if (bDate.after(eDate)) {
            days = -days;
        }
        return days;
    }

    /**
     * 描述 yyyy-MM-dd 严格日期字符串匹配转换，包括平闰年日期限制.
     *
     * @param strDate the str date
     * @return the date
     * @author Nick Lv
     * @created 2016 /12/28 16:57:55 Str convert strong date date.
     */
    public static Date strConvertStrongDate(String strDate) {
        return parse(strDate, DATE_FORMAT_YYYY_MM_DD);
    }

    /**
     * 描述 yyyy-MM-dd HH:mm:ss 严格日期时间字符串匹配转换，包括平闰年日期限制.
     *
     * @param strDateTime the str date time
     * @return the date
     * @author Nick Lv
     * @created 2016 /12/28 16:40:07 Str convert strong date date.
     */
    public static Date strConvertStrongDateTime(String strDateTime) {
        return parse(strDateTime, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 描述 判断是字符串是否为yyyy-MM-dd格式的严格日期
     *
     * @param strDate the str date
     * @return the boolean
     * @author Nick Lv
     * @created 2016 /12/28 16:59:13 Is legal date str boolean.
     */
    public static Boolean isLegalDateStr(String strDate) {
        Boolean isLegal = false;
        if (StringUtils.isEmpty(strDate)) {
            return isLegal;
        }
        isLegal = strDate.matches(REGEX_DATE_FORMAT_YYYY_MM_DD);
        return isLegal;
    }

    /**
     * 描述 判断是字符串是否为yyyy-MM-dd HH:mm:ss格式的严格日期时间
     *
     * @param strDateTime the str date time
     * @return the boolean
     * @author Nick Lv
     * @created 2016 /12/28 16:50:09 Is legal date str boolean.
     */
    public static Boolean isLegalDateTimeStr(String strDateTime) {
        Boolean isLegal = false;
        if (StringUtils.isEmpty(strDateTime)) {
            return isLegal;
        }
        isLegal = strDateTime.matches(REGEX_DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        return isLegal;
    }
}
