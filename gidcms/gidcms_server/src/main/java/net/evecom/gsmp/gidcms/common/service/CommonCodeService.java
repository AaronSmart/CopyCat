/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.service;

import net.evecom.gsmp.gidcms.common.vo.VCommCode;

import java.util.List;

/**
 * 获取码表值
 *
 * @author Nick Lv
 * @created 2017 /7/6 20:35
 */
public interface CommonCodeService {

    /**
     * 根据表名获取code
     *
     * @author Nick Lv
     * @param tableName the table name
     * @return the codes by table name
     */
    List<VCommCode>  getCodesByTableName(String tableName);
}
