/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.entity;

import net.evecom.gsmp.core.annotation.GridTable;
import net.evecom.gsmp.core.annotation.TableInfo;
import net.evecom.gsmp.core.persitence.DataEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 网格信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 14:26:30
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Entity
@Table(name = "GSMP_LOC_GRIDS", schema = "GSMP", catalog = "")
@Cacheable
public class LocGrids extends DataEntity {

    /**
     * 网格ID
     */
    private Long id;
    /**
     * 网格编码
     */
    private String code;
    /**
     * 网格名称
     */
    @GridTable(cnName = "网格名称",enName = "name",viewOrder = "1", style = "150px")
    @TableInfo(cnName = "网格名称：",rowIndex = "1",groupName = "网格信息")
    private String name;
    /**
     * 网格层级
     */
    private Long lvl;
    /**
     * 网格类型
     */
    private Long gridType;
    /**
     * 描述
     */
    @TableInfo(cnName = "描述：",rowIndex = "6",groupName = "网格信息")
    private String descripiton;
    /**
     * 上级网格中文名
     */
    @TableInfo(cnName = "上级网格：",rowIndex = "4",groupName = "网格信息",defaultValue = "无")
    @GridTable(cnName = "上级网格",enName = "parentIdCnName",viewOrder = "8")
    private String parentIdCnName;
    /**
     * 值班电话
     */
    @GridTable(cnName = "值班电话",enName = "dutyPhone",viewOrder = "7")
    @TableInfo(cnName = "值班电话：",rowIndex = "4",groupName = "网格信息")
    private String dutyPhone;
    /**
     * 网格图片
     */
    private String photoUrl;
    /**
     * 网格员数量
     */
    @GridTable(cnName = "网格员数量",enName = "memberNum",viewOrder = "6", style="90px")
    @TableInfo(cnName = "网格员数量：",rowIndex = "3",groupName = "网格信息")
    private Long memberNum;
    /**
     * 网格户数
     */
    @GridTable(cnName = "网格户数",enName = "householdeNum",viewOrder = "5", style="90px")
    @TableInfo(cnName = "网格户数：",rowIndex = "3",groupName = "网格信息")
    private Long householdeNum;
    /**
     * 网格面积（平方米）
     */
    @TableInfo(cnName = "网格面积（平方米）：",rowIndex = "2",groupName = "网格信息")
    @GridTable(cnName = "网格面积（平方米）",enName = "area",viewOrder = "4", style="130px")
    private Double area;
    /**
     * 上级网格ID.
     */
    private Long parentId;
    /**
     * 隶属行政区划编码.
     */
    private String adminDivisionCode;
    /**
     * 隶属行政区划.
     */
    @GridTable(cnName = "隶属行政区划",enName = "adminDivision",viewOrder = "9")
    @TableInfo(cnName = "隶属行政区划：",rowIndex = "5",groupName = "网格信息")
    private String adminDivision;
    /**
     * 手动排序
     */
    private Long manualSn;
    /**
     * 轮廓ID.
     */
    private Long geoOutlineId;

    /**
     * 可用状态
     */
    private Long enabled;
    /**
     * 行政区划ID
     */
    private Long districtId;

    /**
     * 网格类型中文名称.
     */
//    @GridTable(cnName = "网格类型",enName = "gridTypeCnName",viewOrder = "2")
//    @TableInfo(cnName = "网格类型：",rowIndex = "1",groupName = "网格信息")
    private String gridTypeCnName;
    /**
     * 网格层级的中文
     */
    @GridTable(cnName = "网格层级",enName = "lvlCnName",viewOrder = "3")
    @TableInfo(cnName = "网格层级：",rowIndex = "2",groupName = "网格信息")
    private String lvlCnName;

    /**
     * 上级网格编码
     */
    private String parentCode;

    /**
     * 描述 Gets parent code.
     *
     * @return the parent code
     * @author Submarine Lin
     * @created 2017 /11/10 14:02:12
     */
    @Formula("(select glg.code from gsmp.GSMP_LOC_GRIDS glg where glg.id = PARENT_ID)")
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 描述 Sets parent code.
     *
     * @param parentCode the parent code
     * @author Submarine Lin
     * @created 2017 /11/10 14:02:12
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "my_sequence", sequenceName = "GSMP_LOC_GRIDS_S", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets code.
     *
     * @return the code
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "CODE", nullable = true, length = 16)
    public String getCode() {
        return code;
    }

    /**
     * 描述 Sets code.
     *
     * @param code the code
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 描述 Gets lvl.
     *
     * @return the lvl
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "LVL", nullable = true, precision = 0)
    public Long getLvl() {
        return lvl;
    }

    /**
     * 描述 Sets lvl.
     *
     * @param lvl the lvl
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setLvl(Long lvl) {
        this.lvl = lvl;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "NAME", nullable = true, length = 128)
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     *
     * @param name the name
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets grid type.
     *
     * @return the grid type
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "GRID_TYPE", nullable = true, precision = 0)
    public Long getGridType() {
        return gridType;
    }

    /**
     * 描述 Sets grid type.
     *
     * @param gridType the grid type
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setGridType(Long gridType) {
        this.gridType = gridType;
    }

    /**
     * 描述 Gets descripiton.
     *
     * @return the descripiton
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "DESCRIPITON", nullable = true, length = 256)
    public String getDescripiton() {
        return descripiton;
    }

    /**
     * 描述 Sets descripiton.
     *
     * @param descripiton the descripiton
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }

    /**
     * 描述 Gets duty phone.
     *
     * @return the duty phone
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "DUTY_PHONE", nullable = true, length = 32)
    public String getDutyPhone() {
        return dutyPhone;
    }

    /**
     * 描述 Sets duty phone.
     *
     * @param dutyPhone the duty phone
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setDutyPhone(String dutyPhone) {
        this.dutyPhone = dutyPhone;
    }

    /**
     * 描述 Gets photo url.
     *
     * @return the photo url
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "PHOTO_URL", nullable = true, length = 256)
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * 描述 Sets photo url.
     *
     * @param photoUrl the photo url
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 描述 Gets member num.
     *
     * @return the member num
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "MEMBER_NUM", nullable = true, precision = 0)
    public Long getMemberNum() {
        return memberNum;
    }

    /**
     * 描述 Sets member num.
     *
     * @param memberNum the member num
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setMemberNum(Long memberNum) {
        this.memberNum = memberNum;
    }

    /**
     * 描述 Gets householde num.
     *
     * @return the householde num
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "HOUSEHOLDE_NUM", nullable = true, precision = 0)
    public Long getHouseholdeNum() {
        return householdeNum;
    }

    /**
     * 描述 Sets householde num.
     *
     * @param householdeNum the householde num
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setHouseholdeNum(Long householdeNum) {
        this.householdeNum = householdeNum;
    }

    /**
     * 描述 Gets area.
     *
     * @return the area
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "AREA", nullable = true, precision = 2)
    public Double getArea() {
        return area;
    }

    /**
     * 描述 Sets area.
     *
     * @param area the area
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * 描述 Gets parent id.
     *
     * @return the parent id
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "PARENT_ID", nullable = true, precision = 0)
    public Long getParentId() {
        return parentId;
    }

    /**
     * 描述 Sets parent id.
     *
     * @param parentId the parent id
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述 Gets admin division code.
     *
     * @return the admin division code
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    @Basic
    @Column(name = "ADMIN_DIVISION_CODE", nullable = true, length = 20)
    public String getAdminDivisionCode() {
        return adminDivisionCode;
    }

    /**
     * 描述 Sets admin division code.
     *
     * @param adminDivisionCode the admin division code
     * @author Nick Lv
     * @created 2017 /05/23 14:26:30
     */
    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    /**
     * 描述 Gets admin division.
     *
     * @return the admin division
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    @Basic
    @Column(name = "ADMIN_DIVISION", nullable = true, length = 128)
    public String getAdminDivision() {
        return adminDivision;
    }

    /**
     * 描述 Sets admin division.
     *
     * @param adminDivision the admin division
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    /**
     * 描述 Gets manual sn.
     *
     * @return the manual sn
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    @Basic
    @Column(name = "MANUAL_SN", nullable = true, precision = 0)
    public Long getManualSn() {
        return manualSn;
    }

    /**
     * 描述 Sets manual sn.
     *
     * @param manualSn the manual sn
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    public void setManualSn(Long manualSn) {
        this.manualSn = manualSn;
    }

    /**
     * 描述 Gets geography outline id.
     *
     * @return the geography outline id
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    @Basic
    @Column(name = "GEO_OUTLINE_ID", nullable = true, precision = 0)
    public Long getGeoOutlineId() {
        return geoOutlineId;
    }

    /**
     * 描述 Sets geography outline id.
     *
     * @param geoOutlineId the geography outline id
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    public void setGeoOutlineId(Long geoOutlineId) {
        this.geoOutlineId = geoOutlineId;
    }

    /**
     * 描述 Gets enabled.
     *
     * @return the enabled
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    @Basic
    @Column(name = "ENABLED", nullable = true, precision = 0)
    public Long getEnabled() {
        return enabled;
    }

    /**
     * 描述 Sets enabled.
     *
     * @param enabled the enabled
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    /**
     * 描述 Gets grid type cn name.
     *
     * @return the grid type cn name
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    @Formula("(select glgt.NAME from GSMP.GSMP_LOC_GRID_TYPES glgt where glgt.ID = GRID_TYPE)")
    public String getGridTypeCnName() {
        return gridTypeCnName;
    }

    /**
     * 描述 Sets grid type cn name.
     *
     * @param gridTypeCnName the grid type cn name
     * @author Nick Lv
     * @created 2017 /05/23 14:26:31
     */
    public void setGridTypeCnName(String gridTypeCnName) {
        this.gridTypeCnName = gridTypeCnName;
    }


    /**
     * 行政区划ID
     *
     * @return the district id
     * @author Nick Lv
     * @created 2017 /06/16 11:12:21
     */
    @Formula("(select gld.id from gsmp.GSMP_LOC_DISTRICTS gld where gld.DISTRICT_CODE=ADMIN_DIVISION_CODE)")
    public Long getDistrictId() {
        return districtId;
    }


    /**
     * 描述 Gets parent id cn name.
     *
     * @return the parent id cn name
     * @author Nick Lv
     * @created 2017 /06/16 11:12:18
     */
    @Formula("(select lg.name from gsmp.GSMP_LOC_GRIDS lg where lg.id=PARENT_ID)")
    public String getParentIdCnName() {
        return parentIdCnName;
    }

    /**
     * 描述 Sets parent id cn name.
     *
     * @param parentIdCnName the parent id cn name
     * @author Nick Lv
     * @created 2017 /06/16 11:12:18
     */
    public void setParentIdCnName(String parentIdCnName) {
        this.parentIdCnName = parentIdCnName;
    }


    /**
     * 描述 Sets district id.
     *
     * @param districtId the district id
     * @author Nick Lv
     * @created 2017 /06/16 11:12:21
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }


    /**
     * 描述 Gets lvl cn name.
     *
     * @return the lvl cn name
     * @author Nick Lv
     * @created 2017 /06/20 11:15:09
     */
    @Formula("(select cc.LABEL from gsmp.CODE_GRID_LVL cc where cc.code_value=lvl)")
    public String getLvlCnName() {
        return lvlCnName;
    }

    /**
     * 描述 Sets lvl cn name.
     *
     * @param lvlCnName the lvl cn name
     * @author Nick Lv
     * @created 2017 /06/20 11:15:09
     */
    public void setLvlCnName(String lvlCnName) {
        this.lvlCnName = lvlCnName;
    }
    /**
     * 描述 override hashCode.
     *
     * @author Submarine Lin
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    @Transient
    public Long getPrimaryKey() {
        return null;
    }

    /**
     * 描述 override toString.
     *
     * @author Submarine Lin
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 描述 override equals.
     *
     * @author Submarine Lin
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }
}

