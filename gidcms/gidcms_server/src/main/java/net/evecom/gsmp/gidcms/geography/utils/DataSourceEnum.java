/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.utils;

/**
 * 数据来源枚举类
 *
 * @author Nick Lv
 * @created 2017 /7/5 10:31
 */
public enum DataSourceEnum {
    /**
     * 实测.
     */
    ACTUAL_MEASUREMENT(0,"实测"),
    /**
     * 地形图
     */
    RELIEF_MAP(1,"地形图"),
    /**
     * 基础地理数据
     */
    BASIC_GEOGRAPHIC_DATA(2,"基础地理数据"),
    /**
     * 其它
     */
    OTHER(3,"其它");

    /**
     * 数据来源值
     */
    private Integer dataSourceValue;
    /**
     * 数据来源中文名
     */
    private String dataSourceCnName;

    /**
     * Gets data source cn name.
     *
     * @return the data source cn name
     */
    public String getDataSourceCnName() {
        return dataSourceCnName;
    }

    /**
     * Gets data source value.
     *
     * @return the data source value
     */
    public Integer getDataSourceValue() {
        return dataSourceValue;
    }

    DataSourceEnum(Integer dataSourceValue, String dataSourceCnName) {
        this.dataSourceValue = dataSourceValue;
        this.dataSourceCnName = dataSourceCnName;
    }

    public static DataSourceEnum getCnNameByValue(Integer dataSourceValue) {
        for(DataSourceEnum dse : DataSourceEnum.values()) {
            if(dse.getDataSourceValue().equals(dataSourceValue)) {
                return dse;
            }
        }
        return null;
    }
}
