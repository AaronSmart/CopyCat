/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache工具类
 *
 * @author Nick Lv
 * @version 2013 -5-29
 * @created 2017 /03/01 10:41:48
 */
public class EhCacheUtils {

    /**
     * The constant cacheManager.
     */
    private static CacheManager cacheManager = ((CacheManager) SpringContextHolder.getBean("cacheManager"));

    /**
     * The constant SYS_CACHE.
     */
    private static final String SYS_CACHE = "sysCache";

    /**
     * 获取SYS_CACHE缓存
     *
     * @param key the key
     * @return object
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48 Get object.
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 写入SYS_CACHE缓存
     *
     * @param key   the key
     * @param value the value
     * @return
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48 Put.
     */
    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     *
     * @param key the key
     * @return
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48 Remove.
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName the cache name
     * @param key       the key
     * @return object
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48 Get object.
     */
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 写入缓存
     *
     * @param cacheName the cache name
     * @param key       the key
     * @param value     the value
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48 Put.
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName the cache name
     * @param key       the key
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48 Remove.
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 获得一个Cache，没有则创建一个。
     *
     * @param cacheName the cache name
     * @return cache
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48
     */
    private static Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }

    /**
     * 描述 Gets cache manager.
     *
     * @return the cache manager
     * @author Nick Lv
     * @created 2017 /03/01 10:41:48
     */
    public static CacheManager getCacheManager() {
        return cacheManager;
    }

}
