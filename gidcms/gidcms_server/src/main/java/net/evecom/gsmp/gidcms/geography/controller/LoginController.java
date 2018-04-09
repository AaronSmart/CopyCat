/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.controller;

import com.alibaba.fastjson.JSON;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.gidcms.geography.vo.VStaff;
import net.evecom.gsmp.gidcms.geography.vo.VOperations;
import net.evecom.gsmp.gidcms.geography.vo.VUserInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

/**
 * 登录控制
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /07/10 17:42:15
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/login")
public class LoginController {

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
     * 验证token的地址.
     */
    @Value("#{configProperties.POST_TOKEN_URL}")
    private String postTokenUrl;

    /**
     * 服务器地址.
     */
    @Value("#{configProperties.SERVER_URL}")
    private String serverUrl;

    /**
     * 获取用户信息的url.
     */
    @Value("#{configProperties.USER_INFO_URL}")
    private String userInfoUrl;
    /**
     * The Login out url.
     */
    @Value("#{configProperties.LOGIN_OUT_URL}")
    private String loginOutUrl;
    /**
     * The Current token class url.
     */
    @Value("#{configProperties.CURRENT_TOKEN_URL}")
    private String currentTokenClassUrl;
    /**
     * The Head html.
     */
    @Value("#{configProperties.HEAD_HTML}")
    private String headHtml;

    /**
     * The Application name.
     */
    @Value("#{configProperties.APPLICATION_NAME}")
    private String applicationName;
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * 无权限.
     */
    private static final int NO_POWER = 550;

    /**
     * 登出系统
     *
     * @param req  the req
     * @param resp the resp
     * @author Nick Lv
     * @created 2017 /07/10 17:42:15 Login out by token.
     */
    @RequestMapping("/loginOut")
    public void loginOutByToken(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String token = (String) req.getSession().getAttribute("accessToken");
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(loginOutUrl)
                    .append(token);
            byte[] bytesResult = getResultFromAuth(sBuilder.toString());
            if (null != bytesResult) {
                String responseResult = new String(bytesResult, "utf-8");
                if (responseResult.contains("true")) {
                    HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(resp);
                    req.getSession().invalidate();
                    redirectHtml(wrapper);
                }
            }
        } catch (IOException e) {
            LOGGER.error("重定向到登出页面失败", e);
        }
    }


    /**
     * 从session中获取用户信息.
     *
     * @param req  the req
     * @param resp the resp
     * @return the user info from session
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16
     */
    @RequestMapping("/userInfo")
    public VStaff getUserInfoFromSession(HttpServletRequest req, HttpServletResponse resp) {
        VUserInfo vUserInfo = (VUserInfo) req.getSession().getAttribute("userInfo");
        return vUserInfo.getStaff();
    }

    /**
     * 获取用户操作权限.
     *
     * @param req  the req
     * @param resp the resp
     * @return the operations from session
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16
     */
    @RequestMapping("/operation")
    public List<VOperations> getOperationsFromSession(HttpServletRequest req, HttpServletResponse resp) {
        VUserInfo vUserInfo = (VUserInfo) req.getSession().getAttribute("userInfo");
        return vUserInfo.getOperations();
    }

    /**
     * 检查是否有操作权限
     *
     * @param req       the req
     * @param resp      the resp
     * @param operation the operation
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16 Check operation v return message.
     */
    @RequestMapping("/checkOperation")
    public VReturnMessage checkOperation(HttpServletRequest req, HttpServletResponse resp, String operation) {
        VReturnMessage vReturnMessage = new VReturnMessage();
        if (Objects.equals(req.getRequestURL().toString(), headHtml)) {
            return checkHeadHtml(req, resp, req.getRequestURL());
        }
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(resp);
        if (!StringUtils.isEmpty(operation)) {
            //获取存在session中的token
            int isNotWorkToken = checkTokenTime(req);
            if (HttpStatus.SC_OK == isNotWorkToken) {
                String accessToken = (String) req.getSession().getAttribute("accessToken");
                vReturnMessage = isNotRightfulOperational(operation, accessToken, req);
            } else {
                // 重定向前端页面
                redirectHtml(wrapper);
            }
        }
        return vReturnMessage;
    }

    /**
     * 检测是否合法操作
     *
     * @param operation   the operation
     * @param accessToken the access token
     * @param req         the req
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16 Is not rightful operational v return message.
     */
    private VReturnMessage isNotRightfulOperational(String operation, String accessToken, HttpServletRequest req) {
        VReturnMessage vReturnMessage = new VReturnMessage();
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("http://192.168.200.209:8080/usms/v1/openapi/operation/exist?operation=")
                .append(operation).append("&access_token=").append(accessToken);
        String operationUrl = sBuilder.toString();
        byte[] byteAccess = getResultFromAuth(operationUrl);
        if (null != byteAccess) {
            String isNotAccess = null;
            try {
                isNotAccess = new String(byteAccess, "utf-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("不支持的字符类型", e);
            }
            JSONObject jsonObject = new JSONObject(isNotAccess);
            VUserInfo userInfo = (VUserInfo) req.getSession().getAttribute("userInfo");
            vReturnMessage.setData(userInfo);
            if (Objects.equals(jsonObject.get("result"), true)) {
                // 如果操作是合法的，result中为true，进入这里
                vReturnMessage.setResult(true);
            } else {
                vReturnMessage.setResult(false);
                vReturnMessage.setCode(NO_POWER);
            }
        }
        return vReturnMessage;
    }


    /**
     * 检测是否首页.
     *
     * @param req        the req
     * @param resp       the resp
     * @param requestURL the request url
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16 Check head html v return message.
     */
    private VReturnMessage checkHeadHtml(HttpServletRequest req, HttpServletResponse resp, StringBuffer requestURL) {
        VReturnMessage vReturnMessage = new VReturnMessage();
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(resp);
        int statusCode = checkTokenTime(req);
        if (HttpStatus.SC_OK == statusCode) {
            vReturnMessage.setResult(true);
            vReturnMessage.setData(req.getSession().getAttribute("userInfo"));
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(serverUrl).append("/authorize?client_id=").append(clientId)
                    .append("&response_type=code&redirect_uri=").append(currentTokenClassUrl);
            try {
                wrapper.sendRedirect(stringBuilder.toString());
            } catch (IOException e) {
                LOGGER.error("重定向方法io异常", e);
            }
        }
        return vReturnMessage;
    }

    /**
     * 检查token时间.
     *
     * @param req the req
     * @return the int
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16 Check token time int.
     */
    private int checkTokenTime(HttpServletRequest req) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("http://192.168.200.209:8080/usms/checkAccessToken?access_token=")
                .append(req.getSession().getAttribute("accessToken"));
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(sBuilder.toString());
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(getMethod);
        } catch (IOException e) {
            LOGGER.error("getMethod获取返回的body异常", e);
        }
        return statusCode;
    }

    /**
     * 根据authCode换取token.
     *
     * @param authCode the auth code
     * @param req      the req
     * @param resp     the resp
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16
     */
    @RequestMapping("/guard")
    @Deprecated
    public void getTokenByAuthCode(@RequestParam("code") String authCode,
                                   HttpServletRequest req, HttpServletResponse resp) {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(resp);
        try {
            OAuthClientRequest ocRequest = OAuthClientRequest.tokenLocation(postTokenUrl)
                    .setClientId(clientId)
                    .setClientSecret(clientSecurityKey)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setCode(authCode)
                    .setRedirectURI(currentTokenClassUrl)
                    .buildBodyMessage();
            OAuthClient oaClient = new OAuthClient(new URLConnectionClient());
            OAuthAccessTokenResponse oaResponse = oaClient.accessToken(ocRequest);
            String tokenValue = oaResponse.getAccessToken();
            req.getSession().setAttribute("accessToken", tokenValue);
            HttpClient httpClient = new HttpClient();
            String userUrl = userInfoUrl + tokenValue;
            GetMethod getMethod = new GetMethod(userUrl);
            try {
                int statusCode = httpClient.executeMethod(getMethod);
                if (HttpStatus.SC_OK == statusCode) {
                    byte[] userInfo = getMethod.getResponseBody();
                    String tempUser = new String(userInfo, "utf-8");
                    VUserInfo info = JSON.parseObject(tempUser, VUserInfo.class);
                    LOGGER.info(new String(userInfo, "utf-8"));
                    info.getApplications().forEach(application -> {
                        if (Objects.equals(application.getName(), applicationName)) {
                            req.getSession().setAttribute("userInfo", info);
                            try {
                                wrapper.sendRedirect(headHtml);
                            } catch (IOException e) {
                                LOGGER.error("页面重定向IO异常", e);
                            }
                        } else {
                            // 跳到无权限登录此系统方法
                        }
                    });

                }
            } catch (IOException e) {
                LOGGER.error("页面请求异常", e);
            } finally {
                getMethod.releaseConnection();
            }
        } catch (OAuthSystemException | OAuthProblemException e) {
            LOGGER.error("构建认证请求异常", e);
        }
    }

    /**
     * 根据链接获取结果
     *
     * @param url the url
     * @return the byte[]
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16 Get result from auth byte [ ].
     */
    private byte[] getResultFromAuth(String url) {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        byte[] responseBody = null;
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (HttpStatus.SC_OK == statusCode) {
                responseBody = getMethod.getResponseBody();
            }
        } catch (Exception e) {
            LOGGER.error("根据链接获取响应异常", e);
        }
        return responseBody;
    }

    /**
     * 重定认证页面
     *
     * @param wrapper the wrapper
     * @author Nick Lv
     * @created 2017 /07/10 17:42:16 Redirect html.
     */
    private void redirectHtml(HttpServletResponseWrapper wrapper) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(serverUrl).append("/authorize?client_id=").append(clientId)
                .append("&response_type=code&redirect_uri=").append(currentTokenClassUrl);
        try {
            wrapper.sendRedirect(stringBuilder.toString());
        } catch (IOException e) {
            LOGGER.error("重定向方法io异常", e);
        }
    }
}



