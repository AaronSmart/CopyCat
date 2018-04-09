/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 网格员树
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/08 20:45:35
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class VDistrictTree {
    /**
     * 名字.
     */
    private String label;
    /**
     * 对应的值
     */
    private String data;
    /**
     * 展开的图标.
     */
    private String expandedIcon;
    /**
     * 关闭的图标.
     */
    private String collapsedIcon;

    /**
     * 是否能被选中.
     */
    private boolean selectable = false;

    /**
     * 是否可以展开.
     */
    private boolean expanded = false;

    /**
     * 是否叶子节点.
     */
    private boolean leaf = false;

    /**
     * 区划层级.
     */
    private String level;

    /**
     * 区划code.
     */
    private String districtCode;

    /**
     *  区划全称.
     */
    private String namePath;

    /**
     * The Children.
     */
    List<VDistrictTree> children;

    /**
     * 描述 Gets label.
     *
     * @return the label
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public String getLabel() {
        return label;
    }

    /**
     * 描述 Sets label.
     *
     * @param label the label
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets data.
     *
     * @return the data
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public String getData() {
        return data;
    }

    /**
     * 描述 Sets data.
     *
     * @param data the data
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 描述 Gets expanded icon.
     *
     * @return the expanded icon
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public String getExpandedIcon() {
        return expandedIcon;
    }

    /**
     * 描述 Sets expanded icon.
     *
     * @param expandedIcon the expanded icon
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public void setExpandedIcon(String expandedIcon) {
        this.expandedIcon = expandedIcon;
    }

    /**
     * 描述 Gets collapsed icon.
     *
     * @return the collapsed icon
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public String getCollapsedIcon() {
        return collapsedIcon;
    }

    /**
     * 描述 Sets collapsed icon.
     *
     * @param collapsedIcon the collapsed icon
     * @author Nick Lv
     * @created 2017 /05/08 20:45:35
     */
    public void setCollapsedIcon(String collapsedIcon) {
        this.collapsedIcon = collapsedIcon;
    }

    /**
     * 描述 create.
     *
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /06/03 10:26:45 Is selectable boolean.
     */
    public boolean isSelectable() {
        return selectable;
    }
 /*   *
     * 描述 create.
     *
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /06/06 09:21:22 Is expanded boolean.*/

    public boolean isExpanded() {
        return expanded;
    }

   /* *
     * 描述 Sets expanded.
     *
     * @param expanded the expanded
     * @author Nick Lv
     * @created 2017 /06/06 09:21:22*/

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * 描述 create.
     *
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /06/06 09:21:22 Is leaf boolean.
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * 描述 Sets leaf.
     *
     * @param leaf the leaf
     * @author Nick Lv
     * @created 2017 /06/06 09:21:22
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * 描述 Sets level.
     *
     * @param level the level
     * @author Nick Lv
     * @created 2017 /06/06 09:21:22
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 描述 Gets level.
     *
     * @return the level
     * @author Nick Lv
     * @created 2017 /06/12 16:39:11
     */
    public String getLevel() {
        return level;
    }

    /**
     * 描述 Gets children.
     *
     * @return the children
     * @author Nick Lv
     * @created 2017 /06/12 16:39:11
     */
    public List<VDistrictTree> getChildren() {
        return children;
    }

    /**
     * 描述 Sets children.
     *
     * @param children the children
     * @author Nick Lv
     * @created 2017 /06/12 16:39:11
     */
    public void setChildren(List<VDistrictTree> children) {
        this.children = children;
    }

    /**
     * 描述 Gets district code.
     *
     * @return the district code
     * @author Nick Lv
     * @created 2017 /06/12 16:39:11
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * 描述 Sets district code.
     *
     * @param districtCode the district code
     * @author Nick Lv
     * @created 2017 /06/12 16:39:12
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * 描述 Gets name path.
     *
     * @return the name path
     * @author Nick Lv
     * @created 2017 /06/14 09:59:58
     */
    public String getNamePath() {
        return namePath;
    }

    /**
     * 描述 Sets name path.
     *
     * @param namePath the name path
     * @author Nick Lv
     * @created 2017 /06/14 09:59:58
     */
    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    /**
     * 描述 Sets selectable.
     *
     * @param selectable the selectable
     * @author Nick Lv
     * @created 2017 /06/03 10:26:45
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}



