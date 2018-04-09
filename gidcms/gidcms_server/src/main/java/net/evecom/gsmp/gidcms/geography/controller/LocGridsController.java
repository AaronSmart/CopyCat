/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.controller;

import net.evecom.gsmp.core.web.BaseController;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocGridTypes;
import net.evecom.gsmp.gidcms.geography.entity.LocGrids;
import net.evecom.gsmp.gidcms.geography.service.LocGridsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 网格信息控制层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:34:38
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/geography/grid")
public class LocGridsController extends BaseController<LocGrids,Long> {
    /**
     * 网格信息服务层
     */
    @Autowired
    LocGridsService locGridsService;

    /**
     * 新增 或 修改网格
     *
     * @param locGrids the loc grids
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/23 11:59:05 Add or update grid v return message.
     */
    @RequestMapping("/gridsUpdate")
    public VReturnMessage addOrUpdateGrid(@RequestBody LocGrids locGrids) {
        return locGridsService.addOrUpdateGird(locGrids);
    }

    /**
     * 删除网格信息
     *
     * @param ids the ids
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/23 11:59:05 Remove grid v return message.
     */
    @RequestMapping("/gridsRemove")
    public VReturnMessage removeGrid(@RequestParam("ids") String ids) {
        VReturnMessage vReturnMessage;
        if (!StringUtils.hasText(ids)) {
            return new VReturnMessage(false,VReturnMessage.WARN_CODE,"请选择要删除的地址");
        }
        return locGridsService.removeGrid(ids.split(","));
    }

    /**
     * 网格信息查询.
     *
     * @param body the body
     * @return the grid infos
     * @author Nick Lv
     * @created 2017 /05/23 11:45:00
     */
    @RequestMapping("/grids")
    public Map<String, Object> getGridInfos(@RequestBody SimplePageRequestBody<LocGrids> body) {
        return locGridsService.getLocGirds(body);
    }

    /**
     * 获取单个网格信息.
     *
     * @param gridId the grid id
     * @return the grid info
     * @author Nick Lv
     * @created 2017 /05/31 14:23:17
     */
    @RequestMapping("/gridInfo")
    public LocGrids getGridInfo(@RequestParam("id") String gridId) {
        // Map<Integer, Object> map = new HashMap<>();
        // map.put(1, geoService.getaGrid(gridId));
        return locGridsService.getaGrid(gridId);
    }

    /**
     * 获取所有的网格类型.
     *
     * @return the grid types
     * @author Nick Lv
     * @created 2017 /05/23 14:22:52
     */
    @RequestMapping("/gridTypes")
    public List<LocGridTypes> getGridTypes() {
        return locGridsService.getAllGridTypes();
    }

    /**
     * 根据层级获取网格.
     *
     * @param level the level
     * @return the grids by lvl
     * @author Nick Lv
     * @created 2017 /05/23 20:00:51
     */
    @RequestMapping("/gridLevel")
    public List<LocGrids> getGridsByLvl(@RequestParam("level") String level) {
        return locGridsService.getGridsByLvl(level);
    }
}
