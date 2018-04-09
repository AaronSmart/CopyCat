/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对象操作工具类, 继承org.apache.commons.lang3.ObjectUtils类
 *
 * @author Nick Lv
 * @version 2014 -6-29
 * @created 2017 /03/01 10:43:55
 */
public class IObjectUtils extends org.apache.commons.lang3.ObjectUtils {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IObjectUtils.class);

    /**
     * 注解到对象复制，只复制能匹配上的方法。
     *
     * @param annotation the annotation
     * @param object     the object
     * @author Nick Lv
     * @created 2017 /03/01 10:43:55 Annotation to object.
     */
    public static void annotationToObject(Object annotation, Object object) {
        if (annotation != null && object != null) {
            Class<?> annotationClass = annotation.getClass();
            Class<?> objectClass = object.getClass();
            for (Method m : objectClass.getMethods()) {
                if (StringUtils.startsWith(m.getName(), "set")) {

                    String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
                    Object obj = null;
                    try {
                        obj = annotationClass.getMethod(s).invoke(annotation);
                    } catch (IllegalAccessException e) {
                        LOGGER.error("非法访问异常", e);
                    } catch (InvocationTargetException e) {
                        LOGGER.error("调用目标异常", e);
                    } catch (NoSuchMethodException e) {
                        LOGGER.error("没有这样的方法异常", e);
                    }
                    if (obj != null && !"".equals(obj.toString())) {
                        try {
                            object = objectClass.newInstance();
                        } catch (InstantiationException e) {
                            LOGGER.error("获取实例异常", e);
                        } catch (IllegalAccessException e) {
                            LOGGER.error("非法访问异常", e);
                        }
                        try {
                            m.invoke(object, obj);
                        } catch (IllegalAccessException e) {
                            LOGGER.error("非法访问异常", e);
                        } catch (InvocationTargetException e) {
                            LOGGER.error("调用目标异常", e);
                        }
                    }

                }
            }
        }
    }

    /**
     * 序列化对象
     *
     * @param object the object
     * @return byte [ ]
     * @author Nick Lv
     * @created 2017 /03/01 10:43:55 Serialize byte [ ].
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            if (object != null) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        } catch (Exception e) {
            LOGGER.error("字节数组输出流异常", e);
        }
        return null;
    }

    /**
     * 反序列化对象
     *
     * @param bytes the bytes
     * @return object
     * @author Nick Lv
     * @created 2017 /03/01 10:43:55 Unserialize object.
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            if (bytes != null && bytes.length > 0) {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        } catch (Exception e) {
            LOGGER.error("字节数组输入流异常", e);
        }
        return null;
    }
}
