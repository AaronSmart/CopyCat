/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service;

import net.evecom.gsmp.core.service.BaseService;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocGridTypes;
import net.evecom.gsmp.gidcms.geography.entity.LocGrids;

import java.util.List;
import java.util.Map;

/**
 * 网格信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:46
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocGridsService extends BaseService<LocGrids,Long> {
    /**
     * 获取网格列表.
     *
     * @param body the body
     * @return the loc girds
     * @author Nick Lv
     * @created 2017 /05/23 11:49:33
     */
    Map<String,Object> getLocGirds(SimplePageRequestBody<LocGrids> body);

    /**
     * 修改网格信息.
     *
     * @param locGrids the loc grids
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/23 11:49:33 Add or update gird v return message.
     */
    VReturnMessage addOrUpdateGird(LocGrids locGrids);

    /**
     * 根据ID删除网格信息
     *
     * @param idArray the id array
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/23 11:55:27 Remove grid v return message.
     */
    VReturnMessage removeGrid(String[] idArray);

    /**
     * 获取所有网格类型.
     *
     * @return the all grid types
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53
     */
    List<LocGridTypes> getAllGridTypes();

    /**
     * 根据层级获取网格.
     *
     * @param level the level
     * @return the grids by lvl
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53
     */
    List<LocGrids> getGridsByLvl(String level);


    /**
     * 根据网格id寻找网格信息.
     *
     * @param gridId the grid id
     * @return the grid
     * @author Nick Lv
     * @created 2017 /05/31 14:21:57
     */
    LocGrids getaGrid(String gridId);
}
