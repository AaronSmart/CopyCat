/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 树信息，用于前端页面展示。.
 * @author Iverson Cai      
 * @created 2017 /05/12 09:16:44
 */
public class Tree {
    /**
     * 网格ID.
     */
    private Object data;
    /**
     * 展示的名称，这边相当于网格名字.
     */
    private String label;
    /**
     * 是否叶子节点.
     */
    private String leaf;
    /**
     * 树节点未展开时的图标.
     */
    private String collapsedIcon = "fa-folder";
    /**
     * 树展开的图标.
     */
    private String expandedIcon = "fa-folder-open";
    /**
     * 叶子节点的图标.
     */
    private String icon = "fa-file-o";
    /**
     * 下级数据.
     */
    private List<Tree> children = new ArrayList<>();

    /**
     * 描述 Gets data.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * 描述 Sets data.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 描述 Gets label.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 描述 Sets label.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets leaf.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @return the leaf
     */
    public String getLeaf() {
        return leaf;
    }

    /**
     * 描述 Sets leaf.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @param leaf the leaf
     */
    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    /**
     * 描述 Gets collapsed icon.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @return the collapsed icon
     */
    public String getCollapsedIcon() {
        return collapsedIcon;
    }

    /**
     * 描述 Sets collapsed icon.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @param collapsedIcon the collapsed icon
     */
    public void setCollapsedIcon(String collapsedIcon) {
        this.collapsedIcon = collapsedIcon;
    }

    /**
     * 描述 Gets expanded icon.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:01 
     * @return the expanded icon
     */
    public String getExpandedIcon() {
        return expandedIcon;
    }

    /**
     * 描述 Sets expanded icon.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:02 
     * @param expandedIcon the expanded icon
     */
    public void setExpandedIcon(String expandedIcon) {
        this.expandedIcon = expandedIcon;
    }

    /**
     * 描述 Gets icon.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:02 
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 描述 Sets icon.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:02 
     * @param icon the icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 描述 Gets children.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:02 
     * @return the children
     */
    public List<Tree> getChildren() {
        return children;
    }

    /**
     * 描述 Sets children.
     * @author Iverson Cai 
     * @created 2017 /06/30 09:24:02 
     * @param children the children
     */
    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Tree{" + "data=" + data + ", label='" + label + '\'' + ", leaf='" + leaf + '\''
                + ", collapsedIcon='" + collapsedIcon + '\'' + ", expandedIcon='" + expandedIcon + '\'' + ", icon='"
                + icon + '\'' + ", children=" + children + '}';
    }
}
