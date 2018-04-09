/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程相关工具类.
 *
 * @author Nick Lv
 * @version 2013 -01-15
 * @created 2017 /03/01 10:46:43
 */
public class Threads {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Threads.class);

    /**
     * sleep等待,单位为毫秒,忽略InterruptedException.
     *
     * @param millis
     *            the millis
     * @author Nick Lv
     * @created 2017 /03/01 10:46:43 Sleep.
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Ignore.
            return;
        }
    }

    /**
     * sleep等待,忽略InterruptedException.
     *
     * @param duration
     *            the duration
     * @param unit
     *            the unit
     * @author Nick Lv
     * @created 2017 /03/01 10:46:43 Sleep.
     */
    public static void sleep(long duration, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(duration));
        } catch (InterruptedException e) {
            // Ignore.
            return;
        }
    }

    /**
     * 按照ExecutorService JavaDoc示例代码编写的Graceful Shutdown方法. 先使用shutdown,
     * 停止接收新任务并尝试完成所有已存在任务. 如果超时, 则调用shutdownNow,
     * 取消在workQueue中Pending的任务,并中断所有阻塞函数. 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     *
     * @param pool
     *            the pool
     * @param shutdownTimeout
     *            the shutdown timeout
     * @param shutdownNowTimeout
     *            the shutdown now timeout
     * @param timeUnit
     *            the time unit
     * @author Nick Lv
     * @created 2017 /03/01 10:46:43 Graceful shutdown.
     */
    public static void gracefulShutdown(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout,
            TimeUnit timeUnit) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(shutdownTimeout, timeUnit)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(shutdownNowTimeout, timeUnit)) {
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 直接调用shutdownNow的方法, 有timeout控制.取消在workQueue中Pending的任务,并中断所有阻塞函数.
     *
     * @param pool
     *            the pool
     * @param timeout
     *            the timeout
     * @param timeUnit
     *            the time unit
     * @author Nick Lv
     * @created 2017 /03/01 10:46:43 Normal shutdown.
     */
    public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
        try {
            pool.shutdownNow();
            if (!pool.awaitTermination(timeout, timeUnit)) {

            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}
