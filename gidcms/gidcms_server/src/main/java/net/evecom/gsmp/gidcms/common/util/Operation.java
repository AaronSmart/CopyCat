/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

/**
 * 统一用户管理系统Model：权限信息
 *
 * @author Iverson Cai  
 * @created 2017 /05/31 16:03:57
 */
public class Operation {

    /**
     * 序号
     */
    private Long id;
    /**
     * 父亲节点
     */
    private Long parentId;
    /**
     * 资源名称
     */
    private String label;
    /**
     * 资源编码
     */
    private String name;
    /**
     * 资源URL
     */
    private String resUrl;
    /**
     * 图标地址
     */
    private String iconPath;
    /**
     * 操作类型
     */
    private Long optType;

    /**
     * 描述 Gets id.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets parent id.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @return the parent id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 描述 Sets parent id.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @param parentId the parent id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述 Gets label.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 描述 Sets label.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets res url.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:57  
     * @return the res url
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 描述 Sets res url.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:58  
     * @param resUrl the res url
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /**
     * 描述 Gets icon path.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:58  
     * @return the icon path
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * 描述 Sets icon path.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:58  
     * @param iconPath the icon path
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * 描述 Gets opt type.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:58  
     * @return the opt type
     */
    public Long getOptType() {
        return optType;
    }

    /**
     * 描述 Sets opt type.
     * @author Iverson Cai  
     * @created 2017 /05/31 16:03:58  
     * @param optType the opt type
     */
    public void setOptType(Long optType) {
        this.optType = optType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Operation that = (Operation) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null)
            return false;
        if (label != null ? !label.equals(that.label) : that.label != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (resUrl != null ? !resUrl.equals(that.resUrl) : that.resUrl != null)
            return false;
        if (iconPath != null ? !iconPath.equals(that.iconPath) : that.iconPath != null)
            return false;
        return optType != null ? optType.equals(that.optType) : that.optType == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (resUrl != null ? resUrl.hashCode() : 0);
        result = 31 * result + (iconPath != null ? iconPath.hashCode() : 0);
        result = 31 * result + (optType != null ? optType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Operation{" + "id=" + id + ", parentId=" + parentId + ", label='" + label + '\'' + ", name='" + name
                + '\'' + ", resUrl='" + resUrl + '\'' + ", iconPath='" + iconPath + '\'' + ", optType=" + optType
                + '}';
    }
}
