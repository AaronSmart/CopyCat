/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service;

import net.evecom.gsmp.core.service.BaseService;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocStreets;

import java.util.List;
import java.util.Map;

/**
 * 街巷信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:46
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocStreetsService extends BaseService<LocStreets,Long> {
    /**
     * 根据条件获取街巷.
     *
     * @param body the body
     * @return the streets
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53
     */
    Map<String,Object> getStreets(SimplePageRequestBody<LocStreets> body);


    /**
     * 新增修改街巷.
     *
     * @param locStreets the loc streets
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53 Add or update street v return message.
     */
    VReturnMessage addOrUpdateStreet(LocStreets locStreets);


    /**
     * 新增街巷.
     *
     * @param locStreets the loc streets
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53 Add or update street v return message.
     */
    VReturnMessage<LocStreets> addStreet(LocStreets locStreets);

    /**
     * 删除街巷.
     *
     * @param idArray the id array
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53 Remove streets v return message.
     */
    VReturnMessage removeStreets(String[] idArray);

    /**
     * 获取所有的.
     *
     * @param districtCode the district code
     * @param level        the level
     * @return the all streets
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53
     */
    List<LocStreets> getAllStreetsByDistrictCode(String districtCode, String level);

    /**
     * 获取道路详情.
     *
     * @param streetId the street id
     * @return the street
     * @author Nick Lv
     * @created 2017 /05/31 14:40:19
     */
    LocStreets getaStreet(String streetId);
}
