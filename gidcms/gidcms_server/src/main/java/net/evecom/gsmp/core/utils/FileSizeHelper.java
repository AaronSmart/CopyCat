/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import java.text.DecimalFormat;

/**
 * <p>
 * 文件大小工具类.
 * </p>
 *
 * @author Nick Lv
 * @version 1.0 2013-01-02 12:50 PM
 * @created 2017 /03/01 10:42:34
 * @since JDK 1.5
 */
public class FileSizeHelper {
    /**
     * 常量 ONE_KB.
     */
    public static final long ONE_KB = 1024;
    /**
     * 常量 ONE_KB
     */
    public static final long ONE_MB = ONE_KB * 1024;
    /**
     * 常量 ONE_KB
     */
    public static final long ONE_GB = ONE_MB * 1024;
    /**
     * 常量 ONE_KB
     */
    public static final long ONE_TB = ONE_GB * (long) 1024;
    /**
     * 常量 ONE_KB
     */
    public static final long ONE_PB = ONE_TB * (long) 1024;

    /**
     * 描述 Gets human readable file size.
     *
     * @param fileSize the file size
     * @return the human readable file size
     * @author Nick Lv
     * @created 2017 /03/01 10:42:34
     */
    public static String getHumanReadableFileSize(Long fileSize) {
        if (fileSize == null)
            return null;
        return getHumanReadableFileSize(fileSize.longValue());
    }

    /**
     * 描述 Gets human readable file size.
     *
     * @param fileSize the file size
     * @return the human readable file size
     * @author Nick Lv
     * @created 2017 /03/01 10:42:35
     */
    public static String getHumanReadableFileSize(long fileSize) {
        if (fileSize < 0) {
            return String.valueOf(fileSize);
        }
        String result = getHumanReadableFileSize(fileSize, ONE_PB, "PB");
        if (result != null) {
            return result;
        }

        result = getHumanReadableFileSize(fileSize, ONE_TB, "TB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_GB, "GB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_MB, "MB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_KB, "KB");
        if (result != null) {
            return result;
        }
        return String.valueOf(fileSize) + "B";
    }

    /**
     * 描述 Gets human readable file size.
     *
     * @param fileSize the file size
     * @param unit     the unit
     * @param unitName the unit name
     * @return the human readable file size
     * @author Nick Lv
     * @created 2017 /03/01 10:42:35
     */
    private static String getHumanReadableFileSize(long fileSize, long unit, String unitName) {
        if (fileSize == 0)
            return "0";

        if (fileSize / unit >= 1) {
            double value = fileSize / (double) unit;
            DecimalFormat df = new DecimalFormat("######.##" + unitName);
            return df.format(value);
        }
        return null;
    }
}
