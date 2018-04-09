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
 * 操作信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /06/01 10:31:02
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class VOperations {

    /**
     * 操作id
     */
    private Long id;
    /**
     * 操作名称中文
     */
    private String label;
    /**
     * 操作名称英文
     */
    private String name;
    /**
     * 手动排序
     */
    private Long manualSn;
    /**
     * 父亲id
     */
    private Long parentId;
    /**
     * 树形层级
     */
    private Long treeLevel;
    /**
     * 树形路径
     */
    private String path;
    /**
     * 操作URL
     */
    private String resUrl;
    /**
     * 操作图标路径
     */
    private String iconPath;
    /**
     * 操作类型 1:菜单 2:按钮
     */
    private long optType;
    /**
     * 应用ID
     */
    private Long applicationId;

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets label.
     *
     * @return the label
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public String getLabel() {
        return label;
    }

    /**
     * 描述 Sets label.
     *
     * @param label the label
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     *
     * @param name the name
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets manual sn.
     *
     * @return the manual sn
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public Long getManualSn() {
        return manualSn;
    }

    /**
     * 描述 Sets manual sn.
     *
     * @param manualSn the manual sn
     * @author Nick Lv
     * @created 2017 /07/31 17:47:45
     */
    public void setManualSn(Long manualSn) {
        this.manualSn = manualSn;
    }

    /**
     * 描述 Gets parent id.
     *
     * @return the parent id
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 描述 Sets parent id.
     *
     * @param parentId the parent id
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 描述 Gets tree level.
     *
     * @return the tree level
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public Long getTreeLevel() {
        return treeLevel;
    }

    /**
     * 描述 Sets tree level.
     *
     * @param treeLevel the tree level
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    /**
     * 描述 Gets path.
     *
     * @return the path
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public String getPath() {
        return path;
    }

    /**
     * 描述 Sets path.
     *
     * @param path the path
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 描述 Gets res url.
     *
     * @return the res url
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 描述 Sets res url.
     *
     * @param resUrl the res url
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /**
     * 描述 Gets icon path.
     *
     * @return the icon path
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * 描述 Sets icon path.
     *
     * @param iconPath the icon path
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * 描述 Gets opt type.
     *
     * @return the opt type
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public long getOptType() {
        return optType;
    }

    /**
     * 描述 Sets opt type.
     *
     * @param optType the opt type
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public void setOptType(long optType) {
        this.optType = optType;
    }

    /**
     * 描述 Gets application id.
     *
     * @return the application id
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public Long getApplicationId() {
        return applicationId;
    }

    /**
     * 描述 Sets application id.
     *
     * @param applicationId the application id
     * @author Nick Lv
     * @created 2017 /07/31 17:47:46
     */
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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



