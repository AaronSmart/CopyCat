/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import net.evecom.gsmp.gidcms.common.vo.usms.UserResource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/15.
 *
 * @author Iverson Cai
 * @created 2017 /08/15 15:30:12
 */
@Component
public class AppUtil implements ServletContextListener {

    /**
     * The constant logger.
     */
    private static Logger logger = Logger.getLogger(AppUtil.class);
    /**
     * The constant servletContext.
     */
    private static ServletContext servletContext;
    /**
     * The constant interfaceCodesMap.
     */
    private static Map<String, String> interfaceCodesMap = new HashMap();
    /**
     * The constant isAllowLoginRepeat.
     */
    private static String isAllowLoginRepeat;

    /**
     * Instantiates a new App util.
     */
    public AppUtil() {
    }

    /**
     * Gets is allow login repeat.
     *
     * @return the is allow login repeat
     */
    public static String getIsAllowLoginRepeat() {
        return isAllowLoginRepeat;
    }

    /**
     * Gets login user.
     *
     * @return the login user
     */
    public static User getLoginUser() {
        User userResource = new User();
        HttpSession session = getSession();
        if (session == null) {
            return userResource;
        } else {
            User curLoginUser = (User) session.getAttribute("loginUser");
            if (curLoginUser == null) {
                curLoginUser = new User();
            }
            return curLoginUser;
        }
    }

    /**
     * Gets login token.
     *
     * @return the login token
     */
    public static String getLoginToken() {
        HttpSession session = getSession();
        String curLoginUser = (String) session.getAttribute("accessToken");
        return curLoginUser;
    }

    /**
     * Remove login user.
     */
    public static void removeLoginUser() {
        HttpSession session = getSession();
        session.removeAttribute("loginUser");
    }

    /**
     * Remove login token.
     */
    public static void removeLoginToken() {
        HttpSession session = getSession();
        session.removeAttribute("accessToken");
    }

    /**
     * Gets session.
     *
     * @return the session
     */
    public static HttpSession getSession() {
        HttpSession session = null;

        try {
            session = getRequest().getSession();
        } catch (Exception e) {
            logger.error("无法获取session");
        }

        return session;
    }

    /**
     * Gets request.
     *
     * @return the request
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    /**
     * Add user to session.
     *
     * @param session      the session
     * @param curLoginUser the cur login user
     */
    public static void addUserToSession(HttpSession session, UserResource curLoginUser) {
        session.setAttribute("curLoginUser", curLoginUser);
    }

    /**
     * Add user to session.
     *
     * @param curLoginUser the cur login user
     */
    public static void addUserToSession(UserResource curLoginUser) {
        getSession().setAttribute("curLoginUser", curLoginUser);
    }

    /**
     * Add token to session.
     *
     * @param accessToken the access token
     */
    public static void addTokenToSession(String accessToken) {
        getSession().setAttribute("accessToken", accessToken);
    }


}
