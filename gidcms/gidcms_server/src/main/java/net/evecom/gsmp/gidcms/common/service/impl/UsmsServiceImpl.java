/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.service.impl;


import com.alibaba.fastjson.JSON;
import net.evecom.gsmp.gidcms.common.service.UsmsService;
import net.evecom.gsmp.gidcms.common.util.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 统一用户管理系统服务层实现
 *
 * @author Iverson Cai
 * @created 2017 /08/15 09:35:25
 */
@Service
@Transactional
public class UsmsServiceImpl implements UsmsService {

    /**
     * 日志信息
     */
    private static Logger logger = Logger.getLogger(UsmsServiceImpl.class);
    /**
     * 客户端ID.
     */
    @Value("#{configProperties.CLIENT_ID}")
    private String clientId;
    /**
     * 客户端安全KEY.
     */
    @Value("#{configProperties.CLIENT_SECRET}")
    private String clientSecurityKey;
    /**
     * 服务器地址.
     */
    @Value("#{configProperties.SERVER_URL}")
    private String serverUrl;
    /**
     * token换取地址
     */
    @Value("#{configProperties.OAUTH_SERVER_TOKEN_URL}")
    private String oauthServerTokenUrl;
    /**
     * 获取用户信息
     */
    @Value("#{configProperties.USER_TOKEN_URL}")
    private String userInfoUri;
    /**
     * 检查token
     */
    @Value("#{configProperties.CHECK_TOKEN_URL}")
    private String checkTokenUri;
    /**
     * 登出
     */
    @Value("#{configProperties.LOGIN_OUT_URL}")
    private String loginOutUri;

    @Override
    public String getToken(String authCode, String redirectUri) throws EveException {
        String token = "";
        EveException eveException = new EveException();
        if (StringUtils.isEmpty(authCode) || StringUtils.isEmpty(redirectUri))
            throw new EveException("9999", "获取token异常，authCode或redirectUri为空。");
        try {
            redirectUri = URLDecoder.decode(redirectUri, "utf-8");
            //获取token
            OAuthClientRequest oAuthClientRequest = OAuthClientRequest
                    .tokenLocation(serverUrl + oauthServerTokenUrl)
                    .setClientId(clientId).setClientSecret(clientSecurityKey)
                    .setGrantType(GrantType.AUTHORIZATION_CODE).setCode(authCode).setRedirectURI(redirectUri)
                    .buildBodyMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthAccessTokenResponse oauthResponse = null;
            oauthResponse = oAuthClient.accessToken(oAuthClientRequest);
            token = oauthResponse.getAccessToken();
        } catch (UnsupportedEncodingException e) {
            eveException.setMessage("单点登录回调地址转码异常:" + e.getMessage());
            throw eveException;
        } catch (OAuthSystemException e) {
            eveException.setMessage("获取token，OAuthSystemException异常:" + e.getMessage());
            throw eveException;
        } catch (OAuthProblemException e) {
            eveException.setMessage("获取token，OAuthClientRequest异常:" + e.getMessage());
            throw eveException;
        }
        return token;
    }

    @Override
    public User getLoginUserInfo(String token) throws EveException {
        String url = serverUrl +  "/v1/openapi/user?access_token=" + token;
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        String responseJson = "";
        User ssoUser;
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                EveException eveException = new EveException();
                eveException.setMessage("根据token获取用户信息出错；错误代码：" + getMethod.getStatusLine());
                throw eveException;
            } else {
                byte[] responseBody = getMethod.getResponseBody();
                responseJson = new String(responseBody, "utf-8");
                ssoUser = JSON.parseObject(responseJson, User.class);
            }
        } catch (IOException e) {
            EveException eveException = new EveException();
            eveException.setMessage("用户JSON数据解析出错。");
            throw eveException;
        } finally {
            getMethod.releaseConnection();
        }
        return ssoUser;
    }

    @Override
    public Boolean verifyToken(String token) throws TokenEmptyException {
        if (StringUtils.isEmpty(token))
            throw new TokenEmptyException();
        String url = serverUrl + checkTokenUri + token;
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                return true;
            }
        } catch (IOException e) {
            logger.error("验证token异常！", e);
        }
        return false;
    }

    @Override
    public String getTokenByLoginNameAndClientId(String loginName, String clientId) {
        String token = null;
        if (!StringUtils.isEmpty(loginName) && !StringUtils.isEmpty(clientId)) {
            String url = serverUrl + userInfoUri + "?login_name=" + loginName
                    + "&client_id=" + clientId;
            String result = HttpUtils.getJsonByUrl(url);
            try {
                Map<String, Object> map = JSONUtils.json2map(result);
                token = (String) ((Map) map.get("user")).get("accessToken");
            } catch (Exception e) {
                logger.info("解析json数据异常", e);
            }
        }
        return token;
    }

    @Override
    public Boolean loginout(String token) {
        if (StringUtils.isEmpty(token))
            return false;
        String result = "";
        String url = serverUrl + loginOutUri + token;
        result = HttpUtils.getJsonByUrl(url);
        try {
            Map<String, Object> loginOutResult = JSONUtils.json2map(result);
            if (loginOutResult.get("success").equals(true)) {
                UsmsUtils.removeLoginUser();
                UsmsUtils.removeLoginToken();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.info("登出异常", e);
        }
        return false;
    }

    @Override
    public String getTokenNew(String authCode, String url) throws InvalidAgentException {
        int errorCode = -1;
        String result = "";
        String errorMessage = "";
        OAuthJSONAccessTokenResponse oauthResponse = null;
        boolean var14 = false;
        InvalidAgentException exception;
        try {
            var14 = true;
            logger.debug("准备执行accessToken");
            logger.info(url);
            OAuthClientRequest oAuthClientRequest = OAuthClientRequest.
                    tokenLocation(serverUrl + oauthServerTokenUrl).
                    setClientId(clientId).
                    setClientSecret(clientSecurityKey).
                    setGrantType(GrantType.AUTHORIZATION_CODE).
                    setCode(authCode).
                    setRedirectURI(url).
                    buildBodyMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            oauthResponse = oAuthClient.accessToken(oAuthClientRequest);
            result = oauthResponse.getAccessToken();
            var14 = false;
        } catch (OAuthProblemException ope) {
            logger.error("OAuthProblemException-accessToken执行失败");
            errorCode = ope.getResponseStatus();
            errorMessage = ope.getMessage();
            logger.error("accessToken失败原因", ope);
            var14 = false;
        } catch (OAuthSystemException ose) {
            logger.error("OAuthSystemException-accessToken执行失败");
            errorMessage = "失败,系统内部异常";
            errorCode = Integer.valueOf("9999").intValue();
            logger.error("accessToken失败原因", ose);
            var14 = false;
        } finally {
            if (var14) {
                if (errorCode != -1) {
                    logger.error("oauth失败：" + errorMessage);
                    logger.error("oauth失败,errorCode：" + errorCode);
                    exception = new InvalidAgentException();
                    exception.setErrorCodeAndMessage(String.valueOf(errorCode), errorMessage);
                    throw exception;
                }
            }
        }
        logger.debug("结束获取令牌,result=" + result);
        return result;
    }
}
