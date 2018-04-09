/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.gsmp.core.web;

import net.evecom.gsmp.core.persitence.handler.ConditionFilter;
import net.evecom.gsmp.core.persitence.handler.SortFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * 仅用于前端查询的参数包装实体类
 *
 * @param <T> the type parameter
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /07/06 09:27:46
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class SimplePageRequestBody<T> extends PageRequestBody<T>{


    /**
     * The Query params.
     * pattern like:
     * Field^Operator^Entity
     * Field^Operator
     * eg:
     * username^LK^User
     */
    private Map<String,Object> queryParams;
    /**
     * The Order params.
     * pattern like:
     * Field:Direction
     * eg:
     * username:desc
     */
    private Map<String,String> orderParams;
    /**
     * The Join params.
     */
    private Map<String,Object> joinParams;

    /**
     * bean查询条件.
     */
    private T condition;
    /**
     * 其他查询条件map.
     */
    private Map<String, Object> dataMap = new HashMap<String, Object>();


    /**
     * 描述： Gets query params.
     *
     * @return the query params
     * @author Nick Lv
     * @created 2017 /07/06 09:33:36
     */
    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    /**
     * 描述： Sets query params.
     *
     * @param queryParams the query params
     * @author Nick Lv
     * @created 2017 /07/06 09:33:39
     */
    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;

        //init condition filters
        if(queryParams != null &&queryParams.size()>0) {
            queryParams.forEach((param,value) -> {
                String[] fieldAndOp = param.split("\\^");
                this.addCondition(new ConditionFilter(fieldAndOp[0],value,fieldAndOp[1]));
            });
        }
    }

    /**
     * 描述： Gets order params.
     *
     * @return the order params
     * @author Nick Lv
     * @created 2017 /07/06 09:33:42
     */
    public Map<String, String> getOrderParams() {
        return orderParams;
    }

    /**
     * 描述： Sets order params.
     *
     * @param orderParams the order params
     * @author Nick Lv
     * @created 2017 /07/06 17:16:31
     */
    public void setOrderParams(Map<String, String> orderParams) {
        this.orderParams = orderParams;
        //init sort filters
        if(orderParams != null &&orderParams.size()>0) {
            orderParams.forEach((param,dir) -> {
                this.addSort(new SortFilter(param,dir));
            });
        }
    }

    /**
     * 描述： Gets join params.
     *
     * @return the join params
     * @author Nick Lv
     * @created 2017 /07/06 09:33:44
     */
    public Map<String, Object> getJoinParams() {
        return joinParams;
    }

    /**
     * 描述： Sets join params.
     *
     * @param joinParams the join params
     * @author Nick Lv
     * @created 2017 /07/06 09:33:47
     */
    public void setJoinParams(Map<String, Object> joinParams) {
        this.joinParams = joinParams;
    }


    /**
     * 描述： Gets condition.
     *
     * @return the condition
     * @author Nick Lv
     * @created 2017 /07/06 16:47:53
     */
    public T getCondition() {
        return condition;
    }

    /**
     * 描述： Sets condition.
     *
     * @param condition the condition
     * @author Nick Lv
     * @created 2017 /07/06 16:47:57
     */
    public void setCondition(T condition) {
        this.condition = condition;
    }

    /**
     * 描述： Gets data map.
     *
     * @return the data map
     * @author Nick Lv
     * @created 2017 /07/06 16:48:00
     */
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    /**
     * 描述： Sets data map.
     *
     * @param dataMap the data map
     * @author Nick Lv
     * @created 2017 /07/06 16:48:02
     */
    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}



