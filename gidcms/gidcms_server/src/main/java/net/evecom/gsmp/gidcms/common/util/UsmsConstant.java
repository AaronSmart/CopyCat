/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import org.springframework.stereotype.Component;

/**
 * 统一用户管理系统中的常量信息
 * @author Iverson Cai   
 * @created 2017 /09/06 11:51:12
 */
@Component
public class UsmsConstant {

    /**
     * 应用ID
     */
    public static String clientId;
    /**
     * 应用secret
     */
    public static String clientSecret;
    /**
     * 服务器URL地址
     */
    public static String serverUrl;
    /**
     * 获取token地址
     */
    public static String oauthServerTokenUrl;
    /**
     * 获取用户信息地址
     */
    public static String userInfoUrl;
    /**
     * 登录地址
     */
    public static String loginOutUrl;
    /**
     * 根据登录名，获取用户信息,包括token信息
     */
    public static String userTokenUrl;
    /**
     * 获取用户所管辖的网格
     */
    public static String userGridUrl;
    /**
     * 验证token是否有效
     */
    public static String checkTokenUrl;

}
