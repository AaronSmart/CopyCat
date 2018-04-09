/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.dao;

import net.evecom.gsmp.core.persitence.BaseJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocDistricts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 区划信息JPA
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 14:24:29
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocDistrictsJPA extends BaseJPA<LocDistricts, Long> {
    /**
     * 根据父级id找区划信息.
     *
     * @param parentId the parent id
     * @return the districts by level
     * @author Nick Lv
     * @created 2017 /06/01 17:25:08
     */
    @Query("select ld from LocDistricts ld where ld.parentId = :id")
    List<LocDistricts> getDistrictsByLevel(@Param("id") Long parentId);

    /**
     * 根据父级id找区划信息.
     *
     * @return the districts by level
     * @author Nick Lv
     * @created 2017 /06/01 17:25:08
     */
    @Query("select ld from LocDistricts ld where ld.parentId is null")
    List<LocDistricts> getDistrictsNoParentId();

    /**
     * 根据编码找上级区划信息.
     *
     * @return the districts by level
     * @author Nick Lv
     * @created 2017 /06/01 17:25:08
     */
    @Query("select ld from LocDistricts ld where ld.id = " +
            "(select ld2.parentId from LocDistricts ld2 where ld2.districtCode = :districtCode)")
    LocDistricts getParentDistrictsByCode(@Param("districtCode") String districtCode);
}
