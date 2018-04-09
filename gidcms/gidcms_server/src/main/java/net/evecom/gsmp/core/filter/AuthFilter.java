/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.filter;

import com.alibaba.fastjson.JSON;
import net.evecom.gsmp.gidcms.geography.utils.Constants;
import net.evecom.gsmp.gidcms.geography.vo.VUserInfo;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 权限控制过滤器
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class AuthFilter implements Filter {
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 获得 httpRequest
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 获得 httpResponse
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);

        // 获得 session 对象
        HttpSession session = httpRequest.getSession();

        // 获得授权码
        String authCode = request.getParameter("code");

        // 获得session中的accessToken
        String accessToken = (String) httpRequest.getSession().getAttribute("accessToken");

        // 获得请求地址
        String requestUrl = httpRequest.getRequestURL().toString();

        // 授权码不为空
        if (StringUtils.isNotEmpty(authCode)) {
            try {
                // 携带授权码向认证服务器发送请求，并获得 accessToken
                OAuthAccessTokenResponse oauthResponse = makeTokenRequestWithAuthCode(authCode, requestUrl);
                // 将accessToken存入session
                session.setAttribute("accessToken", oauthResponse.getAccessToken());
                setUserInfo(oauthResponse.getAccessToken(), session);
                chain.doFilter(request, response);
            } catch (OAuthProblemException | OAuthSystemException e) {
                LOGGER.error(e.getMessage());
                // 如果出现授权码错误，删除code参数
                String urlQueryString = this.removeParams(httpRequest.getQueryString(), "code");
                String redirectUrl;
                StringBuffer sb = new StringBuffer();
                if (StringUtils.isNotEmpty(urlQueryString)) {
                    sb.append(httpRequest.getRequestURL()).append("?").append(urlQueryString);
                    redirectUrl = sb.toString();
                } else {
                    redirectUrl = httpRequest.getRequestURL().toString();
                }
                wrapper.sendRedirect(redirectUrl);
            }
        } else {
            // 构造重定向地址
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.OAUTH_SERVER_AUTH_URL)
                    .append("?client_id=")
                    .append(Constants.CLIENT_ID)
                    .append("&response_type=code&redirect_uri=")
                    .append(requestUrl);
            String redirectUrl = sb.toString();

            // 如果session中有令牌，且access_token有效，则继续请求，否则重定向至OAuth2服务器进行认证
            if (StringUtils.isNotEmpty(accessToken) && checkAccessToken(httpRequest, accessToken)) {
                chain.doFilter(request, response);
            } else {
                wrapper.sendRedirect(redirectUrl);
            }
        }
    }

    /**
     * 根据授权码获取accessToken
     *
     * @param authCode
     * @return accessToken响应
     * @author Nick Lv
     * @throws OAuthProblemException
     * @throws OAuthSystemException
     */
    private OAuthAccessTokenResponse makeTokenRequestWithAuthCode(String authCode, String redirectUri)
            throws OAuthProblemException, OAuthSystemException {
        OAuthClientRequest request = OAuthClientRequest
                .tokenLocation(Constants.OAUTH_SERVER_TOKEN_URL)
                .setClientId(Constants.CLIENT_ID)
                .setClientSecret(Constants.CLIENT_SECRET)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setCode(authCode)
                .setRedirectURI(redirectUri)
                .buildBodyMessage();
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthAccessTokenResponse oauthResponse = oAuthClient.accessToken(request);
        return oauthResponse;
    }

    /**
     * 验证accessToken是否有效
     *
     * @param accessToken
     * @author Nick Lv
     * @throws IOException
     */
    private boolean checkAccessToken(HttpServletRequest request, String accessToken) throws IOException {
        String checkAccessCodeUrl = Constants.SERVER_URL + "/checkAccessToken?access_token=";
        URL url = new URL(checkAccessCodeUrl + accessToken);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.disconnect();
        return HttpServletResponse.SC_OK == conn.getResponseCode();
    }

    /**
     * 删除url中的参数
     *
     * @param queryString
     * @param params
     * @return
     * @author Nick Lv
     */
    private String removeParams(String queryString, String... params) {
        for (String param : params) {
            String keyValue = param + "=[^&]*?";
            queryString = queryString.replaceAll("(&" + keyValue + "(?=(&|$))|^" + keyValue + "(&|$))", "");
        }
        return queryString;
    }

    @Override
    public void destroy() {

    }

    /**
     * Sets user info.
     *
     * @param accessToken the access token
     * @param session     the session
     * @author Nick Lv
     */
    public void setUserInfo(String accessToken, HttpSession session) {
        org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(Constants.OAUTH_SERVICE_API).append("?access_token=").append(accessToken);
        String userInfoUrl = sBuilder.toString();
        GetMethod getMethod = new GetMethod(userInfoUrl);
        int statusCode = 0;
        try {
            // 发起get方法的验证
            statusCode = httpClient.executeMethod(getMethod);
            if (HttpStatus.SC_OK == statusCode) {
                byte[] userInfoByte = getMethod.getResponseBody();
                VUserInfo info = JSON.parseObject(new String(userInfoByte, "utf-8"), VUserInfo.class);
                session.setAttribute("userInfo", info);
            }
        } catch (IOException e) {
            LOGGER.error("httpClientGET方法异常", e);
        }
    }
}



