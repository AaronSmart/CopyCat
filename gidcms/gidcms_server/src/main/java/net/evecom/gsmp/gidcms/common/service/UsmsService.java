/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.service;


import net.evecom.gsmp.gidcms.common.util.EveException;
import net.evecom.gsmp.gidcms.common.util.InvalidAgentException;
import net.evecom.gsmp.gidcms.common.util.TokenEmptyException;
import net.evecom.gsmp.gidcms.common.util.User;

/**
 * 统一用户管理系统服务层
 *
 * @author Iverson Cai
 * @created 2017 /08/15 09:35:00
 */
public interface UsmsService {

    /**
     * 描述 获取统一用户管理系统登录后的token.
     *
     * @param authCode    临时授权码
     * @param redirectUri 登录成功后的跳转地址（前后端须一致）
     * @return the token
     * @throws EveException the eve exception
     * @author Iverson Cai
     * @created 2017 /08/15 09:47:38
     */
    String getToken(String authCode, String redirectUri) throws EveException;

    /**
     * 描述 获取用户信息
     *
     * @param token token
     * @return the login user info
     * @throws EveException the eve exception
     * @author Iverson Cai
     * @created 2017 /08/15 10:49:27
     */
    User getLoginUserInfo(String token) throws EveException;

    /**
     * 描述 验证token是否有效.
     *
     * @param token the token
     * @return the v return message
     * @throws TokenEmptyException the token empty exception
     * @author Iverson Cai
     * @created 2017 /08/15 11:40:47 Verify token v return message.
     */
    Boolean verifyToken(String token) throws TokenEmptyException;

    /**
     * 描述 Gets token by login name and client id.
     *
     * @param loginName the login name
     * @param clientId  the client id
     * @return the token by login name and client id
     * @author Iverson Cai
     * @created 2017 /08/15 16:16:01
     */
    String getTokenByLoginNameAndClientId(String loginName, String clientId);

    /**
     * 描述 登出系统.
     *
     * @param token the token
     * @return the boolean
     * @author Iverson Cai
     * @created 2017 /08/21 18:01:09 Loginout boolean.
     */
    Boolean loginout(String token);

    /**
     * 获取token
     *
     * @param authCode the auth code
     * @param url      the url
     * @return token new
     */
    String getTokenNew(String authCode, String url) throws InvalidAgentException;
}
