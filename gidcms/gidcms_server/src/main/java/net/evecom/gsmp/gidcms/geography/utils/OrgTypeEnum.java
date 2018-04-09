/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.utils;

/**
 * 区划类型
 *
 * @author Nick Lv
 * @created 2017 /7/5 10:39
 */
public enum OrgTypeEnum {

    /**
     * 国家行政区划
     */
    NATIONAL_ADMINISTRATIVE_DIVISIONS(0,"国家行政区划"),
    /**
     * 群众自治组织区划
     */
    MASS_AUTONOMOUS_ORG(1,"群众自治组织区划"),
    /**
     * 治安民警片区
     */
    POLICE_AREA(2,"治安民警片区");
    /**
     * 区划值
     */
    private Integer OrgTypeValue;
    /**
     * 区划中文名字
     */
    private String OrgTypeCnName;

    /**
     * Gets org type value.
     *
     * @return the org type value
     */
    public Integer getOrgTypeValue() {
        return OrgTypeValue;
    }

    /**
     * Gets org type cn name.
     *
     * @return the org type cn name
     */
    public String getOrgTypeCnName() {
        return OrgTypeCnName;
    }

    /**
     *
     * @param orgTypeValue
     * @param orgTypeCnName
     */
    OrgTypeEnum(Integer orgTypeValue, String orgTypeCnName) {
        OrgTypeValue = orgTypeValue;
        OrgTypeCnName = orgTypeCnName;
    }

    /**
     * 根据值获取区划类型
     *
     * @param orgTypeValue the org type value
     * @return the cn name by value
     */
    public static OrgTypeEnum getCnNameByValue(Integer orgTypeValue) {
        for(OrgTypeEnum ote : OrgTypeEnum.values()) {
            if(ote.getOrgTypeValue().equals(orgTypeValue)) {
                return ote;
            }
        }
        return null;
    }
}
