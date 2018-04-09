/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 关于异常的工具类.
 *
 * @author Nick Lv
 * @version 2013 -01-15
 * @created 2017 /03/01 10:42:20
 */
public class Exceptions {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Exceptions.class);

    /**
     * 将CheckedException转换为UncheckedException.
     *
     * @param e the e
     * @return the runtime exception
     * @author Nick Lv
     * @created 2017 /03/01 10:42:20 Unchecked runtime exception.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String.
     *
     * @param e the e
     * @return the stack trace as string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:20
     */
    public static String getStackTraceAsString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        LOGGER.error("",new PrintWriter(stringWriter),e);
        return stringWriter.toString();
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     *
     * @param ex                    the ex
     * @param causeExceptionClasses the cause exception classes
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /03/01 10:42:20 Is caused by boolean.
     */
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }

    /**
     * 在request中获取异常类
     *
     * @param request the request
     * @return throwable
     * @author Nick Lv
     * @created 2017 /03/01 10:42:21
     */
    public static Throwable getThrowable(HttpServletRequest request) {
        Throwable ex = null;
        if (request.getAttribute("exception") != null) {
            ex = (Throwable) request.getAttribute("exception");
        } else if (request.getAttribute("javax.servlet.error.exception") != null) {
            ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
        return ex;
    }

}
