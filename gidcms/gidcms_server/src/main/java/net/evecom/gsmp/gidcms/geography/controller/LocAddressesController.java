/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.controller;

import net.evecom.gsmp.core.web.BaseController;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocAddresses;
import net.evecom.gsmp.gidcms.geography.service.LocAddressesService;
import net.evecom.gsmp.core.annotation.TableInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地址信息控制层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:34:38
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/geography/address")
public class LocAddressesController extends BaseController<LocAddresses,Long> {
    /**
     * 地址信息服务类
     */
    @Autowired
    LocAddressesService locAddressesService;

    /**
     * 修改地址.
     *
     * @param locAddresses the loc addresses
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:52:04 Add or update address v return message.
     */
    @RequestMapping("/addressUpdate")
    public VReturnMessage addOrUpdateAddress(@RequestBody LocAddresses locAddresses) {
        return locAddressesService.addOrUpdateAddress(locAddresses);
    }

    /**
     * 新增或修改地址与新增街巷.
     *
     * @param locAddresses the loc addresses
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:52:04 Add or update address v return message.
     */
    @RequestMapping("/addOrUpdateAddressAndStreet")
    public VReturnMessage addOrUpdateAddressAndStreet(@RequestBody LocAddresses locAddresses) {
        return locAddressesService.addOrUpdateAddressAndStreet(locAddresses);
    }

    /**
     * 删除地址信息.
     *
     * @param ids the ids
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:52:04 Remove addresses v return message.
     */
    @RequestMapping("/addressesRemove")
    public VReturnMessage removeAddresses(@RequestParam("ids") String ids) {
        if (!StringUtils.hasText(ids)) {
            return new VReturnMessage(false,VReturnMessage.WARN_CODE,"请选择要删除的地址");
        }
        return locAddressesService.removeAddresses(ids.split(","));
    }

    /**
     * 获取门牌地址信息.
     *
     * @param body the body
     * @return the addresses
     * @author Nick Lv
     * @created 2017 /05/24 15:28:40
     */
    @RequestMapping("/address")
    public Map<String, Object> getAddresses(@RequestBody SimplePageRequestBody<LocAddresses> body
            ,@RequestParam("level")String level) {
        return locAddressesService.getAddresses(body,level);
    }

    /**
     * 获取门牌地址信息.
     *
     * @param body the body
     * @return the addresses
     * @author Nick Lv
     * @created 2017 /05/24 15:28:40
     */
    @RequestMapping("/addressAll")
    public Map<String, Object> getAddressesAll(@RequestBody SimplePageRequestBody<LocAddresses> body
            ,@RequestParam("level")String level) {
        return locAddressesService.getAddressesAll(body,level);
    }

    /**
     * 描述 获取门牌楼信息详情
     *
     * @param addressId the address id
     * @return the address
     * @author Nick Lv
     * @created 2017 /05/31 14:39:53
     */
    @RequestMapping("/addressInfo")
    public List<Map<String, String>> getaAddress(@RequestParam("id") String addressId) {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, locAddressesService.getaAddress(addressId));
        return TableInfoUtil.createTableInfoGroups(map);
    }
}
