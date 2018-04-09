/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.vo.usms;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 机构信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /10/05 16:37:14
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class Institutions {
    /**
     * 行政区划
     */
    private String adminDivision;
    /**
     * 行政区划code
     */
    private String adminDivisionCode;
    /**
     * id
     */
    private Long id;
    /**
     * 标签
     */
    private String label;
    /**
     * 名字
     */
    private String name;
    /**
     * 组织code
     */
    private String orgCode;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 社会信用code
     */
    private String socialCreditUnicode;
    /**
     * 类型
     */
    private Long type;

    /**
     * 描述 Gets admin division.
     *
     * @return the admin division
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public String getAdminDivision() {
        return this.adminDivision;
    }

    /**
     * 描述 Sets admin division.
     *
     * @param adminDivision the admin division
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    /**
     * 描述 Gets admin division code.
     *
     * @return the admin division code
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public String getAdminDivisionCode() {
        return this.adminDivisionCode;
    }

    /**
     * 描述 Sets admin division code.
     *
     * @param adminDivisionCode the admin division code
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets label.
     *
     * @return the label
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * 描述 Sets label.
     *
     * @param label the label
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public String getName() {
        return this.name;
    }

    /**
     * 描述 Sets name.
     *
     * @param name the name
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets org code.
     *
     * @return the org code
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public String getOrgCode() {
        return this.orgCode;
    }

    /**
     * 描述 Sets org code.
     *
     * @param orgCode the org code
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 描述 Gets parent id.
     *
     * @return the parent id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public Long getParentId() {
        return this.parentId;
    }

    /**
     * 描述 Sets parent id.
     *
     * @param parentId the parent id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述 Gets social credit unicode.
     *
     * @return the social credit unicode
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public String getSocialCreditUnicode() {
        return this.socialCreditUnicode;
    }

    /**
     * 描述 Sets social credit unicode.
     *
     * @param socialCreditUnicode the social credit unicode
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setSocialCreditUnicode(String socialCreditUnicode) {
        this.socialCreditUnicode = socialCreditUnicode;
    }

    /**
     * 描述 Gets type.
     *
     * @return the type
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public Long getType() {
        return this.type;
    }

    /**
     * 描述 Sets type.
     *
     * @param type the type
     * @author Nick Lv
     * @created 2017 /10/09 08:43:14
     */
    public void setType(Long type) {
        this.type = type;
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



