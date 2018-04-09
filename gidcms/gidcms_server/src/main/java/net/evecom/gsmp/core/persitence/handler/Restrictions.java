/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;

import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 描述 条件构造器 用于创建条件表达式
 *
 * @author Nick Lv
 * @created 2017 /01/11 11:02
 */
public class Restrictions {

    /**
     * 等于
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return simple expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:45 Eq simple expression.
     */
    public static SimpleExpression eq(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value))
            return null;
        return new SimpleExpression(fieldName, value, Operator.EQ);
    }

    /**
     * 不等于
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return simple expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:45 Ne simple expression.
     */
    public static SimpleExpression ne(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value))
            return null;
        return new SimpleExpression(fieldName, value, Operator.NE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return simple expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:45 Like simple expression.
     */
    public static SimpleExpression like(String fieldName, String value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value))
            return null;
        return new SimpleExpression(fieldName, value, Operator.LIKE);
    }

    /**
     * 大于
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return simple expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:46 Gt simple expression.
     */
    public static SimpleExpression gt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value))
            return null;
        return new SimpleExpression(fieldName, value, Operator.GT);
    }

    /**
     * 小于
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return simple expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:46 Lt simple expression.
     */
    public static SimpleExpression lt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value))
            return null;
        return new SimpleExpression(fieldName, value, Operator.LT);
    }

    /**
     * 大于等于
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return simple expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:46 Gte simple expression.
     */
    public static SimpleExpression gte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value))
            return null;
        return new SimpleExpression(fieldName, value, Operator.GTE);
    }

    /**
     * 小于等于
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return simple expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:46 Lte simple expression.
     */
    public static SimpleExpression lte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value))
            return null;
        return new SimpleExpression(fieldName, value, Operator.LTE);
    }

    /**
     * 并且
     *
     * @param criterions
     *            the criterions
     * @return logical expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:46 And logical expression.
     */
    public static LogicalExpression and(Criterion... criterions) {
        return new LogicalExpression(criterions, Operator.AND);
    }

    /**
     * 或者
     *
     * @param criterions
     *            the criterions
     * @return logical expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:46 Or logical expression.
     */
    public static LogicalExpression or(Criterion... criterions) {
        return new LogicalExpression(criterions, Operator.OR);
    }

    /**
     * 包含于
     *
     * @param fieldName
     *            the field name
     * @param value
     *            the value
     * @param ignoreNull
     *            the ignore null
     * @return logical expression
     * @author Nick Lv
     * @created 2017 /01/11 11:03:47 In logical expression.
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression(fieldName, obj, Operator.EQ);
            i++;
        }
        return new LogicalExpression(ses, Operator.OR);
    }

    public static Criterion allOperator(String fileName, Operator operator, Object value) {

        return null;
    }
}
