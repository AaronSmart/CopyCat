/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 码表实体类（一码一表）.
 *
 * @author Nick Lv
 * @created 2017 /7/6 20:30
 */
public class VCommCode {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 标签.
     */
    private String label;
    /**
     * 名称.
     */
    private String name;
    /**
     * 代码值.
     */
    private String codeValue;
    /**
     * comm_code_type代码类型表对应的主键id.（暂时无用）
     */
    private Long codeTypeId;
    /**
     * 父代码id.
     */
    private Long parentId;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets label.
     *
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets code value.
     *
     * @return the code value
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * Sets code value.
     *
     * @param codeValue the code value
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    /**
     * Gets code type id.
     *
     * @return the code type id
     */
    public Long getCodeTypeId() {
        return codeTypeId;
    }

    /**
     * Sets code type id.
     *
     * @param codeTypeId the code type id
     */
    public void setCodeTypeId(Long codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    /**
     * Gets parent id.
     *
     * @return the parent id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets parent id.
     *
     * @param parentId the parent id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
