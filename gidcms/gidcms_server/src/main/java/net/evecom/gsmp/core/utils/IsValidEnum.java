/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

/**
 * <P><B>Description: </B> 是否有效的枚举  </P>
 * Revision Trail: (Date/Author/Description)
 * 2015-1-26 Nelson Ni CREATE
 *
 * @author Nick Lv
 * @version 1.0
 * @created 2017 /03/01 10:43:27
 */
public enum IsValidEnum {

    /**
     * 有效
     */
    VALID("1"),
    /**
     * 无效
     */
    INVALID("0");

    /**
     * 值
     */
    private String value;

    private IsValidEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
