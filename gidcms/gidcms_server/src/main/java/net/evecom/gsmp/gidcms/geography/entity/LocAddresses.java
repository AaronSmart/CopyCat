/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vividsolutions.jts.geom.Geometry;
import net.evecom.gsmp.core.annotation.GridTable;
import net.evecom.gsmp.core.annotation.TableInfo;
import net.evecom.gsmp.core.persitence.DataEntity;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 地址编码.
 *
 * @author Submarine Lin
 * @created 2017 /05/26 09:35:38
 */
@Entity
@Table(name = "GSMP_LOC_ADDRESSES", schema = "GSMP", catalog = "")
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class LocAddresses extends DataEntity {

    /**
     * 地址编码
     */
    private Long id;
    /**
     * 地址UUID编码
     */
    private String uuid;
    /**
     * 标准地址名称
     */

    @GridTable(cnName = "标准地址名称", enName = "addrName", viewOrder = "1")
    @TableInfo(cnName = "标准地址名称：", rowIndex = "1", groupName = "门牌楼信息")
    private String addrName;

    /**
     * 空间数据
     */
    private Geometry geoLocation;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 行政区域CODE
     */
    private String districtCode;
    /**
     * 街巷编号
     */
    private String streetCode;
    /**
     * 地名路名(街巷)
     */
    private String roadName;
    /**
     * 门牌号
     */
    @GridTable(cnName = "门牌号", enName = "houseNumber", viewOrder = "5", style = "100px")
    @TableInfo(cnName = "门牌号：", rowIndex = "5", groupName = "门牌楼信息")
    private String houseNumber;
    /**
     * 城乡分类属性.
     */
    private String urbanRuralClass;
    /**
     * 属性标识（0-注销 1-启用 2-停用）
     */
    private String addrStatus;
    /**
     * 启用日期
     */
    private Date startingDate;
    /**
     * 停用日期
     */
    private Date stoppingDate;
    /**
     * 注销日期
     */
    private Date canceloutDate;
    /**
     * 登记单位名称
     */
    private String orgName;
    /**
     * 登记单位代码（单位统一代码）
     */
    private String orgId;
    /**
     * 兴趣点名
     */
    private String interestingPointName;
    /**
     * 组合规则
     */
    private Long combiningRule;
    /**
     * 数据来源（0-实测 1-地形图 2-基础地理数据 3-其他）
     */
    private Integer datasource;
    /**
     * 最后变更时间
     */
    private Date lastModified;

    /**
     * 小区名称.
     */
    private String quarterName;
    /**
     * 标准地址MD5.
     */
    private String addrMd5;
    /**
     * 地址简称.
     */
    private String addrShort;
    /**
     * 状态
     */
// @GridTable(cnName = "状态", enName = "addrStatusCnName", viewOrder = "8")
    // @TableInfo(cnName = "状态：", rowIndex = "8", groupName = "门牌楼信息")
    private String addrStatusCnName;
    /**
     * 所属行政区划
     */
    @GridTable(cnName = "所属行政区划", enName = "districtCnName", viewOrder = "2", style = "160px")
    @TableInfo(cnName = "所属行政区划：", rowIndex = "2", groupName = "门牌楼信息")
    private String districtCnName;
    /**
     * 所属街道
     */
 @GridTable(cnName = "所属街巷",enName = "streetCnName",viewOrder = "4", style = "160px" )
     @TableInfo(cnName = "所属街巷：",rowIndex = "4",groupName = "门牌楼信息")
    private String streetCnName;
    /**
     * 数据来源（0-实测 1-地形图 2-基础地理数据 3-其他）
     */
    private String dataSourceCnName;

    /**
     * 树的层级
     */
    private Integer treeLevel;

    /**
     * 区划地址全称.
     */
    private String treePathName;

    /**
     * 区划id.
     */
    private Long districtId;

    /**
     * 城乡分类属性.
     */
    @GridTable(cnName = "城乡分类属性", enName = "urbanRuralClassCnName", viewOrder = "6", style = "140px")
    @TableInfo(cnName = "城乡分类属性：", rowIndex = "6", groupName = "门牌楼信息")
    private String urbanRuralClassCnName;

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:38
     */
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "my_sequence", sequenceName = "GSMP_LOC_ADDRESSES_S", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:38
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets uuid.
     *
     * @return the uuid
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:39
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
     * @created 2017 /05/26 09:35:39
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 描述 Gets addr name.
     *
     * @return the addr name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:39
     */
    @Basic
    @Column(name = "ADDR_NAME", nullable = true, length = 128)
    public String getAddrName() {
        return addrName;
    }

    /**
     * 描述 Sets addr name.
     *
     * @param addrName the addr name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:39
     */
    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    /**
     * 描述 Gets geography location.
     *
     * @return the geography location
     * @author Nick Lv
     * @created 2017 /06/13 20:18:59
     */
    @Column(name = "GEO_LOCATION")
    public Geometry getGeoLocation() {
        return geoLocation;
    }

    /**
     * 描述 Sets geography location.
     *
     * @param geoLocation the geography location
     * @author Warren Chen
     * @created 2017 /06/14 11:52:02
     */
    @JsonBackReference
    /**
     * 描述 Sets geography location.
     *
     * @param geoLocation
     *            the geography location
     * @author Nick Lv
     * @created 2017 /06/13 20:18:59
     */
    public void setGeoLocation(Geometry geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * 描述 Gets longitude.
     *
     * @return the longitude
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:39
     */
    @Basic
    @Column(name = "LONGITUDE", nullable = true, precision = 15)
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 描述 Sets longitude.
     *
     * @param longitude the longitude
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:39
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 描述 Gets latitude.
     *
     * @return the latitude
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:39
     */
    @Basic
    @Column(name = "LATITUDE", nullable = true, precision = 15)
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 描述 Sets latitude.
     *
     * @param latitude the latitude
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 描述 Gets district code.
     *
     * @return the district code
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    @Basic
    @Column(name = "DISTRICT_CODE", nullable = true, length = 12)
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * 描述 Sets district code.
     *
     * @param districtCode the district code
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * 描述 Gets street code.
     *
     * @return the street code
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
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
     * @created 2017 /05/26 09:35:40
     */
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    /**
     * 描述 Gets road name.
     *
     * @return the road name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    @Basic
    @Column(name = "ROAD_NAME", nullable = true, length = 100)
    public String getRoadName() {
        return roadName;
    }

    /**
     * 描述 Sets road name.
     *
     * @param roadName the road name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    /**
     * 描述 Gets house number.
     *
     * @return the house number
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    @Basic
    @Column(name = "HOUSE_NUMBER", nullable = true, length = 20)
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * 描述 Sets house number.
     *
     * @param houseNumber the house number
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * 描述 Gets urban rural class.
     *
     * @return the urban rural class
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    @Basic
    @Column(name = "URBAN_RURAL_CLASS", nullable = true, length = 3)
    public String getUrbanRuralClass() {
        return urbanRuralClass;
    }

    /**
     * 描述 Sets urban rural class.
     *
     * @param urbanRuralClass the urban rural class
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    public void setUrbanRuralClass(String urbanRuralClass) {
        this.urbanRuralClass = urbanRuralClass;
    }

    /**
     * 描述 Gets addr status.
     *
     * @return the addr status
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:40
     */
    @Basic
    @Column(name = "ADDR_STATUS", nullable = true, length = 1)
    public String getAddrStatus() {
        return addrStatus;
    }

    /**
     * 描述 Sets addr status.
     *
     * @param addrStatus the addr status
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    public void setAddrStatus(String addrStatus) {
        this.addrStatus = addrStatus;
    }

    /**
     * 描述 Gets starting date.
     *
     * @return the starting date
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    @Basic
    @Column(name = "STARTING_DATE", nullable = true)
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     * 描述 Sets starting date.
     *
     * @param startingDate the starting date
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * 描述 Gets stopping date.
     *
     * @return the stopping date
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    @Basic
    @Column(name = "STOPPING_DATE", nullable = true)
    public Date getStoppingDate() {
        return stoppingDate;
    }

    /**
     * 描述 Sets stopping date.
     *
     * @param stoppingDate the stopping date
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    public void setStoppingDate(Date stoppingDate) {
        this.stoppingDate = stoppingDate;
    }

    /**
     * 描述 Gets cancelout date.
     *
     * @return the cancelout date
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    @Basic
    @Column(name = "CANCELOUT_DATE", nullable = true)
    public Date getCanceloutDate() {
        return canceloutDate;
    }

    /**
     * 描述 Sets cancelout date.
     *
     * @param canceloutDate the cancelout date
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    public void setCanceloutDate(Date canceloutDate) {
        this.canceloutDate = canceloutDate;
    }

    /**
     * 描述 Gets org name.
     *
     * @return the org name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    @Basic
    @Column(name = "ORG_NAME", nullable = true, length = 60)
    public String getOrgName() {
        return orgName;
    }

    /**
     * 描述 Sets org name.
     *
     * @param orgName the org name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 描述 Gets org id.
     *
     * @return the org id
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    @Basic
    @Column(name = "ORG_ID", nullable = true, length = 18)
    public String getOrgId() {
        return orgId;
    }

    /**
     * 描述 Sets org id.
     *
     * @param orgId the org id
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 描述 Gets interesting point name.
     *
     * @return the interesting point name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    @Basic
    @Column(name = "INTERESTING_POINT_NAME", nullable = true, length = 64)
    public String getInterestingPointName() {
        return interestingPointName;
    }

    /**
     * 描述 Sets interesting point name.
     *
     * @param interestingPointName the interesting point name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    public void setInterestingPointName(String interestingPointName) {
        this.interestingPointName = interestingPointName;
    }

    /**
     * 描述 Gets combining rule.
     *
     * @return the combining rule
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:41
     */
    @Basic
    @Column(name = "COMBINING_RULE", nullable = true, precision = 0)
    public Long getCombiningRule() {
        return combiningRule;
    }

    /**
     * 描述 Sets combining rule.
     *
     * @param combiningRule the combining rule
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:42
     */
    public void setCombiningRule(Long combiningRule) {
        this.combiningRule = combiningRule;
    }

    /**
     * 描述 Gets datasource.
     *
     * @return the datasource
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:42
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
     * @created 2017 /05/26 09:35:42
     */
    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    /**
     * 描述 Gets last modified.
     *
     * @return the last modified
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:42
     */
    @Basic
    @Column(name = "LAST_MODIFIED", nullable = true)
    @LastModifiedDate
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * 描述 Sets last modified.
     *
     * @param lastModified the last modified
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:42
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * 描述 Gets quarter name.
     *
     * @return the quarter name
     * @author Nick Lv
     * @created 2017 /06/13 20:27:38
     */
    @Basic
    @Column(name = "QUARTER_NAME", nullable = true, length = 100)
    public String getQuarterName() {
        return quarterName;
    }

    /**
     * 描述 Sets quarter name.
     *
     * @param quarterName the quarter name
     * @author Nick Lv
     * @created 2017 /06/13 20:27:38
     */
    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    /**
     * 描述 Gets addr md 5.
     *
     * @return the addr md 5
     * @author Nick Lv
     * @created 2017 /06/13 20:27:38
     */
    @Basic
    @Column(name = "ADDR_MD5", nullable = true, length = 512)
    public String getAddrMd5() {
        return addrMd5;
    }

    /**
     * 描述 Sets addr md 5.
     *
     * @param addrMd5 the addr md 5
     * @author Nick Lv
     * @created 2017 /06/13 20:27:38
     */
    public void setAddrMd5(String addrMd5) {
        this.addrMd5 = addrMd5;
    }

    /**
     * 描述 Gets addr short.
     *
     * @return the addr short
     * @author Nick Lv
     * @created 2017 /06/13 20:27:38
     */
    @Basic
    @Column(name = "ADDR_SHORT", nullable = true, length = 128)
    public String getAddrShort() {
        return addrShort;
    }

    /**
     * 描述 Sets addr short.
     *
     * @param addrShort the addr short
     * @author Nick Lv
     * @created 2017 /06/13 20:27:38
     */
    public void setAddrShort(String addrShort) {
        this.addrShort = addrShort;
    }

    /**
     * 描述 Gets addr status cn name.
     *
     * @return the addr status cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    @Formula("(select decode(ADDR_STATUS, 0, '注销', 1, '启用', 2, '停用') from dual)")
    public String getAddrStatusCnName() {
        return addrStatusCnName;
    }

    /**
     * 描述 Sets addr status cn name.
     *
     * @param addrStatusCnName the addr status cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    public void setAddrStatusCnName(String addrStatusCnName) {
        this.addrStatusCnName = addrStatusCnName;
    }

    /**
     * 描述 Gets district cn name.
     *
     * @return the district cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    @Formula("(select gld.DISTRICT_NAME from GSMP.GSMP_LOC_DISTRICTS gld where gld.DISTRICT_CODE=DISTRICT_CODE)")
    public String getDistrictCnName() {
        return districtCnName;
    }

    /**
     * 描述 Sets district cn name.
     *
     * @param districtCnName the district cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    public void setDistrictCnName(String districtCnName) {
        this.districtCnName = districtCnName;
    }

    /**
     * 描述 Gets street cn name.
     *
     * @return the street cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    @Formula("(select gls.STREET_NAME from GSMP.GSMP_LOC_STREETS gls " +
            "where gls.STREET_CODE = STREET_CODE and gls.is_valid = 1)")
    public String getStreetCnName() {
        return streetCnName;
    }

    /**
     * 描述 Sets street cn name.
     *
     * @param streetCnName the street cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    public void setStreetCnName(String streetCnName) {
        this.streetCnName = streetCnName;
    }

    /**
     * 描述 Gets data source cn name.
     *
     * @return the data source cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    @Formula("(select decode(DATASOURCE, 0, '实测', 1, '地形图', 2, '基础地理数据', 3, '其它') from dual)")
    public String getDataSourceCnName() {
        return dataSourceCnName;
    }

    /**
     * 描述 Sets data source cn name.
     *
     * @param dataSourceCnName the data source cn name
     * @author Submarine Lin
     * @created 2017 /05/26 09:35:43
     */
    public void setDataSourceCnName(String dataSourceCnName) {
        this.dataSourceCnName = dataSourceCnName;
    }

    /**
     * 描述 Gets tree level.
     *
     * @return the tree level
     * @author Nick Lv
     * @created 2017 /06/13 20:19:03
     */
    @Formula("(select gld.DISTRICT_LVL from gsmp.GSMP_LOC_DISTRICTS gld where gld.DISTRICT_CODE=DISTRICT_CODE)")
    public Integer getTreeLevel() {
        return treeLevel;
    }

    /**
     * 描述 Sets tree level.
     *
     * @param treeLevel the tree level
     * @author Nick Lv
     * @created 2017 /06/13 20:19:03
     */
    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    /**
     * 描述 Gets district id.
     *
     * @return the district id
     * @author Nick Lv
     * @created 2017 /06/13 20:19:03
     */
    @Formula("(select gld.id from gsmp.GSMP_LOC_DISTRICTS gld where gld.DISTRICT_CODE=DISTRICT_CODE)")
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * 描述 Sets district id.
     *
     * @param districtId the district id
     * @author Nick Lv
     * @created 2017 /06/13 20:19:03
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    /**
     * 描述 Gets urban rural class cn name.
     *
     * @return the urban rural class cn name
     * @author Warren Chen
     * @created 2017 /06/14 11:52:01
     */
    @Formula("(select cc.label from gsmp.CODE_URBAN_RURAL_CLASS cc where cc.code_value=URBAN_RURAL_CLASS)")
    public String getUrbanRuralClassCnName() {
        return urbanRuralClassCnName;
    }

    /**
     * 描述 Sets urban rural class cn name.
     *
     * @param urbanRuralClassCnName the urban rural class cn name
     * @author Warren Chen
     * @created 2017 /06/14 11:52:01
     */
    public void setUrbanRuralClassCnName(String urbanRuralClassCnName) {
        this.urbanRuralClassCnName = urbanRuralClassCnName;
    }

    /**
     * 描述 Gets tree pash name.
     *
     * @return the tree pash name
     * @author Nick Lv
     * @created 2017 /06/20 12:03:09
     */
    @Formula("(select gls.NAME_PATH from GSMP.GSMP_LOC_DISTRICTS gls where gls.DISTRICT_code = DISTRICT_code)")
    public String getTreePathName() {
        return treePathName;
    }

    /**
     * 描述 Sets tree pash name.
     *
     * @param treePathName the tree pash name
     * @author Nick Lv
     * @created 2017 /06/20 12:03:10
     */
    public void setTreePathName(String treePathName) {
        this.treePathName = treePathName;
    }

    @Override
    @Transient
    public Long getPrimaryKey() {
        return this.id;
    }
}
