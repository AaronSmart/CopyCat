/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.utils;

/**
 * 封装OAuth Server端认证需要的参数
 *
 * @author Nick Lv
 * @created 2017 /06/12 18:00:20
 */
public class Constants {


    /**
     * The constant CLIENT_ID.
     */
    public static final String CLIENT_ID = "4edc1159-0b7b-44ff-b71a-8efda6100eaa";


    /**
     * The constant CLIENT_SECRET.
     */
    public static final String CLIENT_SECRET = "4b83dbe5-401a-47bc-847b-f0c9bfce6b90";


    /**
     * The constant CLIENT_INDEX_URL.
     */
    public static final String CLIENT_INDEX_URL = "http://localhost:8225/";


    /**
     * The constant SERVER_URL.
     */
    public static final String SERVER_URL = "http://192.168.200.209:8080/usms";


    /**
     * The constant USERNAME.
     */
    public static final String USERNAME = "admin";


    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "123456";


    /**
     * The constant OAUTH_SERVER_AUTH_URL.
     */
    public static final String OAUTH_SERVER_AUTH_URL =  SERVER_URL + "/authorize";


    /**
     * The constant OAUTH_SERVER_TOKEN_URL.
     */
    public static final String OAUTH_SERVER_TOKEN_URL =  SERVER_URL + "/accessToken";


    /**
     * The constant OAUTH_SERVER_REDIRECT_URI.
     */
    public static final String OAUTH_SERVER_REDIRECT_URI =  "http://www.baidu.com";


    /**
     * The constant OAUTH_SERVICE_API.
     */
    public static final String OAUTH_SERVICE_API =  SERVER_URL + "/v1/openapi/user";

    /**
     * The constant OAUTH_LOGIN_OUT.
     */
    public static final String OAUTH_LOGIN_OUT = SERVER_URL + "/v1/openapi/loginOut";
}
