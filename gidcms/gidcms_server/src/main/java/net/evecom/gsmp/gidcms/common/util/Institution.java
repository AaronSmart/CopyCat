/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

/**
 * 描述 组织机构视图对象
 *
 * @author Iverson Cai 
 * @created 2017 /05/31 16:03:48
 */
public class Institution {

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
     * 父机构ID
     */
    private Long parentId;
    /**
     * 类型 1:机构 2:部门
     */
    private Long type;
    /**
     * 行政区划代码
     */
    private String adminDivisionCode;
    /**
     * 行政区划
     */
    private String adminDivision;
    /**
     * 社会信用统一代码
     */
    private String socialCreditUnicode;
    /**
     * 组织机构代码
     */
    private String orgCode;

    /**
     * 描述 Gets id.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets label.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 描述 Sets label.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets parent id.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @return the parent id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 描述 Sets parent id.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @param parentId the parent id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述 Gets type.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @return the type
     */
    public Long getType() {
        return type;
    }

    /**
     * 描述 Sets type.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @param type the type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * 描述 Gets admin division code.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:48 
     * @return the admin division code
     */
    public String getAdminDivisionCode() {
        return adminDivisionCode;
    }

    /**
     * 描述 Sets admin division code.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:49 
     * @param adminDivisionCode the admin division code
     */
    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    /**
     * 描述 Gets admin division.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:49 
     * @return the admin division
     */
    public String getAdminDivision() {
        return adminDivision;
    }

    /**
     * 描述 Sets admin division.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:49 
     * @param adminDivision the admin division
     */
    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    /**
     * 描述 Gets social credit unicode.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:49 
     * @return the social credit unicode
     */
    public String getSocialCreditUnicode() {
        return socialCreditUnicode;
    }

    /**
     * 描述 Sets social credit unicode.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:49 
     * @param socialCreditUnicode the social credit unicode
     */
    public void setSocialCreditUnicode(String socialCreditUnicode) {
        this.socialCreditUnicode = socialCreditUnicode;
    }

    /**
     * 描述 Gets org code.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:49 
     * @return the org code
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 描述 Sets org code.
     * @author Iverson Cai 
     * @created 2017 /05/31 16:03:49 
     * @param orgCode the org code
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Institution that = (Institution) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (label != null ? !label.equals(that.label) : that.label != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null)
            return false;
        if (adminDivisionCode != null ? !adminDivisionCode.equals(that.adminDivisionCode)
                : that.adminDivisionCode != null)
            return false;
        if (adminDivision != null ? !adminDivision.equals(that.adminDivision) : that.adminDivision != null)
            return false;
        if (socialCreditUnicode != null ? !socialCreditUnicode.equals(that.socialCreditUnicode)
                : that.socialCreditUnicode != null)
            return false;
        return orgCode != null ? orgCode.equals(that.orgCode) : that.orgCode == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (adminDivisionCode != null ? adminDivisionCode.hashCode() : 0);
        result = 31 * result + (adminDivision != null ? adminDivision.hashCode() : 0);
        result = 31 * result + (socialCreditUnicode != null ? socialCreditUnicode.hashCode() : 0);
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Institution{" + "id=" + id + ", label='" + label + '\'' + ", name='" + name + '\'' + ", parentId="
                + parentId + ", type=" + type + ", adminDivisionCode='" + adminDivisionCode + '\''
                + ", adminDivision='" + adminDivision + '\'' + ", socialCreditUnicode='" + socialCreditUnicode + '\''
                + ", orgCode='" + orgCode + '\'' + '}';
    }
}
