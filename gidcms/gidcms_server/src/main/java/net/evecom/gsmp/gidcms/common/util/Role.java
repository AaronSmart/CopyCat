/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

/**
 * 统一用户管理系统Model：角色信息
 * @author Iverson Cai   
 * @created 2017 /08/28 11:25:01
 */
public class Role {
    /**
     * The Id.
     */
    private String id;
    /**
     * 角色名称中文
     */
    private String label;
    /**
     * 角色名称英文
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

    /**
     * 描述 Gets id.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:18  
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:18  
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 描述 Gets label.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:18  
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 描述 Sets label.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:18  
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:18  
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:18  
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets remark.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:19  
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 描述 Sets remark.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:25:19  
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null)
            return false;
        if (label != null ? !label.equals(role.label) : role.label != null)
            return false;
        if (name != null ? !name.equals(role.name) : role.name != null)
            return false;
        return remark != null ? remark.equals(role.remark) : role.remark == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" + "id='" + id + '\'' + ", label='" + label + '\'' + ", name='" + name + '\'' + ", remark='"
                + remark + '\'' + '}';
    }
}
