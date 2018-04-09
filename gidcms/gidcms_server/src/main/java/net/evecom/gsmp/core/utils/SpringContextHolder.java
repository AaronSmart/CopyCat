/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 *
 * @author Nick Lv
 * @created 2017 /03/01 10:45:09
 * @date 2013 -5-29 下午1:25:40
 */
@Service
@Lazy(false)
@EnableJpaAuditing
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    /**
     * The constant applicationContext.
     */
    private static ApplicationContext applicationContext = null;

    /**
     * The constant logger.
     */
    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * 取得存储在静态变量中的ApplicationContext.
     *
     * @return the application context
     * @author Nick Lv
     * @created 2017 /03/01 10:45:09
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param <T>  the type parameter
     * @param name the name
     * @return the bean
     * @author Nick Lv
     * @created 2017 /03/01 10:45:09
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param <T>          the type parameter
     * @param requiredType the required type
     * @return the bean
     * @author Nick Lv
     * @created 2017 /03/01 10:45:09
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     *
     * @author Nick Lv
     * @created 2017 /03/01 10:45:09 Clear holder.
     */
    public static void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.info("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
        if (SpringContextHolder.applicationContext != null) {
            logger.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
                    + SpringContextHolder.applicationContext);
        }

        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     *
     * @author Nick Lv
     * @created 2017 /03/01 10:45:09 Assert context injected.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null,
                "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }
}