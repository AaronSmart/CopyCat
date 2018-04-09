/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.controller;

import net.evecom.gsmp.core.web.BaseController;
import net.evecom.gsmp.gidcms.geography.entity.LocDistrictDetails;
import net.evecom.gsmp.core.annotation.TableInfoUtil;
import net.evecom.gsmp.gidcms.geography.service.LocDistrictDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 区划介绍控制层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:34:38
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/geography/locdistrictDetail")
public class LocDistrictDetailsController extends BaseController<LocDistrictDetails,Long> {
    /**
     * 区划介绍服务层
     */
    @Autowired
    LocDistrictDetailsService locDistrictDetailsService;


    /**
     * 获取区划的介绍.
     *
     * @param districtId the district id
     * @return the loc districts detail
     * @author Nick Lv
     * @created 2017 /05/23 11:22:46
     */
    @RequestMapping("/districtsDetail")
    public List<Map<String, String>> getLocDistrictsDetail(@RequestParam("id") String districtId) {
        LocDistrictDetails condition = new LocDistrictDetails();
        condition.setId(Long.valueOf(districtId));
        Map<Integer, Object> map = new HashMap<>();
        if (Objects.equals(null, locDistrictDetailsService.getLocDistrictsDetail(condition))) {
            map.put(1, new LocDistrictDetails());
        } else {
            map.put(1, locDistrictDetailsService.getLocDistrictsDetail(condition));
        }

        return TableInfoUtil.createTableInfoGroups(map);
    }
}
