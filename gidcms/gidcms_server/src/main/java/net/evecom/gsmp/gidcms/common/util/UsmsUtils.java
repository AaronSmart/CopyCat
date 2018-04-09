/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.evecom.gsmp.gidcms.common.vo.usms.UserResource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 单点登录工具类
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /10/05 16:49:54
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class UsmsUtils {
    /**
     * 日志记录
     */
    private static Logger logger = Logger.getLogger(UsmsUtils.class);
    /**
     * mock的http的sesson
     */
    private static HttpSession moke = new MockHttpSession();

    /**
     * 验证token是否有效
     *
     * @param token    the token
     * @param checkUrl the check url
     * @return the boolean 如果token为空，则return false;如果执行结果是200，则为true，否则为false
     * @author Nick Lv
     * @created 2017 /10/05 16:49:54 Is token validate boolean.
     */
    public static boolean isTokenValidate(String token, String checkUrl) {
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

    /**
     * 获取request
     *
     * @return the request
     * @author Nick Lv
     * @created 2017 /10/05 16:52:09
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取session
     *
     * @return the session
     * @author Nick Lv
     * @created 2017 /10/05 16:52:09
     */
    public static HttpSession getSession() {
        HttpSession session = null;

        try {
            session = getRequest().getSession();
        } catch (Exception var2) {
            session = moke;
            logger.error("无法获取session,使用MockHttpSession");
        }

        return session;
    }

    /**
     * 从session中获取token信息
     *
     * @return the login token
     * @author Nick Lv
     * @created 2017 /10/05 16:50:46
     */
    public static String getLoginToken() {
        HttpSession session = getSession();
        String curLoginUser = (String) session.getAttribute("accessToken");
        return curLoginUser;
    }

    /**
     * 将token放入session中
     *
     * @param accessToken the access token
     * @author Nick Lv
     * @created 2017 /10/05 16:58:48 Add token to session.
     */
    public static void addTokenToSession(String accessToken) {
        getSession().setAttribute("accessToken", accessToken);
    }

    /**
     * 根据url获取json
     *
     * @param url the url
     * @return response json
     * @author Nick Lv
     * @created 2017 /10/05 16:58:48
     */
    public static String getResponseJson(String url) {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        String responseJson = "";

        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != 200) {
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
     * 从userdata中获取token信息
     *
     * @param tokenResponse the token response
     * @return token by user data
     * @author Nick Lv
     * @created 2017 /10/05 16:58:48
     */
    public static String getTokenByUserData(String tokenResponse) {
        JSONObject jsonObject = JSON.parseObject(tokenResponse);
        if (jsonObject == null) {
            return "";
        } else {
            JSONObject userObject = (JSONObject) jsonObject.get("user");
            if (userObject == null) {
                return "";
            } else {
                String tempToken = (String) userObject.get("accessToken");
                return tempToken;
            }
        }
    }

    /**
     * 将用户信息放入session中
     *
     * @param curLoginUser the cur login user
     * @author Nick Lv
     * @created 2017 /10/05 16:58:48 Add user to session.
     */
    public static void addUserToSession(UserResource curLoginUser) {
        getSession().setAttribute("curLoginUser", curLoginUser);
    }

    /**
     * 移除登录用户信息
     *
     * @author Nick Lv
     * @created 2017 /10/05 17:19:42 Remove login user.
     */
    public static void removeLoginUser() {
        HttpSession session = getSession();
        session.removeAttribute("loginUser");
    }

    /**
     * 移除token
     *
     * @author Nick Lv
     * @created 2017 /10/05 17:19:42 Remove login token.
     */
    public static void removeLoginToken() {
        HttpSession session = getSession();
        session.removeAttribute("accessToken");
    }
}



