/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import net.evecom.gsmp.gidcms.common.service.UsmsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by lenovo on 2017/8/21.
 *
 * @author Iverson Cai
 * @created 2017 /08/21 16:46:45
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    /**
     * The Debug.
     */
    private boolean debug = false;

    /**
     * The Usms service.
     */
    @Autowired
    private UsmsService usmsService;
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /*if (!debug) {
            return true;
        }*/
        logger.info("当前模式为：" + request.getHeader("CLIENT-MODE".toLowerCase()));
        if (request.getHeader("CLIENT-MODE".toLowerCase()) != null) {
            if ("APP".equals(request.getHeader("CLIENT-MODE".toLowerCase()).toUpperCase()))
                return true;
            if ("IFRAME".equals(request.getHeader("CLIENT-MODE".toLowerCase()).toUpperCase()))
                return true;
        }
        //首先判断是否是Debug模式(全局)，如果否则使拦截器失效
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            NoneToken noneToken = method.getMethodAnnotation(NoneToken.class);
            if (noneToken != null)
                return true;
        } else {
            return true;
        }
        //若token为空则重新获取token
        String token = AppUtil.getLoginToken();

        if (StringUtils.isEmpty(token)) {
            //根据loginName、clientId获取token（APP端请求的参数中带有这2个数据）

            String loginName = request.getParameter("loginName");
            String clientId = request.getParameter("clientId");
            if (!StringUtils.isEmpty(loginName) && !StringUtils.isEmpty(clientId)) {
                token = usmsService.getTokenByLoginNameAndClientId(loginName, clientId);
            }
        }
        if (StringUtils.isEmpty(token)) {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print("{\"message\":\"token is null\",\"tokenError\":" + true + "}");
            return false;
        } else if (!usmsService.verifyToken(token)) {
            //token不为空则请求验证token
            PrintWriter out = response.getWriter();
            AppUtil.removeLoginToken();
            AppUtil.removeLoginUser();
            response.setCharacterEncoding("utf-8");
            out.print("{\"message\":\"token is out of data\",\"tokenError\":" + true + "}");
            return false;
        }
        return true;
    }

}
