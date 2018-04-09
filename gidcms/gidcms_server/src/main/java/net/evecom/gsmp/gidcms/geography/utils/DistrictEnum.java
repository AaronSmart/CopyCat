/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.utils;

/**
 * 区划编码枚举类
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /06/06 16:46:33
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public enum DistrictEnum {
    /**
     * 国家
     */
    COUNTRY(1,12),
    /**
     * 省
     */
    PROVINCE(2,2),
    /**
     * 市
     */
    CITY(3,4),
    /**
     * 县
     */
    COUNTY(4,6),
    /**
     * 街道
     */
    STREET(5,9),
    /**
     * 社区
     */
    COMMUNITY(6,12);
    /**
     * 层级
     */
    private int level;
    /**
     * 截取长度
     */
    private int subStringLength;

    /**
     * 构造函数
     * @param level
     * @param subStringLength
     */
    DistrictEnum(int level, int subStringLength) {
        this.level = level;
        this.subStringLength = subStringLength;
    }

    /**
     * get level
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * get subString length
     * @return
     */
    public int getSubStringLength() {
        return subStringLength;
    }

    /**
     * 获取截取长度
     * @param level
     * @return
     */
    public static DistrictEnum getSubStringLengthByLvl(int level) {
        for(DistrictEnum item : DistrictEnum.values()) {
            if(item.getLevel() == level) {
                return item;
            }
        }
        return null;
    }
}
