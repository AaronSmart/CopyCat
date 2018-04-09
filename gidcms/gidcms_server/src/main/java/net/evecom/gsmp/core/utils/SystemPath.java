/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 The type System path.
 *
 * @author Nick Lv
 * @version v 1.0
 * @created 2017 /03/01 10:46:28
 * @date Dec 14, 2008
 * @description 得到当前应用的系统路径
 */
public class SystemPath {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemPath.class);

    /**
     * 描述 Gets sys path.
     *
     * @return the sys path
     * @author Nick Lv
     * @created 2017 /03/01 10:46:28
     */
    public static String getSysPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
        String separator = System.getProperty("file.separator");
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    /**
     * 描述 Gets class path.
     *
     * @return the class path
     * @author Nick Lv
     * @created 2017 /03/01 10:46:29
     */
    public static String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        String temp = path.replaceFirst("file:/", "");
        String separator = System.getProperty("file.separator");
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    /**
     * 描述 Gets systemp path.
     *
     * @return the systemp path
     * @author Nick Lv
     * @created 2017 /03/01 10:46:29
     */
    public static String getSystempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 描述 Gets separator.
     *
     * @return the separator
     * @author Nick Lv
     * @created 2017 /03/01 10:46:29
     */
    public static String getSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     * 描述 The entry point of application.
     *
     * @param args the input arguments
     * @author Nick Lv
     * @created 2017 /03/01 10:46:29
     */
    public static void main(String[] args) {
//        LOGGER.info(getSysPath());
//        LOGGER.info(System.getProperty("java.io.tmpdir"));
//        LOGGER.info(getSeparator());
//        LOGGER.info(getClassPath());
    }
}
