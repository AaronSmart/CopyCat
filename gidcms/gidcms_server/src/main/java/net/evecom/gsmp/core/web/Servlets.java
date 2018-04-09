/*
 * Copyright (c) 2005,2017, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.gsmp.core.web;

import com.google.common.net.HttpHeaders;
import net.evecom.gsmp.core.utils.Encodes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Http与Servlet工具类.
 *
 * @author Nick Lv
 * @version 2014 -8-19
 * @created 2017 /07/06 17:33:42
 */
public class Servlets {

    /**
     * 常用数值定义
     */
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

    /**
     * 静态文件后缀
     */
    private final static String[] STATIC_FILES = {};

    /**
     * 动态映射URL后缀
     */
    private final static String URL_SUFFIX = ".html";

    /**
     * 设置客户端缓存过期时间 的Header.
     *
     * @param response       the response
     * @param expiresSeconds the expires seconds
     * @author Nick Lv
     * @created 2017 /07/06 17:34:35
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        // Http 1.0 header, set a fix expires date.
        response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
        // Http 1.1 header, set a time after now.
        response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     *
     * @param response the response
     * @author Nick Lv
     * @created 2017 /07/06 17:34:32
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader(HttpHeaders.EXPIRES, 1L);
        response.addHeader(HttpHeaders.PRAGMA, "no-cache");
        // Http 1.1 header
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
    }

    /**
     * 设置LastModified Header.
     *
     * @param response         the response
     * @param lastModifiedDate the last modified date
     * @author Nick Lv
     * @created 2017 /07/06 17:34:38
     */
    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
        response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
    }

    /**
     * 设置Etag Header.
     *
     * @param response the response
     * @param etag     the etag
     * @author Nick Lv
     * @created 2017 /07/06 17:34:40
     */
    public static void setEtag(HttpServletResponse response, String etag) {
        response.setHeader(HttpHeaders.ETAG, etag);
    }

    /**
     * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
     * <p>
     * 如果无修改, checkIfModify返回false ,设置304 not modify status.
     *
     * @param request      the request
     * @param response     the response
     * @param lastModified 内容的最后修改时间.
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /07/06 17:34:44 Check if modified since boolean.
     */
    public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
                                               long lastModified) {
        long ifModifiedSince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
        if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return false;
        }
        return true;
    }

    /**
     * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
     * <p>
     * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
     *
     * @param request  the request
     * @param response the response
     * @param etag     内容的ETag.
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /07/06 17:34:49 Check if none match etag boolean.
     */
    public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
        String headerValue = request.getHeader(HttpHeaders.IF_NONE_MATCH);
        if (headerValue != null) {
            boolean conditionSatisfied = false;
            if (!"*".equals(headerValue)) {
                StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

                while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(etag)) {
                        conditionSatisfied = true;
                    }
                }
            } else {
                conditionSatisfied = true;
            }

            if (conditionSatisfied) {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                response.setHeader(HttpHeaders.ETAG, etag);
                return false;
            }
        }
        return true;
    }

    /**
     * 设置让浏览器弹出下载对话框的Header.
     *
     * @param response the response
     * @param fileName 下载后的文件名.
     * @author Nick Lv
     * @created 2017 /07/06 17:34:53
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            // 中文文件名支持
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        }
    }

    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     * <p>
     * 返回的结果的Parameter名已去除前缀.
     *
     * @param request the request
     * @param prefix  the prefix
     * @return parameters starting with
     * @author Nick Lv
     * @created 2017 /07/06 17:34:55
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
        Validate.notNull(request, "Request must not be null");
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        String pre = prefix;
        if (pre == null) {
            pre = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(pre) || paramName.startsWith(pre)) {
                String unprefixed = paramName.substring(pre.length());
                String[] values = request.getParameterValues(paramName);
                if (values != null && values.length != 0) {
                    if (values.length > 1) {
                        params.put(unprefixed, values);
                    } else {
                        params.put(unprefixed, values[0]);
                    }
                }
            }
        }
        return params;
    }

    /**
     * 组合Parameters生成Query String的Parameter部分,并在paramter name上加上prefix.
     *
     * @param params the params
     * @param prefix the prefix
     * @return the string
     * @author Nick Lv
     * @created 2017 /07/06 17:34:59 Encode parameter string with prefix string.
     */

    public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
        StringBuilder queryStringBuilder = new StringBuilder();

        String pre = prefix;
        if (pre == null) {
            pre = "";
        }
        Iterator<Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            queryStringBuilder.append(pre).append(entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                queryStringBuilder.append("&");
            }
        }
        return queryStringBuilder.toString();
    }

    /**
     * 客户端对Http Basic验证的 Header进行编码.
     *
     * @param userName the user name
     * @param password the password
     * @return the string
     * @author Nick Lv
     * @created 2017 /07/06 17:35:01 Encode http basic string.
     */
    public static String encodeHttpBasic(String userName, String password) {
        String encode = userName + ":" + password;
        return "Basic " + Encodes.encodeBase64(encode.getBytes());
    }

    //
    // /**
    // * 是否是Ajax异步请求
    // * @param request
    // */
    // public static boolean isAjaxRequest(HttpServletRequest request){
    //
    // String accept = request.getHeader("accept");
    // String xRequestedWith = request.getHeader("X-Requested-With");
    // Principal principal = UserUtils.getPrincipal();
    //
    // // 如果是异步请求或是手机端，则直接返回信息
    // return ((accept != null && accept.indexOf("application/json") != -1
    // || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") !=
    // -1)
    // || (principal != null && principal.isMobileLogin())));
    // }
    //

    /**
     * 获取当前请求对象
     *
     * @return request request
     * @author Nick Lv
     * @created 2017 /07/06 17:35:04
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断访问URI是否是静态文件请求
     *
     * @param uri the uri
     * @return the boolean
     * @throws Exception
     * @author Nick Lv
     * @created 2017 /07/06 17:35:06 Is static file boolean.
     */
    public static boolean isStaticFile(String uri) {
        if (STATIC_FILES == null) {
            try {
                throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。" +
                        "配置示例：\n#静态文件后缀\n"
                        + "web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg," +
                        ".bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // if ((StringUtils.startsWith(uri, "/static/") ||
        // StringUtils.endsWithAny(uri, sfs))
        // && !StringUtils.endsWithAny(uri, ".jsp") &&
        // !StringUtils.endsWithAny(uri, ".java")){
        // return true;
        // }
        if (StringUtils.endsWithAny(uri, STATIC_FILES) && !StringUtils.endsWithAny(uri, URL_SUFFIX)
                && !StringUtils.endsWithAny(uri, ".jsp")
                && !StringUtils.endsWithAny(uri, ".java")) {
            return true;
        }
        return false;
    }
}
