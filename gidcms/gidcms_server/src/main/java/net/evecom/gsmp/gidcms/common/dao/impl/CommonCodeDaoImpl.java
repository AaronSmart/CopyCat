/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.dao.impl;

import net.evecom.gsmp.core.persitence.BaseJPAImpl;
import net.evecom.gsmp.gidcms.common.dao.CommonCodeDao;
import net.evecom.gsmp.gidcms.common.vo.VCommCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 码表持久层实现类
 *
 * @author Nick Lv
 * @created 2017/7/6 20:39
 */
@Repository
public class CommonCodeDaoImpl extends BaseJPAImpl<VCommCode, Long> implements CommonCodeDao {
    @Override
    public List<VCommCode> getCodesByTableName(String tableName) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("select t.ID as ID, t.LABEL as LABEL,t.NAME as NAME, t.CODE_VALUE as CODE_VALUE,")
                .append("t.CODE_TYPE_ID as CODE_TYPE_ID, t.PARENT_ID as PARENT_ID").append(" from gsmp.CODE_")
                .append(tableName).append(" t where t.is_valid=1 order by view_order asc");

        return super.queryEntityBySql(VCommCode.class, sBuilder.toString(), null);
    }
}
