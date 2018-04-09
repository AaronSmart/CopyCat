/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.dao;

import net.evecom.gsmp.core.persitence.BaseDao;
import net.evecom.gsmp.core.persitence.BaseJPA;
import net.evecom.gsmp.gidcms.common.vo.VCommCode;

import java.util.List;

/**
 * 码表持久层
 *
 * @author Nick Lv
 * @created 2017 /7/6 20:39
 */
public interface CommonCodeDao extends BaseDao<VCommCode, Long> {

    /**
     * 根据表名获取码表值
     *
     * @param tableName the table name
     * @return the codes by table name
     * @author Nick Lv
     * @created 2017 /07/10 17:41:16
     */
    List<VCommCode> getCodesByTableName(String tableName);
}
