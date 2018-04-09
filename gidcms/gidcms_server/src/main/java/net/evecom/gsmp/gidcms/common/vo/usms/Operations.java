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
 * 操作
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /10/09 08:44:04
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class Operations {
    /**
     * id
     */
    private Long id;
    /**
     * 标签
     */
    private String label;
    /**
     * 操作类型
     */
    private Long optType;
    /**
     * 名字
     */
    private String name;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 路径
     */
    private String resUrl;


    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets label.
     *
     * @return the label
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * 描述 Sets label.
     *
     * @param label the label
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public String getName() {
        return this.name;
    }

    /**
     * 描述 Sets name.
     *
     * @param name the name
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets parent id.
     *
     * @return the parent id
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * 描述 Sets parent id.
     *
     * @param parentId the parent id
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述 Gets opt type.
     *
     * @return the opt type
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public Long getOptType() {
        return this.optType;
    }

    /**
     * 描述 Sets opt type.
     *
     * @param optType the opt type
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public void setOptType(Long optType) {
        this.optType = optType;
    }

    /**
     * 描述 Gets res url.
     *
     * @return the res url
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public String getResUrl() {
        return this.resUrl;
    }

    /**
     * 描述 Sets res url.
     *
     * @param resUrl the res url
     * @author Nick Lv
     * @created 2017 /10/09 08:44:04
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
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



