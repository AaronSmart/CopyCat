/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;

import java.util.Locale;

/**
 *
 * @author Nick Lv
 * @created 2016 /12/15 15:00:55
 */
public enum Operator {
    /**
     * Eq operator.等于
     */
    EQ,
    /**
     * Ne operator.不等于
     */
    NE,
    /**
     * Like operator.模糊查询
     */
    LIKE,
    /**
     * Gt operator.大于
     */
    GT,
    /**
     * Lt operator.小于
     */
    LT,
    /**
     * Gte operator.大于等于
     */
    GTE,
    /**
     * Lte operator.小于等于
     */
    LTE,
    /**
     * And operator.并且
     */
    AND,
    /**
     * Or operator.或
     */
    OR;

    /**
     * 描述 create. Returns the Operator enum for the given String value.
     *
     * @param value the value
     * @return the operator
     * @author Nick Lv
     * @created 2017 /01/11 01:21:21 From string operator.
     */
    public static Operator fromString(String value) {
        try {
            return Operator.valueOf(value.toUpperCase(Locale.US));
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format(
                    "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).",
                    value), e);
        }
    }

    /**
     * 描述 create. Returns the Operator enum for the given String or null if it
     * cannot be parsed into an enum value.
     *
     * @param value the value
     * @return the operator
     * @author Nick Lv
     * @created 2017 /01/11 01:21:21 From string or null operator.
     */
    public static Operator fromStringOrNull(String value) {
        try {
            return fromString(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
