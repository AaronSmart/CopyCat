/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service;

import net.evecom.gsmp.core.service.BaseService;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocDistricts;
import net.evecom.gsmp.gidcms.geography.vo.VDistrictTree;

import java.util.List;
import java.util.Map;

/**
 * 区划信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:46
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocDistrictsService extends BaseService<LocDistricts,Long> {
    /**
     * 获取区划信息.
     *
     * @param body the body
     * @return the loc districts
     * @author Nick Lv
     * @created 2017 /05/23 10:35:46
     */
    Map<String,Object> getLocDistricts(SimplePageRequestBody<LocDistricts> body);
    /**
     * 获取所有的行政区划.
     *
     * @param level the level
     * @return the all districts
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53
     */
    List<LocDistricts> getAllDistricts(String level);



    /**
     * 描述 根据区划id寻找区划信息.
     *
     * @param districtId the district id
     * @return loc district
     * @author Nick Lv
     * @created 2017 /05/31 14:19:05
     */
    LocDistricts getaLocDistrict(String districtId);

    /**
     * 根据层级获取区划下拉树
     *
     * @param parentId the parent id
     * @return the districts by level
     * @author Nick Lv
     * @created 2017 /06/01 17:31:01
     */
    List<VDistrictTree> getDistrictsByParentId(String parentId);

    /**
     * 县以下的区划才能被选.
     *
     * @param parentId the parent id
     * @return the districts for addr by parent id
     * @author Nick Lv
     * @created 2017 /06/03 10:36:44
     */
    List<VDistrictTree> getDistrictsForAddrByParentId(String parentId);

    /**
     * 网格管理获取区划信息.
     *
     * @param parentId the parent id
     * @return the districts for grid by parent id
     * @author Nick Lv
     * @created 2017 /06/06 08:43:15
     */
    List<VDistrictTree> getDistrictsForGridByParentId(String parentId);

    /**
     * 描述 Gets districts by town.
     *
     * @param districtCode the district code
     * @return the districts by town
     * @author Nick Lv
     * @created 2017 /06/17 12:05:48
     */
    List<LocDistricts> getDistrictsByTown(String districtCode);
}
