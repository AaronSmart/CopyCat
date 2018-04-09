/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.service.impl;

import net.evecom.gsmp.gidcms.common.dao.CommonCodeDao;
import net.evecom.gsmp.gidcms.common.service.CommonCodeService;
import net.evecom.gsmp.gidcms.common.vo.VCommCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 码表业务层实现类
 *
 * @author Nick Lv
 * @created 2017/7/6 20:36
 */
@Service
public class CommonCodeServiceImpl implements CommonCodeService {
    /**
     * 码表dao层
     */
    @Autowired
    CommonCodeDao commonCodeDao;

    @Override
    public List<VCommCode> getCodesByTableName(String tableName) {

        return commonCodeDao.getCodesByTableName(tableName);
    }
}
