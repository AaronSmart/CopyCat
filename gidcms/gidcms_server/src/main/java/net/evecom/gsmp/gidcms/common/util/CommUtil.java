/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <P><B>描述:</B> 通用工具类 </P>
 *
 * @author Iverson Cai  
 * @created 2017 /05/08 16:27:10  
 * @version 1.0
 */
public class CommUtil {

    /**
     * 日志.
     */
    public static Logger logger = Logger.getLogger(CommUtil.class);

    /**
     * 描述 获取get请求中的参数并将其转成receiveBody.
     * @author Iverson Cai  
     * @created 2017 /08/07 08:53:38 Get receive body by request receive body.  
     * @param request the request  
     * @return the receive body
     */
    public static ReceiveBody getReceiveBodyByRequest(HttpServletRequest request) {
        ReceiveBody receiveBody = new ReceiveBody();
        Map<String, Object> dataMap = new HashMap<>();
        String queryString = "";
        try {
            queryString = URLDecoder.decode(request.getQueryString(), "utf-8");//将中文转码
        } catch (Exception e) {
            logger.info("get参数转码失败", e);
        }
        String datas[] = queryString.split("&");
        for (int i = 0; i < datas.length; i++) {
            String parameters[] = datas[i].split("=");
            if (parameters.length == 2) {
                if (parameters[0].equals("page")) {
                    receiveBody.setPage(Integer.valueOf(parameters[1]));
                } else if (parameters[0].equals("size")) {
                    receiveBody.setSize(Integer.valueOf(parameters[1]));
                } else {
                    dataMap.put(parameters[0], parameters[1]);
                }
            }
        }
        receiveBody.setDataMap(dataMap);
        return receiveBody;
    }

    /**
     * 描述 根据网格层级所对应的下级网格Code长度
     * @author Iverson Cai 
     * @created 2017 /08/10 09:39:01 
     * @param gridlvl the gridlvl 
     * @return the code length by grid lvl
     */
    public static Integer getCodeLengthByGridLvl(Integer gridlvl) {
        Integer codeLength = 0;
        if (gridlvl == 1)
            codeLength = 6;
        if (gridlvl == 2)
            codeLength = 9;
        if (gridlvl == 3 || gridlvl == 4)
            codeLength = 12;
        return codeLength;
    }

    public static String getResponseJson(String url) {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        String responseJson = "";

        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if(statusCode != 200) {
                logger.error("资源信息获取失败：url=" + url);
                logger.error("Method failed: " + getMethod.getStatusLine());
            } else {
                byte[] responseBody = getMethod.getResponseBody();
                responseJson = new String(responseBody, "utf-8");
            }
        } catch (IOException var9) {
            logger.error("The page can not be found.");
            logger.error("The page can not be found", var9);
        } finally {
            getMethod.releaseConnection();
        }

        return responseJson;
    }
    /**
     * 描述 用于获取map中的gridId字段（在网格关联查询中常用到）
     * @author Iverson Cai  
     * @created 2017 /06/28 10:55:42  
     * @param map the map  
     * @return the grid id by map
     */
    /*public static Long getGridIdByMap(Map<String, Object> map) {
        Long grid = (map != null && map.get("gridId") != null) ? Long.valueOf(String.valueOf(map.get("gridId")))
                : SystemConstant.GRID_ROOT_ID;
        return grid;
    }*/

    /**
     * 判断String 是否为空（NULL 或者空字符串）
     * @author Iverson Cai  
     * @created 2017 /06/14 09:26:30 Is empty boolean.  
     * @param sourceString the source string  
     * @return the boolean
     */
    public static boolean isEmpty(String sourceString) {
        if (null == sourceString || sourceString.isEmpty())
            return true;
        else
            return false;
    }

    /**
     * 判断String 是否非空（NULL 或者空字符串）
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:22 Is not empty boolean.  
     * @param sourceString the source string  
     * @return the boolean
     */
    public static boolean isNotEmpty(String sourceString) {
        if (null != sourceString && !sourceString.isEmpty())
            return true;
        else
            return false;
    }

    /**
     * 判断数组是否为空
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:22 Is empty boolean.  
     * @param sourceObjects the source objects  
     * @return the boolean
     */
    public static boolean isEmpty(Object[] sourceObjects) {
        return !isNotEmpty(sourceObjects);
    }

    /**
     * 判断数组是否非空
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:22 Is not empty boolean.  
     * @param sourceObjects the source objects  
     * @return the boolean
     */
    public static boolean isNotEmpty(Object[] sourceObjects) {
        if (null != sourceObjects && sourceObjects.length > 0)
            return true;
        else
            return false;
    }

    /**
     * 判断Collection类比如HashMap、Arraylist等是否为NULL且集合大小为0
     * @author Iverson Cai  
     * @created 2017 /05/08 16:27:11 Is empty boolean.  
     * @param collection 要判断的集合  
     * @return true --- 为NULL或 集合大小为0;false --- 否则
     */
    public static boolean isEmpty(Collection collection) {
        return !isNotEmpty(collection);
    }

    /**
     * 判断Collection类比如HashMap、Arraylist等是否不为NULL且集合大小不为0
     * @author Iverson Cai  
     * @created 2017 /05/08 16:27:11 Is not empty boolean.  
     * @param collection 要判断的集合  
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
     * @author Iverson Cai  
     * @created 2017 /05/08 16:27:11 Is empty boolean.  
     * @param sourceTimestamp the source timestamp  
     * @return boolean boolean
     */
    public static boolean isEmpty(Timestamp sourceTimestamp) {
        return !isNotEmpty(sourceTimestamp);
    }

    /**
     * 判断Timestamp 是否不为空，同时不等于1970-1-1
     * @author Iverson Cai  
     * @created 2017 /05/08 16:27:11 Is not empty boolean.  
     * @param sourceTimestamp the source timestamp  
     * @return boolean boolean
     */
    public static boolean isNotEmpty(Timestamp sourceTimestamp) {
        if (null != sourceTimestamp && !(new Timestamp(0)).equals(sourceTimestamp))
            return true;
        else
            return false;
    }

    /**
     * 获取介于0和scope之间的随机数
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:23  
     * @param scope 范围  
     * @return 0 ～scope随机数
     */
    public static int getRandomNum(int scope) {
        int aNum = (int) Math.round(Math.random() * (double) (scope - 1));
        if (aNum > scope)
            aNum = scope;
        return aNum;
    }

    /**
     * 获取num位长度的随机数
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:23  
     * @param num 要获取随机数的长度  
     * @return 随机数 random
     */
    public static String getRandom(int num) {
        String tmpRandom = "";
        int iRan = (int) (Math.random() * Math.pow(10D, num));
        /*tmpRandom = (new Integer(iRan)).toString();*/
        tmpRandom = Integer.valueOf(iRan) + "";
        String tmpSpace = "";
        if (tmpRandom.length() < num) {
            int spaceLen = num - tmpRandom.length();
            StringBuffer sbf = new StringBuffer(String.valueOf(tmpSpace));
            for (int i = 0; i < spaceLen; i++)
                /*tmpSpace = (new StringBuilder(String.valueOf(tmpSpace))).append("0").toString();*/
                tmpSpace = sbf.append("0").toString();
            tmpRandom = (new StringBuilder(String.valueOf(tmpSpace))).append(tmpRandom).toString();
        }
        return tmpRandom;
    }

    /**
     * 生成32位uuid
     * @author Iverson Cai  
     * @created 2017 /05/08 16:27:11 Uuid generator string.  
     * @return uuid值 string
     */
    public static String uuidGenerator() {
        String result = UUID.randomUUID().toString();
        return result.replace("-", "");
    }

    /**
     * 计算相隔天数
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:24  
     * @param timestamp1 the timestamp 1  
     * @param timestamp2 the timestamp 2  
     * @return days between
     */
    public static int getDaysBetween(Timestamp timestamp1, Timestamp timestamp2) {
        return getDaysBetween(new Date(timestamp1.getTime()), new Date(timestamp2.getTime()));
    }

    /**
     * 计算相隔天数
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:24  
     * @param date1 the date 1  
     * @param date2 the date 2  
     * @return days between
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
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:24  
     * @param d1 the d 1  
     * @param d2 the d 2  
     * @return days between
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
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:24  
     * @param beginDate the begin date  
     * @param endDate the end date  
     * @return minutes between
     */
    public static int getMinutesBetween(Date beginDate, Date endDate) {
        return getMinutesBetween(new Timestamp(beginDate.getTime()), new Timestamp(endDate.getTime()));
    }

    /**
     * 计算两个时间之间的分钟间隔
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:24  
     * @param beginTimestamp the begin timestamp  
     * @param endTimestamp the end timestamp  
     * @return minutes between
     */
    public static int getMinutesBetween(Timestamp beginTimestamp, Timestamp endTimestamp) {
        if (null == beginTimestamp || null == endTimestamp)
            return -1;

        long timeBetween = beginTimestamp.getTime() - endTimestamp.getTime();
        long minuteBetween = timeBetween / (1000 * 60);

        return (int) minuteBetween;
    }

    /**
     * 描述  获取paramsDay的前/后num天的时间Timestamp
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:24  
     * @param paramsDay the params day  
     * @param num the num  
     * @return day by timestamp
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
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:24  
     * @param paramsDay the params day  
     * @param num the num  
     * @return hour by timestamp
     */
    public static Timestamp getHourByTimestamp(Timestamp paramsDay, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(paramsDay.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, num);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     *  描述  获取paramsDay的前/后num月的时间Timestamp
     * @author Iverson Cai  
     * @created 2017 /05/08 16:27:12  
     * @param paramsDay the params day  
     * @param num the num  
     * @return month by timestamp
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
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:25  
     * @param paramsDay the params day  
     * @return year by timestamp
     */
    public static int getYearByTimestamp(Timestamp paramsDay) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(paramsDay.getTime());
        return calendar.get(Calendar.YEAR);
    }

    /**
     *
     * 获取当前时间季度
     * @author Iverson Cai  
     * @created 2017 /06/14 09:29:25  
     * @param paramsDay the params day  
     * @return season by timestamp
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
     * @author Iverson Cai  
     * @created 2017 /03/18 11:22:35 Line to hump string.  
     * @param str the str  
     * @return the string
     */
    public static String lineToHump(String str) {
        Pattern linePattern = Pattern.compile("_(\\w)");
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
     * 描述 首字母小写.
     * @author Iverson Cai  
     * @created 2017 /06/07 11:14:35 To lower case first one string.  
     * @param s the s  
     * @return the string
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 描述 首字母大写
     * @author Iverson Cai  
     * @created 2017 /06/07 11:14:35 To lower case first one string.  
     * @param s the s  
     * @return the string
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 描述 Map中的key由下下划线转驼峰.
     * @author Iverson Cai  
     * @created 2017 /03/18 11:22:35 Map key by hump map.  
     * @param map the map  
     * @return the map
     */
    public static Map<String, Object> mapKeyByHump(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Map<String, Object> humpMap = new HashedMap();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            humpMap.put(lineToHump(key), value);
        }
        return humpMap;
    }

    /**
     * 描述 create.
     * @author Iverson Cai  
     * @created 2017 /06/07 11:14:35 Camel to underline string.  
     * @param param the param  
     * @return the string
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static boolean isTokenValidate(String token,String checkUrl) {
        if (org.springframework.util.StringUtils.isEmpty(token)) {
            logger.error("为空的token不应该出现在此处进行验证");
            return false;
        } else {
            String url = checkUrl + token;
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(url);
            boolean result = false;
            try {
                int statusCode = httpClient.executeMethod(getMethod);
                if (statusCode == 200) {
                    result = true;
                } else {
                    logger.debug("token失效,token=" + token);
                }
            } catch (IOException var9) {
                logger.error("The page can not be found", var9);
                logger.error("The page can not be found.");
            } finally {
                getMethod.releaseConnection();
            }
            return result;
        }
    }
}
