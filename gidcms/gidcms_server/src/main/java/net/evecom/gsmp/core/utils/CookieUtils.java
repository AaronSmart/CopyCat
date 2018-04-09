/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie工具类
 *
 * @author Nick Lv
 * @version 2013 -01-15
 * @created 2017 /03/01 10:40:41
 */
public class CookieUtils {
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class);

    /**
     * 每天的秒数
     */
    private static final int DATE_SECONDS = 60 * 60 * 24;

    /**
     * 设置 Cookie（生成时间为1天）
     *
     * @param response the response
     * @param name     名称
     * @param value    值
     * @author Nick Lv
     * @created 2017 /03/01 10:40:41
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, DATE_SECONDS);
    }

    /**
     * 设置 Cookie
     *
     * @param response the response
     * @param name     名称
     * @param value    值
     * @param path     the path
     * @author Nick Lv
     * @created 2017 /03/01 10:40:41
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path) {
        setCookie(response, name, value, path, DATE_SECONDS);
    }

    /**
     * 设置 Cookie
     *
     * @param response the response
     * @param name     名称
     * @param value    值
     * @param maxAge   生存时间（单位秒）
     * @author Nick Lv
     * @created 2017 /03/01 10:40:42
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        setCookie(response, name, value, "/", maxAge);
    }

    /**
     * 设置 Cookie
     *
     * @param response the response
     * @param name     名称
     * @param value    值
     * @param path     the path
     * @param maxAge   生存时间（单位秒）
     * @author Nick Lv
     * @created 2017 /03/01 10:40:42
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        try {
            cookie.setValue(URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException e) {
           LOGGER.error("不支持的编码异常",e);
        }
        response.addCookie(cookie);
    }

    /**
     * 获得指定Cookie的值
     *
     * @param request the request
     * @param name    名称
     * @return 值 cookie
     * @author Nick Lv
     * @created 2017 /03/01 10:40:42
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, null, name, false);
    }

    /**
     * 获得指定Cookie的值，并删除。
     *
     * @param request  the request
     * @param response the response
     * @param name     名称
     * @return 值 cookie
     * @author Nick Lv
     * @created 2017 /03/01 10:40:42
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        return getCookie(request, response, name, true);
    }

    /**
     * 获得指定Cookie的值
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param name     名字
     * @param isRemove 是否移除
     * @return 值 cookie
     * @author Nick Lv
     * @created 2017 /03/01 10:40:42
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name,
            boolean isRemove) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        LOGGER.error("不支持的编码异常",e);
                    }
                    if (isRemove) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        return value;
    }
}
