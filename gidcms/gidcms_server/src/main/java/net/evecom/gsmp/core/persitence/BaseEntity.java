/*
 * Copyright (c) 2005,2017, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.gsmp.core.persitence;

import net.evecom.gsmp.core.utils.Reflections;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <b>基础实体类</b>
 * <p>
 * 对实体类型公用的方法和操作提炼到这里，所有实体类必须继承此类
 * </p>
 *
 * @author Nick Lv
 * @created 2017 /07/06 17:38:25
 * @version --V1.0
 * @since Version 1.0
 */
@MappedSuperclass
public abstract class BaseEntity {

    // 使用序列故无法提成通用，如果使用普通id自增方案可共用
    // @Id
    // @Column(name = "ID", nullable = false, precision = 0)
    // @SequenceGenerator(name = "my_sequence", sequenceName =
    // "gsmp.GSMP_LOC_ADDRESSES_S", allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "my_sequence")
    // @Getter@Setter private Long id;

    /**
     * 获得实体对应的表名
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:21:11
     * @return the table name
     */
    @Transient
    public String getTableName() {
        Table table = this.getClass().getAnnotation(Table.class);
        return table != null ? table.name() : null;
    }

    /**
     * 设置字段值
     *
     * @author Nick Lv
     * @created 2017 /07/06 12:12:54
     * @param filedName
     *            the filed name
     * @param value
     *            the value
     */
    @Transient
    public void setFieldValue(String filedName, Object value) {
        Reflections.invokeSetter(this, filedName, value);
    }

    /**
     * 获取字段值
     *
     * @author Nick Lv
     * @created 2017 /07/06 13:02:30
     * @param field
     *            the field
     * @return the field value
     */
    @Transient
    public Object getFieldValue(String field) {
        return Reflections.invokeGetter(this, field);
    }

    /**
     * 获取主键值 需要各实体类自行实现
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:21:23
     * @return 主键值 primary key
     */
    @Transient
    public abstract Object getPrimaryKey();

    /**
     * 比较主键值是否相同
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:21:29 Equals pk boolean.
     * @param obj
     *            the obj
     * @return boolean boolean
     */
    @Transient
    public boolean equalsPK(Object obj) {
        if (obj == null)// 对象为空不比较
            return false;
        // 类型不同不必进行比较
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        // 不是BaseEO，不必比较
        if (!(obj instanceof BaseEntity)) {
            return false;
        }

        BaseEntity eo = (BaseEntity) obj;

        if (getPrimaryKey() != null && eo.getPrimaryKey() != null) {
            if (getPrimaryKey().equals(eo.getPrimaryKey())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 重写拷贝 深拷贝
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:21:29
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = null;
        // 浅拷贝
        // try {
        // obj = BeanUtils.cloneBean(this);
        // } catch (IllegalAccessException e) {
        // e.printStackTrace();
        // } catch (InstantiationException e) {
        // e.printStackTrace();
        // } catch (InvocationTargetException e) {
        // e.printStackTrace();
        // } catch (NoSuchMethodException e) {
        // e.printStackTrace();
        // }
        // 深拷贝
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = (oi.readObject());
            bo.close();
            oo.close();
            bi.close();
            oi.close();
        } catch (Exception e) {
            throw new CloneNotSupportedException(e.getMessage());
        }
        return obj;
    }

    /**
     * 描述 override toString.
     *
     * @author Nick Lv
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 描述 override equals.
     *
     * @author Nick Lv
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * 描述 override hashCode.
     *
     * @author Nick Lv
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
