/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.utils;

import net.evecom.gsmp.core.utils.IStringUtils;

/**
 * 描述 枚举类 综合查询.
 *
 * @author Submarine Lin
 * @created 2017 /05/19 19:43:02
 */
public enum IntegratedEnum {
    /**
     * 自然人
     */
    POPULATION("自然人", "getPopulations"),
    /**
     * 部件
     */
    CIOBJ("部件", "getCiObjs"),
    /**
     * 法人
     */
    LEGAL("法人", "getOrgRegCertificates");
    /**
     * 中文名
     */
    private String valueCN;
    /**
     * 方法名
     */
    private String functionName;

    /**
     * 描述 Gets value cn.
     *
     * @return the value cn
     * @author Submarine Lin
     * @created 2017 /05/19 19:38:44
     */
    public String getValueCN() {
        return valueCN;
    }

    /**
     * 描述 Gets function name.
     *
     * @return the function name
     * @author Submarine Lin
     * @created 2017 /05/19 19:38:44
     */
    public String getFunctionName() {
        return functionName;
    }

    IntegratedEnum(String valueCN, String functionName) {
        this.valueCN = valueCN;
        this.functionName = functionName;
    }

    /**
     * 根据中文名取得枚举类
     * 
     * @return
     * @throws Exception
     */
    public static IntegratedEnum getEnumFromValueCN(String valueCN) {
        if (valueCN != null) {
            for (IntegratedEnum item : IntegratedEnum.values()) {
                if (IStringUtils.equals(valueCN, item.getValueCN())) {
                    return item;
                }
            }
        }
        return null;
    }
}
