/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.entity;

import net.evecom.gsmp.core.persitence.DataEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 网格类型
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 14:29:56
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Entity
@Table(name = "GSMP_LOC_GRID_TYPES", schema = "GSMP", catalog = "")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class LocGridTypes extends DataEntity {

    /**
     * 网格类型ID.
     */
    private Long id;
    /**
     * 网格类型编码.
     */
    private String code;
    /**
     * 网格类型名称.
     */
    private String name;
    /**
     * 描述.
     */
    private String descripiton;

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /05/23 14:29:56
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
     * @author Nick Lv
     * @created 2017 /05/23 14:29:56
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets code.
     *
     * @return the code
     * @author Nick Lv
     * @created 2017 /05/23 14:29:56
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
     * @created 2017 /05/23 14:29:56
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /05/23 14:29:56
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
     * @created 2017 /05/23 14:29:56
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets descripiton.
     *
     * @return the descripiton
     * @author Nick Lv
     * @created 2017 /05/23 14:29:56
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
     * @created 2017 /05/23 14:29:56
     */
    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
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
