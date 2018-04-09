/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.dao;

import net.evecom.gsmp.core.persitence.BaseJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocStreets;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 街道信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /06/15 21:30:23
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocStreetsJPA extends BaseJPA<LocStreets, Long> {

    /**
     * 根据code查找街道
     *
     * @param streetCode the street code
     * @return the list
     * @author Nick Lv
     * @created 2017 /06/15 21:30:23 Check repeat streets list.
     */
    @Query("select ls from LocStreets ls where ls.streetCode= :sCode and ls.isValid= 1")
    List<LocStreets> checkRepeatStreetsNoId(@Param("sCode") String streetCode);

    /**
     * 描述 获取同一个社区下的街道名称
     *
     * @param communityCode the community code
     * @param streetName    the street name
     * @return the name bycommunity code
     * @author Submarine Lin
     * @created 2017 /10/17 15:39:55
     */
    @Query("SELECT ls from LocStreets ls where ls.isValid = 1 and ls.communityCode = :communityCode " +
            "and ls.streetName = :streetName")
    List<LocStreets> getNameByCommunityCode(@Param("communityCode") String communityCode,
                                            @Param("streetName") String streetName);
//    /**
//     * 根据code和id查找除自己以外的
//     *
//     * @param streetCode the street code
//     * @param id         the id
//     * @return the list
//     * @author Nick Lv
//     * @created 2017 /06/16 18:07:57 Check repeat streets list.
//     */
//    @Query("select ls from LocStreets ls where ls.streetCode= :sCode and ls.id <> :id and ls.isValid= 1")
//    List<LocStreets> checkRepeatStreets(@Param("sCode") String streetCode, @Param("id") Long id);

    /**
     * 逻辑删除街道
     *
     * @param id id
     * @author Nick Lv
     * @created 2017 /06/16 18:07:57 Check repeat streets list.
     */
    @Modifying
    @Query("update LocStreets ls set ls.isValid = 0 where ls.id= :id")
    void removeStreet(@Param("id") Long id);

    /**
     * 描述 根据社区创造编码，若为0则编码为异常的数据
     *
     * @param communityCode the community code
     * @return the string
     * @author Submarine Lin
     * @created 2017 /11/07 15:00:13 Build street code by community code string.
     */
//    @Query("select coalesce(concat(max(gls.streetCode) + 1,''), concat(:communityCode,'000001')) " +
//            " from LocStreets gls where gls.communityCode = :communityCode")
    @Query("select (case when count(gls.id) = 0 then concat(:communityCode, '000001') else (case when substring(" +
            "max(gls.streetCode), -6) = '999999' then '0' else concat(max (gls.streetCode)  + 1, '') end) end) " +
            " from LocStreets gls where gls.isValid = 1 and gls.communityCode = :communityCode")
    String buildStreetCodeByCommunityCode(@Param("communityCode") String communityCode);
}
