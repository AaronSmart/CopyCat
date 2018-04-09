/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.entity;

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
 * 区划介绍
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/24 16:48:34
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Entity
@Table(name = "GSMP_LOC_DISTRICT_DETAILS", schema = "GSMP", catalog = "")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class LocDistrictDetails  extends DataEntity {

    /**
     * 行政区划ID
     */
    private Long id;
    /**
     * 毗邻的相关政区s.
     */
    private String adjacentRegions;
    /**
     * 总面积（平方千米）
     */
    @TableInfo(cnName = "总面积（平方千米）：",rowIndex = "1",groupName = "区划介绍")
    private Long totalArea;
    /**
     * 电话区号
     */
    @TableInfo(cnName = "电话区号：",rowIndex = "1",groupName = "区划介绍")
    private String areaCode;
    /**
     * 邮政编码
     */
    @TableInfo(cnName = "邮政编码：",rowIndex = "1",groupName = "区划介绍")
    private String zipCode;
    /**
     * 总人口(约)
     */
    @TableInfo(cnName = "总人口(约)：",rowIndex = "2",groupName = "区划介绍")
    private Long totalPopulation;
    /**
     * 其中政府驻地人口.
     */
    private Long govResidentPop;
    /**
     * 人均收入
     */
    @TableInfo(cnName = "人均收入：",rowIndex = "2",groupName = "区划介绍")
    private Long perCapitaIncome;
    /**
     * 民族构成
     */
    private String ethnicComposition;
    /**
     * 政府网址
     */
    @TableInfo(cnName = "政府网址：",rowIndex = "2",groupName = "区划介绍")
    private String govWetsite;
    /**
     * 气候类型
     */
    @TableInfo(cnName = "气候类型：",rowIndex = "3",groupName = "区划介绍")
    private String climate;
    /**
     * 地貌特牲
     */
    @TableInfo(cnName = "地貌特牲：",rowIndex = "3",groupName = "区划介绍")
    private String geographicalFeature;
    /**
     * 主要山峰
     */
    @TableInfo(cnName = "主要山峰：",rowIndex = "3",groupName = "区划介绍")
    private String majorPeaks;
    /**
     * 主要河流
     */
    @TableInfo(cnName = "主要河流：",rowIndex = "4",groupName = "区划介绍")
    private String majorRivers;
    /**
     * 境内主要矿产资源
     */
    @TableInfo(cnName = "境内主要矿产资源：",rowIndex = "4",groupName = "区划介绍")
    private String mainMineralResources;
    /**
     * 主要工业
     */
    @TableInfo(cnName = "主要工业：",rowIndex = "4",groupName = "区划介绍")
    private String majorIndustries;
    /**
     * 主要农业
     */
    @TableInfo(cnName = "主要农业：",rowIndex = "5",groupName = "区划介绍")
    private String majorAgricultures;
    /**
     * 主要交通干道
     */
    @TableInfo(cnName = "主要交通干道：",rowIndex = "5",groupName = "区划介绍")
    private String mainRoads;
    /**
     * 主要文教科体卫事业单位
     */
    @TableInfo(cnName = "主要文教科体卫事业单位：",rowIndex = "5",groupName = "区划介绍")
    private String mainCesshInstitutions;
    /**
     * 著名名胜古迹和旅游景点
     */
    @TableInfo(cnName = "著名名胜古迹和旅游景点：",rowIndex = "6",groupName = "区划介绍")
    private String famousSsHs;
    /**
     * 著名历史人物
     */
    @TableInfo(cnName = "著名历史人物：",rowIndex = "6",groupName = "区划介绍")
    private String famousHistoricalFigures;
    /**
     * 主要特产
     */
    @TableInfo(cnName = "主要特产：",rowIndex = "6",groupName = "区划介绍")
    private String mainSpecialties;

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
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
     * @created 2017 /05/24 16:48:34
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets adjacent regions.
     *
     * @return the adjacent regions
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    @Basic
    @Column(name = "ADJACENT_REGIONS", nullable = true, length = 256)
    public String getAdjacentRegions() {
        return adjacentRegions;
    }

    /**
     * 描述 Sets adjacent regions.
     *
     * @param adjacentRegions the adjacent regions
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    public void setAdjacentRegions(String adjacentRegions) {
        this.adjacentRegions = adjacentRegions;
    }

    /**
     * 描述 Gets total area.
     *
     * @return the total area
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    @Basic
    @Column(name = "TOTAL_AREA", nullable = true, precision = 2)
    public Long getTotalArea() {
        return totalArea;
    }

    /**
     * 描述 Sets total area.
     *
     * @param totalArea the total area
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    public void setTotalArea(Long totalArea) {
        this.totalArea = totalArea;
    }

    /**
     * 描述 Gets area code.
     *
     * @return the area code
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    @Basic
    @Column(name = "AREA_CODE", nullable = true, length = 18)
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 描述 Sets area code.
     *
     * @param areaCode the area code
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 描述 Gets zip code.
     *
     * @return the zip code
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    @Basic
    @Column(name = "ZIP_CODE", nullable = true, length = 18)
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 描述 Sets zip code.
     *
     * @param zipCode the zip code
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 描述 Gets total population.
     *
     * @return the total population
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    @Basic
    @Column(name = "TOTAL_POPULATION", nullable = true, precision = 0)
    public Long getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * 描述 Sets total population.
     *
     * @param totalPopulation the total population
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    public void setTotalPopulation(Long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    /**
     * 描述 Gets gov resident pop.
     *
     * @return the gov resident pop
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:34
     */
    @Basic
    @Column(name = "GOV_RESIDENT_POP", nullable = true, precision = 0)
    public Long getGovResidentPop() {
        return govResidentPop;
    }

    /**
     * 描述 Sets gov resident pop.
     *
     * @param govResidentPop the gov resident pop
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setGovResidentPop(Long govResidentPop) {
        this.govResidentPop = govResidentPop;
    }

    /**
     * 描述 Gets per capita income.
     *
     * @return the per capita income
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "PER_CAPITA_INCOME", nullable = true, precision = 3)
    public Long getPerCapitaIncome() {
        return perCapitaIncome;
    }

    /**
     * 描述 Sets per capita income.
     *
     * @param perCapitaIncome the per capita income
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setPerCapitaIncome(Long perCapitaIncome) {
        this.perCapitaIncome = perCapitaIncome;
    }

    /**
     * 描述 Gets ethnic composition.
     *
     * @return the ethnic composition
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "ETHNIC_COMPOSITION", nullable = true, length = 256)
    public String getEthnicComposition() {
        return ethnicComposition;
    }

    /**
     * 描述 Sets ethnic composition.
     *
     * @param ethnicComposition the ethnic composition
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setEthnicComposition(String ethnicComposition) {
        this.ethnicComposition = ethnicComposition;
    }

    /**
     * 描述 Gets gov wetsite.
     *
     * @return the gov wetsite
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "GOV_WETSITE", nullable = true, length = 256)
    public String getGovWetsite() {
        return govWetsite;
    }

    /**
     * 描述 Sets gov wetsite.
     *
     * @param govWetsite the gov wetsite
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setGovWetsite(String govWetsite) {
        this.govWetsite = govWetsite;
    }

    /**
     * 描述 Gets climate.
     *
     * @return the climate
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "CLIMATE", nullable = true, length = 256)
    public String getClimate() {
        return climate;
    }

    /**
     * 描述 Sets climate.
     *
     * @param climate the climate
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setClimate(String climate) {
        this.climate = climate;
    }

    /**
     * 描述 Gets geographical feature.
     *
     * @return the geographical feature
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "GEOGRAPHICAL_FEATURE", nullable = true, length = 256)
    public String getGeographicalFeature() {
        return geographicalFeature;
    }

    /**
     * 描述 Sets geographical feature.
     *
     * @param geographicalFeature the geographical feature
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setGeographicalFeature(String geographicalFeature) {
        this.geographicalFeature = geographicalFeature;
    }

    /**
     * 描述 Gets major peaks.
     *
     * @return the major peaks
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "MAJOR_PEAKS", nullable = true, length = 256)
    public String getMajorPeaks() {
        return majorPeaks;
    }

    /**
     * 描述 Sets major peaks.
     *
     * @param majorPeaks the major peaks
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setMajorPeaks(String majorPeaks) {
        this.majorPeaks = majorPeaks;
    }

    /**
     * 描述 Gets major rivers.
     *
     * @return the major rivers
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "MAJOR_RIVERS", nullable = true, length = 256)
    public String getMajorRivers() {
        return majorRivers;
    }

    /**
     * 描述 Sets major rivers.
     *
     * @param majorRivers the major rivers
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setMajorRivers(String majorRivers) {
        this.majorRivers = majorRivers;
    }

    /**
     * 描述 Gets main mineral resources.
     *
     * @return the main mineral resources
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "MAIN_MINERAL_RESOURCES", nullable = true, length = 256)
    public String getMainMineralResources() {
        return mainMineralResources;
    }

    /**
     * 描述 Sets main mineral resources.
     *
     * @param mainMineralResources the main mineral resources
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setMainMineralResources(String mainMineralResources) {
        this.mainMineralResources = mainMineralResources;
    }

    /**
     * 描述 Gets major industries.
     *
     * @return the major industries
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "MAJOR_INDUSTRIES", nullable = true, length = 256)
    public String getMajorIndustries() {
        return majorIndustries;
    }

    /**
     * 描述 Sets major industries.
     *
     * @param majorIndustries the major industries
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setMajorIndustries(String majorIndustries) {
        this.majorIndustries = majorIndustries;
    }

    /**
     * 描述 Gets major agricultures.
     *
     * @return the major agricultures
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "MAJOR_AGRICULTURES", nullable = true, length = 256)
    public String getMajorAgricultures() {
        return majorAgricultures;
    }

    /**
     * 描述 Sets major agricultures.
     *
     * @param majorAgricultures the major agricultures
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setMajorAgricultures(String majorAgricultures) {
        this.majorAgricultures = majorAgricultures;
    }

    /**
     * 描述 Gets main roads.
     *
     * @return the main roads
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "MAIN_ROADS", nullable = true, length = 256)
    public String getMainRoads() {
        return mainRoads;
    }

    /**
     * 描述 Sets main roads.
     *
     * @param mainRoads the main roads
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setMainRoads(String mainRoads) {
        this.mainRoads = mainRoads;
    }

    /**
     * 描述 Gets main cessh institutions.
     *
     * @return the main cessh institutions
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "MAIN_CESSH_INSTITUTIONS", nullable = true, length = 256)
    public String getMainCesshInstitutions() {
        return mainCesshInstitutions;
    }

    /**
     * 描述 Sets main cessh institutions.
     *
     * @param mainCesshInstitutions the main cessh institutions
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setMainCesshInstitutions(String mainCesshInstitutions) {
        this.mainCesshInstitutions = mainCesshInstitutions;
    }

    /**
     * 描述 Gets famous ss hs.
     *
     * @return the famous ss hs
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "FAMOUS_SS_HS", nullable = true, length = 256)
    public String getFamousSsHs() {
        return famousSsHs;
    }

    /**
     * 描述 Sets famous ss hs.
     *
     * @param famousSsHs the famous ss hs
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    public void setFamousSsHs(String famousSsHs) {
        this.famousSsHs = famousSsHs;
    }

    /**
     * 描述 Gets famous historical figures.
     *
     * @return the famous historical figures
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:35
     */
    @Basic
    @Column(name = "FAMOUS_HISTORICAL_FIGURES", nullable = true, length = 256)
    public String getFamousHistoricalFigures() {
        return famousHistoricalFigures;
    }

    /**
     * 描述 Sets famous historical figures.
     *
     * @param famousHistoricalFigures the famous historical figures
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:36
     */
    public void setFamousHistoricalFigures(String famousHistoricalFigures) {
        this.famousHistoricalFigures = famousHistoricalFigures;
    }

    /**
     * 描述 Gets main specialties.
     *
     * @return the main specialties
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:36
     */
    @Basic
    @Column(name = "MAIN_SPECIALTIES", nullable = true, length = 256)
    public String getMainSpecialties() {
        return mainSpecialties;
    }

    /**
     * 描述 Sets main specialties.
     *
     * @param mainSpecialties the main specialties
     * @author Submarine Lin
     * @created 2017 /05/24 16:48:36
     */
    public void setMainSpecialties(String mainSpecialties) {
        this.mainSpecialties = mainSpecialties;
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



