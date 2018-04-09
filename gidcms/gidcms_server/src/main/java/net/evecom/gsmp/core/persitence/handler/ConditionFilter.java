/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;


/**
 * 描述 The type Filter util.
 *
 * @author Nick Lv
 * @created 2017 /01/17 11:03:58
 */
public class ConditionFilter {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 值
     */
    private Object value;
    /**
     * 操作
     */
    private String operator;

    public ConditionFilter(String fieldName, Object value, String operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * 描述 Gets field name.
     *
     * @return the field name
     * @author Nick Lv
     * @created 2017 /01/17 10:42:49
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 描述 Sets field name.
     *
     * @param fieldName the field name
     * @author Nick Lv
     * @created 2017 /01/17 10:42:49
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 描述 Gets value.
     *
     * @return the value
     * @author Nick Lv
     * @created 2017 /01/17 10:42:49
     */
    public Object getValue() {
        return value;
    }

    /**
     * 描述 Sets value.
     *
     * @param value the value
     * @author Nick Lv
     * @created 2017 /01/17 10:42:49
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 描述 Gets operator.
     *
     * @return the operator
     * @author Nick Lv
     * @created 2017 /01/17 10:42:49
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 描述 Sets operator.
     *
     * @param operator the operator
     * @author Nick Lv
     * @created 2017 /01/17 10:42:49
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
}
