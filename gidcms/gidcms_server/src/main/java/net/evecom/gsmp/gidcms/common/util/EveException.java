/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import com.alibaba.fastjson.JSON;
import net.evecom.gsmp.core.utils.VReturnMessage;

/**
 * 自定义异常
 * @author Iverson Cai     
 * @created 2017 /08/15 09:41:30
 */
public class EveException extends Exception {
    /**
     * 错误信息
     */
    private String message;
    /**
     * 错误代码
     */
    private String errorCode;

    public EveException() {
        this.errorCode = "9999";
        this.message = "失败,系统内部异常";
    }

    public EveException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorCodeAndMessage(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public VReturnMessage getReturnMessage() {
        VReturnMessage vReturnMessage = new VReturnMessage();
        vReturnMessage.setResult(false);
        vReturnMessage.setMessage(this.getMessage());
        return vReturnMessage;
    }

    public String getJsonMessage() {
        VReturnMessage vReturnMessage = this.getReturnMessage();
        vReturnMessage.setData((Object)null);
        return JSON.toJSONString(vReturnMessage);
    }
}
