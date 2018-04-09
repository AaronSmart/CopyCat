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
 * 综合表单
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/26 10:09:42
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MgmtPage {
    /**
     * 中文名
     *
     * @author Nick Lv
     * @created 2017 /05/26 10:09:42 Cn name string.
     */
    String cnName() default "";

    /**
     * 前端查询条件对应的框的类型，1为文字输入框，2为日期选择框，3为下拉选择框
     *
     * @author Nick Lv
     * @created 2017 /05/26 10:09:42 Input type int.
     */
    int inputType() default -1;
}
