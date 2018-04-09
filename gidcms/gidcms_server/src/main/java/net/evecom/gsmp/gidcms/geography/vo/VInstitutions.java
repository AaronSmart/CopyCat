/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 组织机构信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /06/01 10:31:03
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class VInstitutions {
    /**
     * 机构ID
     */
    private Long id;
    /**
     * 中文名称
     */
    private String label;
    /**
     * 英文名称
     */
    private String name;
    /**
     * 树形等级
     */
    private Long treeLevel;
    /**
     * 父机构ID
     */
    private Long parentId;
    /**
     * 树形路径
     */
    private String path;
    /**
     * 类型 1:机构 2:部门
     */
    private Long type;
    /**
     * 手动排序
     */
    private Long manualSn;
    /**
     * 行政区划代码
     */
    private String adminDivisionCode;
    /**
     * 行政区划
     */
    private String adminDivision;
    /**
     * 可用状态 1:正常 0:冻结
     */
    private Long enabled;
    /**
     * 社会信用统一代码
     */
    private String socialCreditUnicode;
    /**
     * 组织机构代码
     */
    private String orgCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getManualSn() {
        return manualSn;
    }

    public void setManualSn(Long manualSn) {
        this.manualSn = manualSn;
    }

    public String getAdminDivisionCode() {
        return adminDivisionCode;
    }

    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    public String getAdminDivision() {
        return adminDivision;
    }

    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getSocialCreditUnicode() {
        return socialCreditUnicode;
    }

    public void setSocialCreditUnicode(String socialCreditUnicode) {
        this.socialCreditUnicode = socialCreditUnicode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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



