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
 * 列表GridTable
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/26 10:08:53
 * @since Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GridTable {

    /**
     * 中文
     *
     * @author Nick Lv
     */
    String cnName() default "";

    /**
     * 属性名
     *
     * @author Nick Lv
     */
    String enName() default "";

    /**
     * 样式
     *
     * @author Nick Lv
     */
    String styleClass() default "";

    /**
     * 具体样式
     *
     * @author Nick Lv
     */
    String style() default "";

    /**
     * 排列顺序
     *
     * @author Nick Lv
     */
    String viewOrder() default "-1";

    /**
     * 是否日期
     *
     * @author Nick Lv
     */
    String isNotDate() default "0";
}
