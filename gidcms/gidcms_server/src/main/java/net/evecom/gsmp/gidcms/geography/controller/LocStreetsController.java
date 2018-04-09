/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.controller;

import net.evecom.gsmp.core.web.BaseController;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocStreets;
import net.evecom.gsmp.gidcms.geography.service.LocStreetsService;
import net.evecom.gsmp.core.annotation.TableInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 街巷信息控制层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:34:38
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/geography/street")
public class LocStreetsController extends BaseController<LocStreets, Long> {
    /**
     * 街巷信息服务层
     */
    @Autowired
    LocStreetsService locStreetsService;

    /**
     * 修改街道.
     *
     * @param locStreets the loc streets
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:52:04 Add or update street v return message.
     */
    @RequestMapping("/streetUpdate")
    public VReturnMessage addOrUpdateStreet(@RequestBody LocStreets locStreets) {
        return locStreetsService.addOrUpdateStreet(locStreets);
    }

    /**
     * 删除街道信息.
     *
     * @param ids the ids
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:52:05 Remove streets v return message.
     */
    @RequestMapping("/streetsRemove")
    public VReturnMessage removeStreets(@RequestParam("ids") String ids) {
        if (!StringUtils.hasText(ids)) {
            return new VReturnMessage(false, VReturnMessage.WARN_CODE, "请选择要删除的街道");
        }
        return locStreetsService.removeStreets(ids.split(","));
    }

    /**
     * 获取所有道路.
     *
     * @param body the body
     * @return the streets
     * @author Nick Lv
     * @created 2017 /05/24 15:52:05
     */
    @RequestMapping("/street")
    public Map<String, Object> getStreets(@RequestBody SimplePageRequestBody<LocStreets> body) {
        return locStreetsService.getStreets(body);
    }

    /**
     * 获取道路详情.
     *
     * @param streetId the street id
     * @return the street
     * @author Nick Lv
     * @created 2017 /05/31 14:39:53
     */
    @RequestMapping("/streetInfo")
    public List<Map<String, String>> getaStreet(@RequestParam("id") String streetId) {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, locStreetsService.getaStreet(streetId));
        return TableInfoUtil.createTableInfoGroups(map);
    }

    /**
     * 根据区划code和层级获取街道信息.
     *
     * @param districtCode the district code
     * @param level        the level
     * @return the all streets
     * @author Nick Lv
     * @created 2017 /05/24 16:07:13
     */
    @RequestMapping("/streets")
    public List<LocStreets> getAllStreets(@RequestParam("code") String districtCode,
            @RequestParam("lvl") String level) {
        return locStreetsService.getAllStreetsByDistrictCode(districtCode, level);
    }
}
