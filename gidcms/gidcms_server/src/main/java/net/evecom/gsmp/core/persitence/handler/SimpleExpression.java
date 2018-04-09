/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;

import net.evecom.gsmp.core.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.Date;


/**
 * 描述
 *
 * @author Nick Lv
 * @created 2017 /01/11 10:48
 */
public class SimpleExpression implements Criterion {
    /**
     * The Field name.属性名
     */
    private String fieldName;
    /**
     * The Value.对应值
     */
    private Object value;
    /**
     * The Operator.计算符
     */
    private Operator operator;
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExpression.class);

    protected SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * 描述 Gets field name.
     *
     * @return the field name
     * @author Nick Lv
     * @created 2017 /01/11 10:49:58
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 描述 Gets value.
     *
     * @return the value
     * @author Nick Lv
     * @created 2017 /01/11 10:49:58
     */
    public Object getValue() {
        return value;
    }

    /**
     * 描述 Gets operator.
     *
     * @return the operator
     * @author Nick Lv
     * @created 2017 /01/11 10:49:58
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * 描述 create.
     *
     * @param root
     *            the root
     * @param query
     *            the query
     * @param builder
     *            the builder
     * @return the predicate
     * @author Nick Lv
     * @created 2017 /01/11 10:49:59 To predicate predicate.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path expression = null;
        if (fieldName.contains(".")) {
            String[] names = StringUtils.split(fieldName, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(fieldName);
        }

        if (expression != null) {
            // 时间类型转换
            Class typeClazz = expression.getJavaType();
            if (typeClazz == Date.class && !(value instanceof Date)) {
                if (value instanceof Long) {
                    value = new Date((Long) value);
                } else if (value instanceof String) {
                    // yyyy-MM-dd HH:mm:ss
                    if (DateUtil.isLegalDateTimeStr(String.valueOf(value))) {
                        value = DateUtil.strConvertStrongDateTime(String.valueOf(value));
                    }
                    // yyyy-MM-dd
                    else if (DateUtil.isLegalDateStr(String.valueOf(value))) {
                        value = DateUtil.strConvertStrongDate(String.valueOf(value));
                        // 如果是小于或小于的等于，时间则为当天的23:59:59
                        if (value != null && (operator == Operator.LT || operator == Operator.LTE )) {
                            value = new Date(((Date) value).getTime() + 24 * 60 * 60 * 1000 - 1000);
                        }
                    } else {
                        value = null;
                    }
                } else {
                    value = null;
                }
            }

        }

        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                return builder.like((Expression<String>) expression, "%" + value + "%");
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            default:
                return null;
        }
    }
}
