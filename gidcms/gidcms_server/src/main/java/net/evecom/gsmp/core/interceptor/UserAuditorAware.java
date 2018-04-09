/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


/**
 * 实现@createdBy和@modifiedBy两个注解的监听实现类
 * 根据当前session中的单点登录用户信息填入实体类中的创建人和修改人字段的值
 *
 * @author Nick Lv
 * @created 2017 /07/06 17:37:05
 * @version --v1.0
 * @since Version 1.0
 * @see --添加类中引用的相关类和接口
 */
@Component
public class UserAuditorAware implements AuditorAware<Long>{

    /**
     * The constant LOGGER.
     */
    private static  final Logger LOGGER = LoggerFactory.getLogger(UserAuditorAware.class);

    /**
     * 获取当前系统用户信息
     * 审计
     * @return
     */
    @Override
    public Long getCurrentAuditor() {
        LOGGER.debug(" get the username of current session user.");

        //do something
        //get username from session or spring security context user info

        LOGGER.debug(" Returning username.");
        return new Long(1111111);
    }
}



