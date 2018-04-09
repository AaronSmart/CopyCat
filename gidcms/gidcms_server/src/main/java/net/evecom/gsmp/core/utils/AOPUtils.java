/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 描述:spring aop统一管理日志
 *
 * @author Nick Lv
 * @created 2017 /02/17 11:48:48
 */
@Aspect
@Component
public class AOPUtils {

    /**
     * The V return message.
     */
    @Autowired
    private VReturnMessage vReturnMessage;
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(AOPUtils.class);

    /**
     * 监听controller中的方法，在各方法调用之前，先调用这个方法
     *
     * @author Tab You
     * @created 2017 /02/16 10:19:13 Before function.
     * @param joinPoint the join point
     * @throws Exception the exception
     */
  /*  @Before("execution(* net.evecom.gsmp.controller.*.*(..))")
    public void beforeFunction(JoinPoint joinPoint) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Object[] obj = joinPoint.getArgs();
        for (int i = 0; i < obj.length; i++) {
            Object arg = obj[i];
            if(arg != null) {
                stringBuilder.append(getFieldsValues(arg));
            }
        }
        logger.info("开始：" + joinPoint.getTarget().getClass().getSimpleName() + "类"
                + joinPoint.getSignature().getName() + "方法," + "参数为:" + stringBuilder.toString());
    }
*/

    /**
     * 描述 Gets fields value.
     *
     * @param obj the obj
     * @return the fields value
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /02/20 09:59:57
     */
    private String getFieldsValues(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field f : fields) {
            f.setAccessible(true);
            if (fields != null) {
                stringBuilder.append(f.getName() + " = " + f.get(obj) + "; ");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 监听controller中的方法，在各方法调用之后，调用这个方法
     *
     * @author Nick Lv
     * @created 2017 /02/16 10:19:13 After function.
     * @param joinPoint the join point
     */
    /*  @After("execution(* net.evecom.gsmp..controller.*.*(..))")
    public void afterFunction(JoinPoint joinPoint) {
        logger.info("结束：进入" + joinPoint.getTarget().getClass().getSimpleName() + "类"
                + joinPoint.getSignature().getName() + "方法");
    }*/

    /**
     * 监听controller中的方法，当有方法抛出异常的时候，调用该方法
     *
     * @param joinPoint the join point
     * @param ex        the ex
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /02/16 10:19:13 Function throw error.
     */
    @AfterThrowing(throwing = "ex", pointcut = "execution(* net.evecom.gsmp.gmsa.controller.*.*(..))")
    public void functionThrowError(JoinPoint joinPoint, Throwable ex) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Object[] obj = joinPoint.getArgs();
        for (int i = 0; i < obj.length; i++) {
            Object arg = obj[i];
            if (arg != null) {
                stringBuilder.append("参数").append(i + 1).append(":").append(arg);
                if (i != obj.length - 1) {
                    stringBuilder.append(",");
                }
            }
        }

        logger.error("出现异常：" + joinPoint.getTarget().getClass().getSimpleName() + "类"
                + joinPoint.getSignature().getName() + "方法," + "参数为:" + stringBuilder.toString(), ex);
    }


}
