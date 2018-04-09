/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.controller;


import com.alibaba.fastjson.JSON;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.gidcms.common.service.UsmsService;
import net.evecom.gsmp.gidcms.common.util.*;
import net.evecom.gsmp.gidcms.common.util.EveException;
import net.evecom.gsmp.gidcms.common.util.ReceiveBody;
import net.evecom.gsmp.gidcms.common.vo.usms.UserResource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 单点登录Controller.
 *
 * @author Iverson Cai
 * @created 2017 /05/11 11:20:58
 */
@Controller
@RequestMapping("/usms")
public class UsmsController {

    /**
     * 统一管理系统Service
     */
    @Autowired
    private UsmsService usmsService;

    /**
     * 日志.
     */
    public static final Logger LOGGER = Logger.getLogger(UsmsController.class);
    /**
     * 检查token的url
     */
    @Value("#{configProperties.CHECK_TOKEN_URL}")
    private String checkTokenUrl;
    /**
     * 服务器地址.
     */
    @Value("#{configProperties.SERVER_URL}")
    private String serverUrl;
    /**
     * 获取用户信息
     */
    @Value("#{configProperties.USER_INFO_URL}")
    private String userInfoUrl;

    /**
     * 描述 获取用户信息.
     *
     * @param receiveBody the receive body
     * @param request     the request
     * @return the v return message
     * @author Iverson Cai
     * @created 2017 /08/15 11:36:37 Make token request with auth code v return message.
     */
    @ResponseBody
    @RequestMapping("/getUser")
    @NoneToken
    public VReturnMessage makeTokenRequestWithAuthCode(@RequestBody ReceiveBody receiveBody,
                                                       HttpServletRequest request) {
        //获取参数
        Map<String, Object> queryParam = receiveBody.getQueryParams();
        String authCode = (String) queryParam.get("authCode");
        String redirectUri = (String) queryParam.get("redirectUri");
        User ssoUser;
        VReturnMessage vReturnMessage = new VReturnMessage(true);
        try {
            String token = usmsService.getToken(authCode, redirectUri);
            request.getSession().setAttribute("accessToken", token);
            ssoUser = usmsService.getLoginUserInfo(token);
            request.getSession().setAttribute("loginUser", ssoUser);
        } catch (EveException e) {
            EveException eveException = e;
            vReturnMessage = eveException.getReturnMessage();
            return vReturnMessage;
        }
        vReturnMessage.setData(ssoUser);
        return vReturnMessage;
    }

    /**
     * 登出
     *
     * @return the v return message
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /10/05 16:59:27 Loginout v return message.
     */
    @ResponseBody
    @RequestMapping("/loginOut")
    @NoneToken
    public VReturnMessage loginout() throws Exception {
        VReturnMessage vReturnMessage = new VReturnMessage();
        vReturnMessage.setResult(true);
        Boolean success = false;
        String token = UsmsUtils.getLoginToken();
        if (StringUtils.isEmpty(token)) return vReturnMessage;
        if (!usmsService.verifyToken(token)) return vReturnMessage;
        if (!StringUtils.isEmpty(token)) {
            success = usmsService.loginout(UsmsUtils.getLoginToken());
        }
        if (success) {
            vReturnMessage.setMessage("成功！");
            vReturnMessage.setResult(true);
        } else {
            vReturnMessage.setMessage("失败！");
            vReturnMessage.setResult(false);
        }
        return vReturnMessage;
    }

    /**
     * iframe使用的单点登陆
     *
     * @param receiveBody the receive body
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /10/05 16:59:27 Login v return message.
     */
    @NoneToken
    @RequestMapping(value = {"/login" }, method = {RequestMethod.POST})
    @ResponseBody
    public VReturnMessage login(@RequestBody ReceiveBody receiveBody) {
        VReturnMessage vReturnMessage = new VReturnMessage();
        String authCode = receiveBody.getAuthCode();
        String clientId = receiveBody.getClientId();
        String loginName = receiveBody.getLoginName();
        String tokenCode = receiveBody.getTokenCode();
        String url = receiveBody.getUrl();
        String responseJson;
        // clinetId不为空、loginName不为空
        // 有效返回true，否则为false
        // 此if为登录用
        if (!org.springframework.util.StringUtils.isEmpty(clientId)
                && !org.springframework.util.StringUtils.isEmpty(loginName)
                && !UsmsUtils.isTokenValidate(UsmsUtils.getLoginToken(),
                serverUrl + checkTokenUrl)) {
            responseJson = UsmsUtils.getResponseJson(serverUrl
                    + "/v1/internalapi/user?client_id=" + clientId + "&login_name=" + loginName);
            LOGGER.info("获取到资源信息tokenResponse:" + responseJson);
            String tempToken = UsmsUtils.getTokenByUserData(responseJson);
            UsmsUtils.addTokenToSession(tempToken);
        }
        // token为空返回false，无效返回false，有效返回true
        // 进这里面，是为了获取token
        if (!org.springframework.util.StringUtils.isEmpty(tokenCode)
                && !UsmsUtils.isTokenValidate(UsmsUtils.getLoginToken(),
                serverUrl + checkTokenUrl)) {
            UsmsUtils.addTokenToSession(tokenCode);
        }
        // token为空返回false，无效返回false，有效返回true
        // 进这里面，是为了获取token
        if (!org.springframework.util.StringUtils.isEmpty(authCode)
                && !UsmsUtils.isTokenValidate(UsmsUtils.getLoginToken(),
                serverUrl + checkTokenUrl)) {
            try {
                UsmsUtils.addTokenToSession(this.usmsService.getTokenNew(authCode, url));
            } catch (Exception e) {
                LOGGER.error("获取token异常", e);
            }
        }
        // token为空返回false，无效返回false，有效返回true
        // 如果token有效则进入else
        if (!UsmsUtils.isTokenValidate(UsmsUtils.getLoginToken(),
                serverUrl + checkTokenUrl)) {
            vReturnMessage.setResultAndMsg("10001", "TOKEN无效");
            return vReturnMessage;
        } else {
            LOGGER.debug("获取令牌成功:" + UsmsUtils.getLoginToken());
            responseJson = UsmsUtils.getResponseJson(serverUrl +
                    userInfoUrl +
                    (UsmsUtils.getLoginToken()));
            LOGGER.info("获取到资源信息:" + responseJson);
            UserResource userResource = (UserResource) JSON.parseObject(responseJson, UserResource.class);
            UsmsUtils.addUserToSession(userResource);
            vReturnMessage.setData(userResource);
            vReturnMessage.setResult(true);
        }
        return vReturnMessage;
    }
}
