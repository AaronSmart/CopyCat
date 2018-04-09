/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description
 *
 * @version --添加版本信息
 * @author Nick Lv
 * @since Version 1.0
 * @see --添加类中引用的相关类和接口
 */
public class HttpUtil {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 从流中读取post过来的字符串
     * 描述
     *
     * @param request the request
     * @return string
     * @author Nick Lv
     * @created 2016年5月3日 上午10:14:37
     */
    public static String readFromStream(HttpServletRequest request) {
        // 读取请求数据
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String lines = null;
            StringBuffer sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            return sb.toString();
        } catch (Exception ex) {
            LOGGER.error("从流中读取post过来的字符串出现异常", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("从流中读取post过来的字符串,关闭 BufferedReader 出现异常", e);
                }
            }
        }
        return null;
    }

}
