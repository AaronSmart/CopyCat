/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/15.
 * @author Iverson Cai  
 * @created 2017 /08/15 23:38:45
 */
public class JSONUtils {

    /**
     * 单例objectMapper
     */
    private static ObjectMapper objectMapper = null;

    static {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    private JSONUtils() {

    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return objectMapper.readValue(jsonStr, Map.class);
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
        });
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
        });
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
