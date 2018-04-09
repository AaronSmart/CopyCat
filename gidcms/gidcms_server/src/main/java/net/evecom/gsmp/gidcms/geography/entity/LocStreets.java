/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.entity;

import net.evecom.gsmp.core.persitence.DataEntity;
import net.evecom.gsmp.gidcms.geography.utils.DataSourceEnum;
import net.evecom.gsmp.core.annotation.GridTable;
import net.evecom.gsmp.core.annotation.TableInfo;
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
 * 街道信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/24 17:00:01
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Entity
@Table(name = "GSMP_LOC_STREETS", schema = "GSMP", catalog = "")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocStreets extends DataEntity {

    /**
     * 标识
     */
    private Long id;
    /**
     * UUID标识
     */
    private String uuid;
    /**
     * 编号（12位社区行政编号+6位顺序号）
     */
    @GridTable(cnName = "编号",enName = "streetCode",viewOrder = "2")
    @TableInfo(cnName = "编号：",rowIndex = "1",groupName = "街道信息")
    private String streetCode;
    /**
     * 名称
     */
    @GridTable(cnName = "名称",enName = "streetName",viewOrder = "1", style = "200px")
    @TableInfo(cnName = "名称：",rowIndex = "2",groupName = "街道信息")
    private String streetName;
    /**
     * 别名
     */
    @TableInfo(cnName = "别名：",rowIndex = "3",groupName = "街道信息")
    private String streetAlias;
    /**
     * 曾用名
     */
    private String streetOldname;
    /**
     * 最小门（楼）牌号
     */
    @GridTable(cnName = "最小门（楼）牌号",enName = "minHousenum",viewOrder = "3", style = "140px")
    @TableInfo(cnName = "最小门（楼）牌号：",rowIndex = "4",groupName = "街道信息")
    private String minHousenum;
    /**
     * 最大门（楼）牌号
     */
    @GridTable(cnName = "最大门（楼）牌号",enName = "maxHouseNum",viewOrder = "4", style = "140px")
    @TableInfo(cnName = "最大门（楼）牌号：",rowIndex = "5",groupName = "街道信息")
    private String maxHouseNum;
    /**
     * 起点名称
     */
    private String beginName;
    /**
     * 止点名称
     */
    private String endName;
    /**
     * 走向
     */
    private String direction;
    /**
     * 数据来源（0-实测 1-地形图 2-基础地理数据 3-其他）
     */
    private Integer datasource;
    /**
     * 备注
     */
    @TableInfo(cnName = "备注：",rowIndex = "9",groupName = "街道信息")
    private String remark;
    /**
     * 所在社区（村）名称, 对应GSMP_LOC_DISTRICTS社区名称
     */
    @GridTable(cnName = "所在社区（村）",enName = "communityName",viewOrder = "6")
    @TableInfo(cnName = "所在社区（村）：",rowIndex = "6",groupName = "街道信息")
    private String communityName;
    /**
     * 所在社区（村）代码对应GSMP_LOC_DISTRICTS社区代码
     */
    private String communityCode;
    /**
     * 所在街道（镇、乡）名称, 对应GSMP_LOC_DISTRICTS街道（乡镇）名称
     */
    @GridTable(cnName = "所在街道（乡镇）",enName = "townName",viewOrder = "5")
    @TableInfo(cnName = "所在街道（乡镇）：",rowIndex = "7",groupName = "街道信息")
    private String townName;
    /**
     * 所在街道（镇、乡）代码，对应GSMP_LOC_DISTRICTS街道（镇、乡）代码
     */
    private String townCode;
    /**
     * 轮廓ID
     */
    private Long outlineId;
    /**
     * 数据来源中文.
     */
    @GridTable(cnName = "数据来源",enName = "dataSourceCnName",viewOrder = "7")
    @TableInfo(cnName = "数据来源：",rowIndex = "8",groupName = "街道信息")
    private String dataSourceCnName;
    /**
     * 乡镇的id.
     */
    private Long townId;
    /**
     * 0-无效 1-有效
     */
    private Integer isValid;



    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "my_sequence", sequenceName = "GSMP_LOC_STREETS_S", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets uuid.
     *
     * @return the uuid
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    @Basic
    @Column(name = "UUID", nullable = true, length = 128)
    public String getUuid() {
        return uuid;
    }

    /**
     * 描述 Sets uuid.
     *
     * @param uuid the uuid
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 描述 Gets street code.
     *
     * @return the street code
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    @Basic
    @Column(name = "STREET_CODE", nullable = true, length = 18)
    public String getStreetCode() {
        return streetCode;
    }

    /**
     * 描述 Sets street code.
     *
     * @param streetCode the street code
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    /**
     * 描述 Gets street name.
     *
     * @return the street name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    @Basic
    @Column(name = "STREET_NAME", nullable = false, length = 100)
    public String getStreetName() {
        return streetName;
    }

    /**
     * 描述 Sets street name.
     *
     * @param streetName the street name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * 描述 Gets street alias.
     *
     * @return the street alias
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    @Basic
    @Column(name = "STREET_ALIAS", nullable = true, length = 100)
    public String getStreetAlias() {
        return streetAlias;
    }

    /**
     * 描述 Sets street alias.
     *
     * @param streetAlias the street alias
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    public void setStreetAlias(String streetAlias) {
        this.streetAlias = streetAlias;
    }

    /**
     * 描述 Gets street oldname.
     *
     * @return the street oldname
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    @Basic
    @Column(name = "STREET_OLDNAME", nullable = true, length = 100)
    public String getStreetOldname() {
        return streetOldname;
    }

    /**
     * 描述 Sets street oldname.
     *
     * @param streetOldname the street oldname
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    public void setStreetOldname(String streetOldname) {
        this.streetOldname = streetOldname;
    }

    /**
     * 描述 Gets min housenum.
     *
     * @return the min housenum
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:01
     */
    @Basic
    @Column(name = "MIN_HOUSENUM", nullable = true, length = 16)
    public String getMinHousenum() {
        return minHousenum;
    }

    /**
     * 描述 Sets min housenum.
     *
     * @param minHousenum the min housenum
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setMinHousenum(String minHousenum) {
        this.minHousenum = minHousenum;
    }

    /**
     * 描述 Gets max house num.
     *
     * @return the max house num
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "MAX_HOUSE_NUM", nullable = true, length = 16)
    public String getMaxHouseNum() {
        return maxHouseNum;
    }

    /**
     * 描述 Sets max house num.
     *
     * @param maxHouseNum the max house num
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setMaxHouseNum(String maxHouseNum) {
        this.maxHouseNum = maxHouseNum;
    }

    /**
     * 描述 Gets begin name.
     *
     * @return the begin name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "BEGIN_NAME", nullable = true, length = 64)
    public String getBeginName() {
        return beginName;
    }

    /**
     * 描述 Sets begin name.
     *
     * @param beginName the begin name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setBeginName(String beginName) {
        this.beginName = beginName;
    }

    /**
     * 描述 Gets end name.
     *
     * @return the end name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "END_NAME", nullable = true, length = 64)
    public String getEndName() {
        return endName;
    }

    /**
     * 描述 Sets end name.
     *
     * @param endName the end name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setEndName(String endName) {
        this.endName = endName;
    }

    /**
     * 描述 Gets direction.
     *
     * @return the direction
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "DIRECTION", nullable = true, length = 64)
    public String getDirection() {
        return direction;
    }

    /**
     * 描述 Sets direction.
     *
     * @param direction the direction
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * 描述 Gets datasource.
     *
     * @return the datasource
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
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
     * @created 2017 /05/24 17:00:02
     */
    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    /**
     * 描述 Gets remark.
     *
     * @return the remark
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
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
     * @created 2017 /05/24 17:00:02
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 描述 Gets community name.
     *
     * @return the community name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "COMMUNITY_NAME", nullable = true, length = 100)
    public String getCommunityName() {
        return communityName;
    }

    /**
     * 描述 Sets community name.
     *
     * @param communityName the community name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    /**
     * 描述 Gets community code.
     *
     * @return the community code
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "COMMUNITY_CODE", nullable = true, length = 24)
    public String getCommunityCode() {
        return communityCode;
    }

    /**
     * 描述 Sets community code.
     *
     * @param communityCode the community code
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    /**
     * 描述 Gets town name.
     *
     * @return the town name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "TOWN_NAME", nullable = true, length = 100)
    public String getTownName() {
        return townName;
    }

    /**
     * 描述 Sets town name.
     *
     * @param townName the town name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setTownName(String townName) {
        this.townName = townName;
    }

    /**
     * 描述 Gets town code.
     *
     * @return the town code
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "TOWN_CODE", nullable = true, length = 24)
    public String getTownCode() {
        return townCode;
    }

    /**
     * 描述 Sets town code.
     *
     * @param townCode the town code
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    /**
     * 描述 Gets outline id.
     *
     * @return the outline id
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    @Basic
    @Column(name = "OUTLINE_ID", nullable = true, precision = 0)
    public Long getOutlineId() {
        return outlineId;
    }

    /**
     * 描述 Sets outline id.
     *
     * @param outlineId the outline id
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:02
     */
    public void setOutlineId(Long outlineId) {
        this.outlineId = outlineId;
    }

    /**
     * 描述 Gets data source cn name.
     *
     * @return the data source cn name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:03
     */
    @Transient
    public String getDataSourceCnName() {
        if(!Objects.equals(this.datasource,null)) {
           dataSourceCnName =  DataSourceEnum.getCnNameByValue(this.datasource).getDataSourceCnName();
        }
        return dataSourceCnName;
    }

    /**
     * 描述 Sets data source cn name.
     *
     * @param dataSourceCnName the data source cn name
     * @author Submarine Lin
     * @created 2017 /05/24 17:00:03
     */
    public void setDataSourceCnName(String dataSourceCnName) {
        this.dataSourceCnName = dataSourceCnName;
    }

    /**
     * 描述 Gets town id.
     *
     * @return the town id
     * @author Nick Lv
     * @created 2017 /06/14 10:06:45
     */
    @Formula("(select gld.id from gsmp.GSMP_LOC_DISTRICTS gld where gld.DISTRICT_CODE=TOWN_CODE)")
    public Long getTownId() {
        return townId;
    }

    /**
     * 描述 Sets town id.
     *
     * @param townId the town id
     * @author Nick Lv
     * @created 2017 /06/14 10:06:45
     */
    public void setTownId(Long townId) {
        this.townId = townId;
    }


    /**
     * Gets is valid.
     *
     * @return the is valid
     */
    @Basic
    @Column(name = "IS_VALID")
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * Sets is valid.
     *
     * @param isValid the is valid
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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


