/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.annotation;

/**
 * 列表信息实体类
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/24 16:55:22
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class VGridTable {
    /**
     * 中文名.
     */
    private String cnName;
    /**
     * 属性名
     */
    private String enName;
    /**
     * 样式类
     */
    private String styleClass;
    /**
     * 具体样式.
     */
    private String style;
    /**
     * 排列顺序
     */
    private Integer viewOrder;
    /**
     * 是否日期
     */
    private Integer isNotDate;

    /**
     * 描述 Gets view order.
     *
     * @return the view order
     * @author  Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public Integer getViewOrder() {
        return viewOrder;
    }

    /**
     * 描述 Sets view order.
     *
     * @param viewOrder the view order
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    /**
     * 描述 Gets is not date.
     *
     * @return the is not date
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public Integer getIsNotDate() {
        return isNotDate;
    }

    /**
     * 描述 Sets is not date.
     *
     * @param isNotDate the is not date
     * @author Submarine Lin
     * @created 2017 /05/24 16:55:22
     */
    public void setIsNotDate(Integer isNotDate) {
        this.isNotDate = isNotDate;
    }

    /**
     * 描述 Gets cn name.
     *
     * @return the cn name
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 描述 Sets cn name.
     *
     * @param cnName the cn name
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * 描述 Gets en name.
     *
     * @return the en name
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 描述 Sets en name.
     *
     * @param enName the en name
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 描述 Gets style class.
     *
     * @return the style class
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * 描述 Sets style class.
     *
     * @param styleClass the style class
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * 描述 Gets style.
     *
     * @return the style
     * @author Nick Lv
     * @created 2017 /05/24 16:55:22
     */
    public String getStyle() {
        return style;
    }

    /**
     * 描述 Sets style.
     *
     * @param style the style
     * @author Nick Lv
     * @created 2017 /05/24 16:55:23
     */
    public void setStyle(String style) {
        this.style = style;
    }
}



