/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 前端数据接收类.
 *
 * @param <T> the type parameter
 * @author Iverson Cai
 * @created 2017 /05/08 16:27:38
 */
public class ReceiveBody<T> {

    /**
     * 日志.
     */
    public static Logger logger = Logger.getLogger(ReceiveBody.class);

    /**
     * 当前页.
     */
    private int page;
    /**
     * 页大小.
     */
    private int size;
    /**
     * 查询条件map.
     */
    private Map<String, Object> queryParams = new HashMap<String, Object>();
    /**
     * 连接条件map（暂时没用到）.
     */
    private Map<String, Object> joinParams = new HashMap<String, Object>();
    /**
     * 排序条件map.
     */
    private Map<String, String> orderParams = new LinkedHashMap<String, String>();
    /**
     * bean查询条件.
     */
    private T condition;
    /**
     * 其他查询条件map.
     */
    private Map<String, Object> dataMap = new HashMap<String, Object>();
    /**
     * The Json data.
     */
    private String jsonData;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * clientId
     */
    private String clientId;
    /**
     * 换取的code
     */
    private String authCode;
    /**
     * token
     */
    private String tokenCode;
    /**
     * 重定向地址
     */
    private String url;


    /**
     * Gets login name.
     *
     * @return the login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets login name.
     *
     * @param loginName the login name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets auth code.
     *
     * @return the auth code
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Sets auth code.
     *
     * @param authCode the auth code
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * Gets token code.
     *
     * @return the token code
     */
    public String getTokenCode() {
        return tokenCode;
    }

    /**
     * Sets token code.
     *
     * @param tokenCode the token code
     */
    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 描述 Gets query params.
     *
     * @return the query params
     * @author Iverson Cai
     * @created 2017 /05/08 16:27:38
     */
    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    /**
     * 描述 Sets query params.
     *
     * @param queryParams the query params
     * @author Iverson Cai
     * @created 2017 /05/08 16:27:38
     */
    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    /**
     * 描述 Gets join params.
     *
     * @return the join params
     * @author Iverson Cai
     * @created 2017 /05/08 16:27:38
     */
    public Map<String, Object> getJoinParams() {
        return joinParams;
    }

    /**
     * 描述 Sets join params.
     *
     * @param joinParams the join params
     * @author Iverson Cai
     * @created 2017 /05/08 16:27:39
     */
    public void setJoinParams(Map<String, Object> joinParams) {
        this.joinParams = joinParams;
    }

    /**
     * 描述 Gets order params.
     *
     * @return the order params
     * @author Iverson Cai
     * @created 2017 /05/08 16:27:39
     */
    public Map<String, String> getOrderParams() {
        return orderParams;
    }

    /**
     * 描述 Sets order params.
     *
     * @param orderParams the order params
     * @author Iverson Cai
     * @created 2017 /05/08 16:27:39
     */
    public void setOrderParams(Map<String, String> orderParams) {
        this.orderParams = orderParams;
    }

    /**
     * 描述 Gets condition.
     *
     * @return the condition
     * @author Iverson Cai
     * @created 2017 /05/08 18:47:33
     */
    public T getCondition() {
        return condition;
    }

    /**
     * 描述 Sets condition.
     *
     * @param condition the condition
     * @author Iverson Cai
     * @created 2017 /05/08 18:47:33
     */
    public void setCondition(T condition) {
        this.condition = condition;
    }

    /**
     * 描述 Gets data map.
     *
     * @return the data map
     * @author Iverson Cai
     * @created 2017 /05/08 18:47:33
     */
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    /**
     * 描述 Sets data map.
     *
     * @param dataMap the data map
     * @author Iverson Cai
     * @created 2017 /05/08 18:47:34
     */
    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets json data.
     *
     * @return the json data
     */
    public String getJsonData() {
        return jsonData;
    }

    /**
     * Sets json data.
     *
     * @param jsonData the json data
     */
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
