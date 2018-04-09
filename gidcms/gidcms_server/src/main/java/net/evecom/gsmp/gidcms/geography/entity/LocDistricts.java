/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.entity;

import net.evecom.gsmp.core.persitence.BaseEntity;
import net.evecom.gsmp.core.persitence.DataEntity;
import net.evecom.gsmp.gidcms.geography.utils.DataSourceEnum;
import net.evecom.gsmp.gidcms.geography.utils.OrgTypeEnum;
import net.evecom.gsmp.core.annotation.TableInfo;
import net.evecom.gsmp.core.annotation.GridTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * 区划信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/24 16:48:45
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Entity
@Table(name = "GSMP_LOC_DISTRICTS", schema = "GSMP", catalog = "")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class LocDistricts extends DataEntity {

    /**
     * 标识
     */
    private Long id;
    /**
     * 区划编号
     */
    private String districtCode;
    /**
     * 级别
     */


    private String districtLvl;
    /**
     * 名称
     */
    @GridTable(cnName = "区划名称",enName = "districtName",viewOrder = "1", style = "200px")
    @TableInfo(cnName = "区划名称：",rowIndex = "1",groupName = "区划信息")
    private String districtName;
    /**
     * 别名
     */
    @GridTable(cnName = "区划别名",enName = "alias",viewOrder = "2")
    @TableInfo(cnName = "区划别名：",rowIndex = "1",groupName = "区划信息")
    private String alias;
    /**
     * 曾用名
     */
    private String oldname;
    /**
     * 四至范围
     */
    private String boundary;
    /**
     * 数据来源（0-实测 1-地形图 2-基础地理数据 3-其他）
     */
    private Integer datasource;
    /**
     * 初始日期
     */
    private Date dateCreated;
    /**
     * 变更日期
     */
    private Date dateModified;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上级ID
     */
    private Long parentId;
    /**
     * 区划类型（0-国家行政区划、1-群众自治组织区划、2-治安民警片区)
     */
    private Integer sgmOrgType;
    /**
     * 轮廓ID
     */
    private Long geoOutlineId;
    /**
     * 全称
     */
    private String namePath;
    /**
     * 数据来源（0-实测 1-地形图 2-基础地理数据 3-其他）
     */
    @GridTable(cnName = "数据来源",enName = "dataSourceCnName",viewOrder = "5")
    @TableInfo(cnName = "数据来源：",rowIndex = "2",groupName = "区划信息")
    private String dataSourceCnName;
    /**
     * 区划类型
     */
    @GridTable(cnName = "区划类型",enName = "sgmOrgTypeCnName",viewOrder = "4")
    @TableInfo(cnName = "区划类型：",rowIndex = "2",groupName = "区划信息")
    private String sgmOrgTypeCnName;
    /**
     * 上级区划
     */
    @GridTable(cnName = "上级区划",enName = "parentIdCnName",viewOrder = "6")
    @TableInfo(cnName = "上级区划：",rowIndex = "2",groupName = "区划信息")
    private String parentIdCnName;

    /**
     * The District lvl cn name.
     */
    @GridTable(cnName = "区划级别",enName = "districtLvlCnName",viewOrder = "3")
    @TableInfo(cnName = "区划级别：",rowIndex = "1",groupName = "区划信息")
    private String districtLvlCnName;



    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets district code.
     *
     * @return the district code
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "DISTRICT_CODE", nullable = true, length = 24)
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * 描述 Sets district code.
     *
     * @param districtCode the district code
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * 描述 Gets district lvl.
     *
     * @return the district lvl
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "DISTRICT_LVL", nullable = true, precision = 0)
    public String getDistrictLvl() {
        return districtLvl;
    }

    /**
     * 描述 Sets district lvl.
     *
     * @param districtLvl the district lvl
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setDistrictLvl(String districtLvl) {
        this.districtLvl = districtLvl;
    }

    /**
     * 描述 Gets district name.
     *
     * @return the district name
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "DISTRICT_NAME", nullable = true, length = 64)
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 描述 Sets district name.
     *
     * @param districtName the district name
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * 描述 Gets alias.
     *
     * @return the alias
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "ALIAS", nullable = true, length = 100)
    public String getAlias() {
        return alias;
    }

    /**
     * 描述 Sets alias.
     *
     * @param alias the alias
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 描述 Gets oldname.
     *
     * @return the oldname
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "OLDNAME", nullable = true, length = 100)
    public String getOldname() {
        return oldname;
    }

    /**
     * 描述 Sets oldname.
     *
     * @param oldname the oldname
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setOldname(String oldname) {
        this.oldname = oldname;
    }

    /**
     * 描述 Gets boundary.
     *
     * @return the boundary
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "BOUNDARY", nullable = true, length = 256)
    public String getBoundary() {
        return boundary;
    }

    /**
     * 描述 Sets boundary.
     *
     * @param boundary the boundary
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    /**
     * 描述 Gets datasource.
     *
     * @return the datasource
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "DATASOURCE", nullable = true, precision = 0)
    public Integer getDatasource() {
        return datasource;
    }

    /**
     * 描述 Sets datasource.
     *
     * @param datasource the datasource
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    /**
     * 描述 Gets date created.
     *
     * @return the date created
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "DATE_CREATED", nullable = true)
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * 描述 Sets date created.
     *
     * @param dateCreated the date created
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * 描述 Gets date modified.
     *
     * @return the date modified
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "DATE_MODIFIED", nullable = true)
    public Date getDateModified() {
        return dateModified;
    }

    /**
     * 描述 Sets date modified.
     *
     * @param dateModified the date modified
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * 描述 Gets remark.
     *
     * @return the remark
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    @Basic
    @Column(name = "REMARK", nullable = true, length = 2000)
    public String getRemark() {
        return remark;
    }

    /**
     * 描述 Sets remark.
     *
     * @param remark the remark
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:45
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 描述 Gets parent id.
     *
     * @return the parent id
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
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
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述 Gets sgm org type.
     *
     * @return the sgm org type
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    @Basic
    @Column(name = "SGM_ORG_TYPE", nullable = true, precision = 0)
    public Integer getSgmOrgType() {
        return sgmOrgType;
    }

    /**
     * 描述 Sets sgm org type.
     *
     * @param sgmOrgType the sgm org type
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    public void setSgmOrgType(Integer sgmOrgType) {
        this.sgmOrgType = sgmOrgType;
    }

    /**
     * 描述 Gets geography outline id.
     *
     * @return the geography outline id
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
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
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    public void setGeoOutlineId(Long geoOutlineId) {
        this.geoOutlineId = geoOutlineId;
    }

    /**
     * 描述 Gets name path.
     *
     * @return the name path
     * @author Warren Chen
     * @created 2017 /06/14 11:18:49
     */
    @Basic
    @Column(name="NAME_PATH",nullable = true)
    public String getNamePath() {
        return namePath;
    }

    /**
     * 描述 Sets name path.
     *
     * @param namePath the name path
     * @author Warren Chen
     * @created 2017 /06/14 11:18:49
     */
    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    /**
     * 描述 Gets data source cn name.
     *
     * @return the data source cn name
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    @Transient
    public String getDataSourceCnName() {
        if(!Objects.equals(this.datasource,null)) {
            if (!Objects.equals(this.datasource, null)) {
                dataSourceCnName = DataSourceEnum.getCnNameByValue(this.datasource).getDataSourceCnName();
            }
        }
            return dataSourceCnName;
    }

    /**
     * 描述 Sets data source cn name.
     *
     * @param dataSourceCnName the data source cn name
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    public void setDataSourceCnName(String dataSourceCnName) {
        this.dataSourceCnName = dataSourceCnName;
    }

    /**
     * 描述 Gets sgm org type cn name.
     *
     * @return the sgm org type cn name
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    @Transient
    public String getSgmOrgTypeCnName() {
        if(!Objects.equals(null,this.sgmOrgType)) {
            this.sgmOrgTypeCnName = OrgTypeEnum.getCnNameByValue(this.sgmOrgType).getOrgTypeCnName();
        }
        return sgmOrgTypeCnName;
    }

    /**
     * 描述 Sets sgm org type cn name.
     *
     * @param sgmOrgTypeCnName the sgm org type cn name
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:46
     */
    public void setSgmOrgTypeCnName(String sgmOrgTypeCnName) {
        this.sgmOrgTypeCnName = sgmOrgTypeCnName;
    }

    /**
     * 描述 Gets parent id cn name.
     *
     * @return the parent id cn name
     * @author Warren Chen
     * @created 2017 /06/14 11:18:50
     */
    @Formula("(select gld.district_Name from GSMP.GSMP_LOC_DISTRICTS gld where gld.id=PARENT_ID)")
    public String getParentIdCnName() {
        return parentIdCnName;
    }

    /**
     * 描述 Sets parent id cn name.
     *
     * @param parentIdCnName the parent id cn name
     * @author Warren Chen
     * @created 2017 /06/14 11:18:50
     */
    public void setParentIdCnName(String parentIdCnName) {
        this.parentIdCnName = parentIdCnName;
    }

    /**
     * 描述 Gets district lvl cn name.
     *
     * @return the district lvl cn name
     * @author Warren Chen
     * @created 2017 /06/14 11:18:47
     */
    @Formula("(select cc.label from gsmp.CODE_DISTRICT_LVL cc where cc.code_value = DISTRICT_LVL )")
    public String getDistrictLvlCnName() {
        return districtLvlCnName;
    }

    /**
     * 描述 Sets district lvl cn name.
     *
     * @param districtLvlCnName the district lvl cn name
     * @author Warren Chen
     * @created 2017 /06/14 11:18:47
     */
    public void setDistrictLvlCnName(String districtLvlCnName) {
        this.districtLvlCnName = districtLvlCnName;
    }

    @Override
    @Transient
    public Long getPrimaryKey() {
        return this.id;
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
}
