/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.dao;

import net.evecom.gsmp.core.persitence.BaseJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocAddresses;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 地址编码
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /06/14 09:49:30
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocAddressesJPA extends BaseJPA<LocAddresses, Long> {

    /**
     * 利用md5查询地址
     *
     * @param md5 the md 5
     * @return the list
     * @author Nick Lv
     * @created 2017 /06/14 09:49:30 Check repeat grids list.
     */
    @Query("select la from LocAddresses la where la.addrMd5= :md5 and la.addrStatus= 1")
    List<LocAddresses> checkRepeatGrids(@Param("md5") String md5);

    /**
     * 根据地址名和街巷编码查询有效的地址
     *
     * @param addrName   the addr name
     * @param streetCode the street code
     * @return the list
     * @author Nick Lv
     * @created 2017 /06/14 09:49:30 Check repeat grids list.
     */
    @Query("select la from LocAddresses la where la.addrStatus= 1 " +
            "and la.addrName= :addrName and la.streetCode = :streetCode")
    LocAddresses getLocAddressesByAddrNameAndStreetCode(@Param("addrName") String addrName,
                                                              @Param("streetCode") String streetCode);

    /**
     * 根据地址名和社区编码查询有效的地址
     *
     * @param addrName     the addr name
     * @param districtCode the district code
     * @return the list
     * @author Nick Lv
     * @created 2017 /06/14 09:49:30 Check repeat grids list.
     */
    @Query("select la from LocAddresses la where la.addrStatus= 1 " +
            "and la.addrName= :addrName and la.districtCode = :districtCode")
    LocAddresses getLocAddressesByAddrNameAndDistrictCode(@Param("addrName") String addrName,
                                                                @Param("districtCode") String districtCode);

    /**
     * 逻辑删除地址
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /11/08 09:07:29 Remove addresses.
     */
    @Modifying
    @Query("update LocAddresses la set la.addrStatus = 0,la.canceloutDate = current_date where la.id= :id")
    void removeAddresses(@Param("id") Long id);
}
