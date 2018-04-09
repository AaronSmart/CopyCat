/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;


import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 启动监听器
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/14 20:16:31
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupListener.class);

    /**
     * 基础表相关操作
     * @author Nick Lv
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    }

    /**
     * 描述 create.
     *
     * @param object the object
     * @return the byte [ ]
     * @author Nick Lv
     * @created 2017 /05/14 20:16:32 Serialize byte [ ].
     */
    private static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                LOGGER.error("关闭流异常", e);
            }

        }
        return null;
    }
}
