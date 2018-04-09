/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述： TODO..
 *
 * @author Nick Lv
 * @created 2017 /02/22 17:55:32
 */
public class WorkDayUtils {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkDayUtils.class);


    /**
     * 描述 The entry point of application.
     *
     * @param args the input arguments
     * @author Nick Lv
     * @created 2017 /03/01 10:47:36
     */
    public static void main(String[] args) {
        try {
            String strDateStart = "2013-08-01";
            String strDateEnd = "2014-08-31";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date_start = sdf.parse(strDateStart);
            Date date_end = sdf.parse(strDateEnd);
            WorkDayUtils app = new WorkDayUtils();
            Calendar cal_start = Calendar.getInstance();
            Calendar cal_end = Calendar.getInstance();
            cal_start.setTime(date_start);
            cal_end.setTime(date_end);
            LOGGER.info("开始日：" + cal_start.get(Calendar.YEAR) + "-" + (cal_start.get(Calendar.MONTH) + 1)
                    + "-" + cal_start.get(Calendar.DAY_OF_MONTH) + " " + app.getChineseWeek(cal_start));
            LOGGER.info("结束日：" + cal_end.get(Calendar.YEAR) + "-" + (cal_end.get(Calendar.MONTH) + 1) + "-"
                    + cal_end.get(Calendar.DAY_OF_MONTH) + " " + app.getChineseWeek(cal_end));
            LOGGER.info("工作日：" + app.getWorkingDay(cal_start, cal_end));
            LOGGER.info("休息日：" + app.getHolidays(cal_start, cal_end));
        } catch (Exception e) {
           LOGGER.error("格式转换异常",e);
        }
    }

    /**
     * 获取日期之间的天数
     *
     * @param d1 the d 1
     * @param d2 the d 2
     * @return days between
     * @author Nick Lv
     * @created 2017 /03/01 10:47:36
     */
    public int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 获取工作日
     *
     * @param d1 the d 1
     * @param d2 the d 2
     * @return working day
     * @author Nick Lv
     * @created 2017 /03/01 10:47:36
     */
    public int getWorkingDay(Calendar d1, Calendar d2) {
        int result = -1;
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        // int betweendays = getDaysBetween(d1, d2);
        // int charge_date = 0;
        int charge_start_date = 0;// 开始日期的日期偏移量
        int charge_end_date = 0;// 结束日期的日期偏移量
        // 日期不在同一个日期内
        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
        // }
        result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7) * 5 + charge_start_date
                - charge_end_date;
        // System.out.println("charge_start_date>" + charge_start_date);
        // System.out.println("charge_end_date>" + charge_end_date);
        // System.out.println("between day is-->" + betweendays);
        return result;
    }

    /**
     * 获取中文日期
     *
     * @param date the date
     * @return chinese week
     * @author Nick Lv
     * @created 2017 /03/01 10:47:36
     */
    public String getChineseWeek(Calendar date) {
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        // System.out.println(dayNames[dayOfWeek - 1]);
        return dayNames[dayOfWeek - 1];
    }

    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date the date
     * @return next monday
     * @author Nick Lv
     * @created 2017 /03/01 10:47:36
     */
    public Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    /**
     * 获取休息日
     *
     * @param d1 the d 1
     * @param d2 the d 2
     * @return holidays
     * @author Nick Lv
     * @created 2017 /03/01 10:47:36
     */
    public int getHolidays(Calendar d1, Calendar d2) {
        return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);
    }

}
