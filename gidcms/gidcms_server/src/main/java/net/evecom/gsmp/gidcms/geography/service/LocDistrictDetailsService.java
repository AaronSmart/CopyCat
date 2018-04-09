/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service;

import net.evecom.gsmp.core.service.BaseService;
import net.evecom.gsmp.gidcms.geography.entity.LocDistrictDetails;

/**
 * 区划介绍服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:46
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocDistrictDetailsService extends BaseService<LocDistrictDetails, Long> {
    /**
     * 获取区划信息介绍.
     *
     * @param condition the condition
     * @return the loc districts detail
     * @author Nick Lv
     * @created 2017 /05/23 11:49:33
     */
    LocDistrictDetails getLocDistrictsDetail(LocDistrictDetails condition);
}
