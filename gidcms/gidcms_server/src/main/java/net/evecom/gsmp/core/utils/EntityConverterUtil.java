/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.*;

/**
 * 转换对象
 *
 * @author Nick Lv
 * @created 2017 /04/24 19:36:56
 */
public class EntityConverterUtil {
    public static <T> List<T> transform(List<Map<String, Object>> list, Class<T> entityClass)
            throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        List<T> resultList = new LinkedList<>();
        PropertyDescriptor propertyDescriptor = null;
        String propertyName = null;
        Object value = null;
        for (Map<String, Object> map : list) {
            Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
            T entity = entityClass.newInstance();
            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();
                if (null == entry) {
                    continue;
                }
                propertyName = CommUtil.lineToHump(entry.getKey());
                propertyDescriptor = BeanUtils.getPropertyDescriptor(entityClass, propertyName);
                value = transformType(propertyDescriptor, entry.getValue());
                Reflections.invokeSetter(entity, propertyName, value);
            }
            resultList.add(entity);
        }
        return resultList;
    }

    /**
     * 根据实体类字段类型转换数据类型
     *
     * @param propertyDescriptor
     * @param value
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static Object transformType(PropertyDescriptor propertyDescriptor, Object value)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object result = null;
        if (null == value) {
            return null;
        }
        Class<?> typeClass = propertyDescriptor.getPropertyType();
        if (String.class.getName().equals(typeClass.getName())) {
            result = value.toString();
        } else if (Timestamp.class.getName().equals(typeClass.getName())) {
            result = value;
        } else if (BigDecimal.class.getName().equals(typeClass.getName())) {
            result = value;
        } else if (Long.class.getName().equals(typeClass.getName())) {
            result = Long.valueOf(String.valueOf(value));
        } else if (Integer.class.getName().equals(typeClass.getName())) {
            result = Integer.valueOf(String.valueOf(value));
        } else if (boolean.class.getName().equals(typeClass.getName())) {
            result = Boolean.parseBoolean(value.toString());
        } else if (Clob.class.getName().equals(typeClass.getName())) {
            result = value;
        } else if (Date.class.getName().equals(typeClass.getName())) {
            result = value;
        } else if (int.class.getName().equals(typeClass.getName())) {
            result = value;
        } else if (Double.class.getName().equals(typeClass.getName())) {
            result = new Double(((BigDecimal) (value)).doubleValue());
        } else {
            Method valueOfMethod = typeClass.getMethod("valueOf", String.class);
            result = valueOfMethod.invoke(typeClass, (String) value);
        }
        return result;
    }

}
