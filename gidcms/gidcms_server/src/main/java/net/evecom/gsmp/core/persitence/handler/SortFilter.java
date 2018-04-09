/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;

/**
 * 排序工具类
 *
 * @author Nick Lv
 * @created 2017 /01/17 10:37:06
 */
public class SortFilter {

    /**
     * 字段名、域名
     */
    private String filedName;

    /**
     * 目录
     */
    private String direction;

    public SortFilter(String filedName, String direction) {
        this.filedName = filedName;
        this.direction = direction;
    }

    /**
     * 获取字段名
     *
     * @return the filed name
     * @author Nick Lv
     * @created 2017 /01/17 10:39:38
     */
    public String getFiledName() {
        return filedName;
    }

    /**
     * 获取字段名集合
     *
     * @param filedName the filed name
     * @author Nick Lv
     * @created 2017 /01/17 10:39:38
     */
    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    /**
     * 获取目录
     *
     * @return the direction
     * @author Nick Lv
     * @created 2017 /01/17 16:44:09
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 获取目录集合
     *
     * @param direction the direction
     * @author Nick Lv
     * @created 2017 /01/17 16:44:09
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
