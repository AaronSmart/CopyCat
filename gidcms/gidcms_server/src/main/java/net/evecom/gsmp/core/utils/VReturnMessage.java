/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <B>描述:</B> 后台执行结果返回类
 * </P>
 *
 * @param <T> the type parameter
 * @author Nick Lv
 * @version 1.0
 * @created 2017 /03/01 10:47:26
 */
@Component
public class VReturnMessage<T> {

    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 失败状态码
     */
    public static final int ERROR_CODE = 500;
    /**
     * 警告状态码
     */
    public static final int WARN_CODE = 600;

    /**
     * 执行成功与否
     */
    private boolean result;

    /**
     * The Code.
     */
    private int code = this.SUCCESS_CODE;

    /**
     * 执行结果信息。如果执行成功，该值可为空
     */
    private String message;

    /**
     * 返回给前台的数据对象 简单对象
     */
    private Object data;

    /**
     * 返回给前台的数据对象
     */
    private T dataT;

    /**
     * 宽集的数据返回集合 复杂对象信息
     */
    private Map<String, Object> dataMap;

    /**
     * Instantiates a new V return message.
     *
     * @param result
     *            the result
     */
    public VReturnMessage(boolean result) {
        this.result = result;
    }

    /**
     * Instantiates a new V return message.
     *
     * @param result
     *            the result
     */
    public VReturnMessage(boolean result, int code, String message) {
        this.result = result;
        this.message = message;
        this.code = code;
    }

    /**
     * Instantiates a new V return message.
     */
    public VReturnMessage() {
    }

    /**
     * 描述 create.
     *
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27 Is result boolean.
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 描述 Sets result.
     *
     * @param result
     *            the result
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code
     *            the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 描述 Gets message.
     *
     * @return the message
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public String getMessage() {
        return message;
    }

    /**
     * 描述 Sets message.
     *
     * @param message
     *            the message
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 描述 Gets data.
     *
     * @return the data
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public Object getData() {
        return data;
    }

    /**
     * 描述 Sets data.
     *
     * @param data
     *            the data
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 描述 Gets data.
     *
     * @return the data
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public T getDataT() {
        return dataT;
    }

    /**
     * 描述 Sets data.
     *
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public void setDataT(T dataT) {
        this.dataT = dataT;
    }

    /**
     * 描述 Gets data map.
     *
     * @return the data map
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public Map<String, Object> getDataMap() {
        if (this.dataMap == null) {
            this.dataMap = new HashMap<String, Object>();
        }
        return this.dataMap;
    }

    /**
     * 描述 Sets data map.
     *
     * @param dataMap
     *            the data map
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    /**
     * 设置result和message
     *
     * @param errorCode
     *            the result
     * @param _message
     *            the message
     * @author Nick Lv
     * @created 2017 /03/01 10:47:27
     */
    public void setResultAndMsg(String errorCode, String _message) {
        this.result = false;
        this.code = Integer.parseInt(errorCode);
        this.message = _message;
    }

    @Override
    public String toString() {
        return "VReturnMessage [result=" + result + ", message=" + message + ", data=" + data + ", dataMap=" + dataMap
                + "]";
    }

}
