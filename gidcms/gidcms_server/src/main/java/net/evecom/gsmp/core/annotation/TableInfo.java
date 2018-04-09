/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，设置前端Dialog中Table的属性
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @since Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableInfo {
    /**
     * 中文名
     *
     * @author Nick Lv
     */
    String cnName() default "";

    /**
     * 放置位置
     *
     * @author Nick Lv
     */
    String align() default " align='left' ";

    /**
     * 所占列数
     *
     * @author Nick Lv
     */
    String colSpan() default " colspan='0' ";

    /**
     * 所占行数
     *
     * @author Nick Lv
     */
    String rowSpan() default " rowspan='0' ";

    /**
     * 行数
     *
     * @author Nick Lv
     */
    String rowIndex() default "-1";

    /**
     * 组数
     *
     * @author Nick Lv
     */
    String groupIndex() default "1";

    /**
     * 组名
     *
     * @author Nick Lv
     */
    String groupName() default "";

    /**
     * 日期格式
     *
     * @author Nick Lv
     */
    String dataFormat() default "yyyy年MM月dd日";

    /**
     * 是否图片
     *
     * @author Nick Lv
     */
    boolean isImage() default false;

    /**
     * 默认值
     *
     * @author Nick Lv
     */
    String defaultValue() default "";

    /**
     * th所占行数
     * @return
     */
    int thRowSpan() default 0;

    /**
     * th所占列数
     * @return
     */
    int thColSpan() default 0;

    /**
     * td所占行数
     * @return
     */
    int tdRowSpan() default 0;

    /**
     * td所占列数
     * @return
     */
    int tdColSpan() default 0;
}