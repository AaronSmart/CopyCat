/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.dao;

import net.evecom.gsmp.core.persitence.BaseJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocGrids;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 网格信息JPA
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 14:24:32
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public interface LocGridsJPA extends BaseJPA<LocGrids,Long> {
    /**
     * 根据网格id设置网格状态为冻结.
     *
     * @param gridId the grid id
     * @author Nick Lv
     * @created 2017 /05/23 14:24:32 Remove grid.
     */
    @Modifying
    @Query("update LocGrids lg set lg.enabled = 0 where lg.id= :id")
    void removeGrid(@Param("id")Long gridId);


    /**
     * 根据层级获取网格
     *
     * @param level the level
     * @return the grids by level
     * @author Nick Lv
     * @created 2017 /05/23 19:28:41
     */
    @Query("select lg from LocGrids lg where lg.lvl= :lvl and lg.enabled=1")
    List<LocGrids> getGridsByLevel(@Param("lvl") Long level);

//    /**
//     * 找出同一个父级网格下，与要新增或修改的网格同名或者同code的网格
//     *
//     * @param code     the code
//     * @param level    the level
//     * @param name     the name
//     * @param type     the type
//     * @param parentId the parent id
//     * @param id       the id
//     * @return the list
//     * @author Nick Lv
//     * @created 2017 /06/13 19:52:16 Check repeat grids list.
//     */
//    @Query("select lg from LocGrids lg where " +
//            "(lg.parentId= :parentId and lg.lvl= :lvl and lg.gridType= :gridType and lg.enabled= 1 and lg.id <> :id)"
//            + "and (lg.code= :code or lg.name= :name)")
//    List<LocGrids> checkRepeatGrids(@Param("code")String code,@Param("lvl")Long level,@Param("name")String name,
//                                    @Param("gridType")Long type,@Param("parentId")Long parentId,@Param("id")Long id);
//
//    /**
//     * 找出同一个父级网格下，与要新增或修改的网格同名或者同code的网格,不加id
//     *
//     * @param code     the code
//     * @param level    the level
//     * @param name     the name
//     * @param type     the type
//     * @param parentId the parent id
//     * @return the list
//     * @author Nick Lv
//     * @created 2017 /06/13 19:52:16 Check repeat grids list.
//     */
//    @Query("select lg from LocGrids lg where " +
//            "(lg.parentId= :parentId and lg.lvl= :lvl and lg.gridType= :gridType and lg.enabled= 1)" +
//            "and (lg.code= :code or lg.name= :name)")
//    List<LocGrids> checkRepeatGridsHavaId(@Param("code")String code,
//                                          @Param("lvl")Long level,
//                                          @Param("name")String name,
//                                          @Param("gridType")Long type,
//                                          @Param("parentId")Long parentId);


    /**
     * 描述 验证网格编码的前缀是否为上级网格编码
     *
     * @param parentId  the parent id
     * @param checkCode the check code
     * @return the integer
     * @author Submarine Lin
     * @created 2017 /10/17 14:06:14 Check code prefix integer.
     */
    @Query("SELECT case when SUBSTRING(:checkCode, 0, length(lg.code)) = lg.code then 1 else 0 end" +
            " from LocGrids lg where lg.id = :parentId")
    Integer checkCodePrefix(@Param("parentId")Long parentId, @Param("checkCode") String checkCode);

    /**
     * 描述 获取同一个网格下的网格
     *
     * @param parentId  the parent id
     * @return the integer
     * @author Submarine Lin
     * @created 2017 /10/17 14:06:14 Check code prefix integer.
     */
    @Query("SELECT lg from LocGrids lg where lg.enabled=1 and lg.parentId = :parentId")
    List<LocGrids> getNameByParentId(@Param("parentId")Long parentId);

//    /**
//     * 修改的时候，保证code的唯一性
//     *
//     * @param code the code
//     * @param id   the id
//     * @return the list
//     * @author Nick Lv
//     * @created 2017 /06/16 11:39:46 Check repeat by code list.
//     */
//    @Query("select lg from LocGrids lg where lg.code = :code and lg.id <> :id and lg.enabled=1")
//    List<LocGrids> checkRepeatByCodeAndId(@Param("code")String code,@Param("id")Long id);

    /**
     * 新增的时候，保证code的唯一性
     *
     * @param code the code
     * @return the list
     * @author Nick Lv
     * @created 2017 /06/16 11:39:46 Check repeat by code list.
     */
    @Query("select lg from LocGrids lg where lg.code = :code and lg.enabled=1")
    List<LocGrids> checkRepeatByCode(@Param("code")String code);

    /**
     * 检测是否有子网格
     *
     * @param parentId the parent id
     * @return the list
     * @author Nick Lv
     * @created 2017 /10/17 14:06:15 Has children grids list.
     */
    @Query("select lg from LocGrids lg where lg.parentId= :parentId and lg.enabled = 1")
    List<LocGrids> hasChildrenGrids(@Param("parentId")Long parentId);
}
