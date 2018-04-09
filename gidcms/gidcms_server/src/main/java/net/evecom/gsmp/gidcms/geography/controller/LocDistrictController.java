/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.controller;

import net.evecom.gsmp.core.web.BaseController;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocDistricts;
import net.evecom.gsmp.gidcms.geography.service.LocDistrictsService;
import net.evecom.gsmp.core.annotation.TableInfoUtil;
import net.evecom.gsmp.gidcms.geography.vo.VDistrictTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区划信息控制层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:34:38
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/geography/district")
public class LocDistrictController extends BaseController<LocDistricts,Long> {
    /**
     * 区划信息服务层
     */
    @Autowired
    LocDistrictsService locDistrictsService;

    /**
     * 获取区划信息
     *
     * @param body the body
     * @return the loc districts
     * @author Nick Lv
     * @created 2017 /05/23 10:34:38
     */
    @RequestMapping("/districts")
    public Map<String, Object> getLocDistricts(@RequestBody SimplePageRequestBody<LocDistricts> body) {
        return locDistrictsService.getLocDistricts(body);
    }

    /**
     * 根据区划id寻找区划信息.
     *
     * @param districtId the district id
     * @return the loc district
     * @author Nick Lv
     * @created 2017 /05/31 14:19:21
     */
    @RequestMapping("/districtInfo")
    public List<Map<String, String>> getaLocDistrict(@RequestParam("id") String districtId) {
        Map<Integer, Object> map = new HashMap<>();
        LocDistricts locDistricts = locDistrictsService.getaLocDistrict(districtId);
        locDistricts.setDataSourceCnName(locDistricts.getDataSourceCnName());
        locDistricts.setSgmOrgTypeCnName(locDistricts.getSgmOrgTypeCnName());
        map.put(1, locDistricts);
        return TableInfoUtil.createTableInfoGroups(map);
    }

    /**
     * 根据层级获取下拉树.
     *
     * @param parentId the parent id
     * @return the districts by level
     * @author Nick Lv
     * @created 2017 /06/01 17:31:34
     */
    @RequestMapping("/districtsLevel")
    public List<VDistrictTree> getDistrictsByParentId(@RequestParam("id") String parentId) {
        return locDistrictsService.getDistrictsByParentId(parentId);
    }

    /**
     * 地址管理获取区划信息.
     *
     * @param parentId the parent id
     * @return the districts for addr by parent id
     * @author Nick Lv
     * @created 2017 /06/03 14:18:14
     */
    @RequestMapping("/districtsForAddr")
    public List<VDistrictTree> getDistrictsForAddrByParentId(@RequestParam("id") String parentId) {
        return locDistrictsService.getDistrictsForAddrByParentId(parentId);
    }

    /**
     * 网格管理获取区划信息
     *
     * @param parentId the parent id
     * @return the districts for grid by parent id
     * @author Nick Lv
     * @created 2017 /06/06 08:42:07
     */
    @RequestMapping("/districtsForGrid")
    public List<VDistrictTree> getDistrictsForGridByParentId(@RequestParam("id") String parentId) {
        return locDistrictsService.getDistrictsForGridByParentId(parentId);
    }


    /**
     * 获取所有的区划.
     *
     * @param level the level
     * @return the all districts
     * @author Nick Lv
     * @created 2017 /05/24 15:52:05
     */
    @RequestMapping("/districtses")
    public List<LocDistricts> getAllDistricts(@RequestParam("lvl") String level) {
        return locDistrictsService.getAllDistricts(level);
    }

    /**
     * 根据乡镇获取社区.
     *
     * @param districtCode the district code
     * @return the districts by town
     * @author Nick Lv
     * @created 2017 /06/06 17:32:02
     */
    @RequestMapping("/districtByTown")
    public List<LocDistricts> getDistrictsByTown(@RequestParam("code") String districtCode) {
        return locDistrictsService.getDistrictsByTown(districtCode);
    }
}
