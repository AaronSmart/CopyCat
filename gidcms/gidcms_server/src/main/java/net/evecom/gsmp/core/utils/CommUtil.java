/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <P><B>描述:</B> 通用工具类 </P>
 * 
 * @author Nick Lv
 * @version 1.0
 */
@Component
public class CommUtil {

    /**
     * 判断String 是否为空（NULL 或者空字符串）
     *
     * @author Nick Lv
     * 
     * @param sourceString
     * @return
     */
    public static boolean isEmpty(String sourceString) {
        if (null == sourceString || sourceString.isEmpty())
            return true;
        else
            return false;
    }

    /**
     * 判断String 是否非空（NULL 或者空字符串）
     *
     * @author Nick Lv
     * 
     * @param sourceString
     * @return
     */
    public static boolean isNotEmpty(String sourceString) {
        if (null != sourceString && !sourceString.isEmpty())
            return true;
        else
            return false;
    }

    /**
     * 判断数组是否为空
     * @author Nick Lv
     * 
     * @param sourceObjects
     * @return
     */
    public static boolean isEmpty(Object[] sourceObjects) {
        return !isNotEmpty(sourceObjects);
    }

    /**
     * 判断数组是否非空
     * @author Nick Lv
     * 
     * @param sourceObjects
     * @return
     */
    public static boolean isNotEmpty(Object[] sourceObjects) {
        if (null != sourceObjects && sourceObjects.length > 0)
            return true;
        else
            return false;
    }

    /**
     * 判断Collection类比如HashMap、Arraylist等是否为NULL且集合大小为0
     * @author Nick Lv
     * 
     * @param collection    要判断的集合
     * @return    true --- 为NULL或 集合大小为0;false --- 否则
     */
    public static boolean isEmpty(Collection collection) {
        return !isNotEmpty(collection);
    }

    /**
     * 判断Collection类比如HashMap、Arraylist等是否不为NULL且集合大小不为0
     *
     * @author Nick Lv
     * 
     * @param collection
     *            要判断的集合
     * @return false --- 为NULL或 集合大小为0;true --- 否则
     */
    public static boolean isNotEmpty(Collection collection) {
        if (null != collection && collection.size() > 0)
            return true;
        else
            return false;
    }

    /**
     * 判断Timestamp 是否为空
     * Revision Trail: (Date/Author/Description)
     *                 2015-1-29 Nelson Ni CREATE
     * @author Nick Lv
     * 
     * @param sourceTimestamp
     * @return
     */
    public static boolean isEmpty(Timestamp sourceTimestamp) {
        return !isNotEmpty(sourceTimestamp);
    }

    /**
     * 判断Timestamp 是否不为空，同时不等于1970-1-1
     * Revision Trail: (Date/Author/Description)
     *                 2015-1-29 Nelson Ni CREATE
     * @author Nick Lv
     * 
     * @param sourceTimestamp
     * @return
     */
    public static boolean isNotEmpty(Timestamp sourceTimestamp) {
        if (null != sourceTimestamp && !(new Timestamp(0)).equals(sourceTimestamp))
            return true;
        else
            return false;
    }

    /**
     * 获取介于0和scope之间的随机数
     *
     * @author Nick Lv
     * 
     * @param scope
     *            范围
     * @return 0～scope随机数
     */
    public static int getRandomNum(int scope) {
        int aNum = (int) Math.round(Math.random() * (double) (scope - 1));
        if (aNum > scope)
            aNum = scope;
        return aNum;
    }

    /**
     * 获取num位长度的随机数
     *
     * @author Nick Lv
     * 
     * @param num
     *            要获取随机数的长度
     * @return 随机数
     */
    public static String getRandom(int num) {
        String tmpRandom = "";
        int iRan = (int) (Math.random() * Math.pow(10D, num));
        tmpRandom = Integer.toString(iRan);

        StringBuilder tmpSpace = new StringBuilder();
        if (tmpRandom.length() < num) {
            int spaceLen = num - tmpRandom.length();
            for (int i = 0; i < spaceLen; i++){
                tmpSpace.append("0");
            }
            tmpRandom = (new StringBuilder(String.valueOf(tmpSpace))).append(tmpRandom).toString();
        }
        return tmpRandom;
    }

    /**
     * 生成32位uuid
     *
     * @author Nick Lv
     * 
     * @return uuid值
     */
    public static String uuidGenerator() {
        String result = UUID.randomUUID().toString();
        return result.replace("-", "");
    }

    /**
     * 计算相隔天数
     *
     * @author Nick Lv
     * 
     * @param timestamp1
     * @param timestamp2
     * @return
     */
    public static int getDaysBetween(Timestamp timestamp1, Timestamp timestamp2) {
        return getDaysBetween(new Date(timestamp1.getTime()), new Date(timestamp2.getTime()));
    }

    /**
     * 计算相隔天数
     *
     * @author Nick Lv
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int getDaysBetween(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return getDaysBetween(calendar1, calendar2);
    }

    /**
     * 计算相隔天数
     *
     * @author Nick Lv
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
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
        return days;
    }

    /**
     * 计算两个时间之间的分钟间隔
     *
     * @author Nick Lv
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getMinutesBetween(Date beginDate, Date endDate) {
        return getMinutesBetween(new Timestamp(beginDate.getTime()), new Timestamp(endDate.getTime()));
    }

    /**
     * 计算两个时间之间的分钟间隔
     *
     * @author Nick Lv
     * 
     * @param beginTimestamp
     * @param endTimestamp
     * @return
     */
    public static int getMinutesBetween(Timestamp beginTimestamp, Timestamp endTimestamp) {
        if (null == beginTimestamp || null == endTimestamp)
            return -1;

        long timeBetween = beginTimestamp.getTime() - endTimestamp.getTime();
        long minuteBetween = timeBetween / (1000 * 60);

        return (int) minuteBetween;
    }

    /**
     * 
     * 描述  获取paramsDay的前/后num天的时间Timestamp
     * @author Nick Lv
     * @created 2015-12-31
     * @param paramsDay
     * @param num
     * @return
     */
    public static Timestamp getDayByTimestamp(Timestamp paramsDay, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(paramsDay.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, num);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 
     * 获取paramsDay的前/后num的时间Timestamp
     * Revision Trail: (Date/Author/Description)
     *                 2016年10月18日 Lynch Lin CREATE
     * @author Nick Lv
     * 
     * @param paramsDay
     * @param num
     * @return
     */
    public static Timestamp getHourByTimestamp(Timestamp paramsDay, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(paramsDay.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, num);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 
     *  描述  获取paramsDay的前/后num月的时间Timestamp
     * Revision Trail: (Date/Author/Description)
     *                 2016年7月25日 Lynch Lin CREATE
     * @author Nick Lv
     * 
     * @param paramsDay
     * @param num
     * @return
     */
    public static Timestamp getMonthByTimestamp(Timestamp paramsDay, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(paramsDay.getTime());
        calendar.add(Calendar.MONTH, num);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 
     * 获取当前日期年份
     * Revision Trail: (Date/Author/Description)
     *                 2016年3月30日 Lynch Lin CREATE
     * @author Nick Lv
     * 
     * @param paramsDay
     * @return
     */
    public static int getYearByTimestamp(Timestamp paramsDay) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(paramsDay.getTime());
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 
     * 获取当前时间季度
     * Revision Trail: (Date/Author/Description)
     *                 2016年3月30日 Lynch Lin CREATE
     * @author Nick Lv
     * 
     * @param paramsDay
     * @return
     */
    public static int getSeasonByTimestamp(Timestamp paramsDay) {
        int season = 0;
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(paramsDay.getTime());
        int month = calendar.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 描述 下划线转驼峰.
     *
     * @param str
     *            the str
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/18 11:22:35 Line to hump string.
     */
    public static String lineToHump(String str) {
        if (str == null || !str.contains("_")) {
            return str.toLowerCase();
        }
        Pattern linePattern = Pattern.compile("_+([A-Za-z0-9])");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 描述 根据fieldName取得实体类中对应的字段
     *
     * @param <T>
     *            the type parameter
     * @param clazz
     *            the clazz
     * @param fieldName
     *            the key
     * @return the field
     * @author Nick Lv
     * @created 2017 /07/06 10:30:45
     */
    public static <T> String getFieldName(Class<T> clazz, String fieldName) {
        if (clazz == null || fieldName == null || fieldName.isEmpty()) {
            return null;
        }
        fieldName = fieldName.replace("_", "");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().toLowerCase().equals(fieldName.toLowerCase())) {
                return field.getName();
            }
        }
        return null;
    }

    /**
     * 描述 Map中的key由下下划线转驼峰.
     *
     * @param <T>
     *            the type parameter
     * @param clazz
     *            the clazz
     * @param map
     *            the map
     * @return the map
     * @author Nick Lv
     * @created 2017 /03/18 11:22:35 Map key by hump map.
     */
    public static <T> Map<String, Object> mapKeyByHump(Class<T> clazz, Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Map<String, Object> humpMap = new HashedMap();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        Map.Entry<String, Object> entry = null;
        String key = null;
        Object value = null;
        while (it.hasNext()) {
            entry = it.next();
            key = entry.getKey();
            value = entry.getValue();
            key = getFieldName(clazz, key);
            if (key != null) {
                humpMap.put(key, value);
            }
        }
        return humpMap;
    }
}
