/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service;

import net.evecom.gsmp.core.service.BaseService;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.entity.LocAddresses;

import java.util.Map;

/**
 * 地址信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:46
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocAddressesService extends BaseService<LocAddresses,Long> {
    /**
     * 根据条件获取门牌楼.
     *
     * @param body  the body
     * @param level the level
     * @return the addresses
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53
     */
    Map<String,Object> getAddresses(SimplePageRequestBody<LocAddresses> body, String level);

    /**
     * 描述 Gets addresses all.
     *
     * @param body  the body
     * @param level the level
     * @return the addresses all
     * @author Submarine Lin
     * @created 2017 /11/09 19:35:35
     */
    Map<String, Object> getAddressesAll(SimplePageRequestBody<LocAddresses> body, String level);

    /**
     * 新增修改门牌楼.
     *
     * @param locAddresses the loc addresses
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53 Add or update address v return message.
     */
    VReturnMessage addOrUpdateAddress(LocAddresses locAddresses);

    /**
     * 新增修改门牌楼,若街巷不存在新增街巷.
     *
     * @param locAddresses the loc addresses
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53 Add or update address v return message.
     */
    VReturnMessage addOrUpdateAddressAndStreet(LocAddresses locAddresses);

    /**
     * 删除门牌楼.
     *
     * @param idArray the id array
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /05/24 15:54:53 Remove addresses v return message.
     */
    VReturnMessage removeAddresses(String[] idArray);

    /**
     * 根据地址id获取地址详情.
     *
     * @param addressId the address id
     * @return the address
     * @author Nick Lv
     * @created 2017 /05/31 14:38:34
     */
    LocAddresses getaAddress(String addressId);
}
