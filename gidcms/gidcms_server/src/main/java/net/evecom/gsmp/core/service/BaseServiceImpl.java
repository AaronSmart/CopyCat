/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.service;

import net.evecom.gsmp.core.annotation.GridData;
import net.evecom.gsmp.core.annotation.GridTableUtil;
import net.evecom.gsmp.core.persitence.BaseEntity;
import net.evecom.gsmp.core.persitence.BaseJPA;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基础service实现类
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 * @author Nick Lv
 * @created 2017 /7/4 15:14
 */
@Service
@Transactional
public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(BaseServiceImpl.class);
    /**
     * baseJpa类
     */
    @Autowired
    BaseJPA<T, ID> baseJPA;
    /**
     * 泛型类
     */
    Class<T> clazz;

    /**
     * 给this.clazz赋值
     */
    protected BaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }
    @Override
    public T saveEntity(T entity) {
        if (null == entity) {
            return null;
        }
        return baseJPA.save(entity);
    }

    @Override
    public List<T> saveEntities(List<T> entities) {
        return baseJPA.save(entities);
    }

    @Override
    public T updateEntity(T entity) {
        if (null == entity) {
            return null;
        }
        return baseJPA.save(entity);
    }

    @Override
    public List<T> updateEntities(List<T> entities) {
        return baseJPA.save(entities);
    }

    @Override
    public VReturnMessage deleteEntities(List<T> entities) {
        VReturnMessage vReturnMessage = new VReturnMessage();
        try {
            baseJPA.deleteInBatch(entities);
            vReturnMessage.setResult(true);
            vReturnMessage.setMessage("您已经成功删除" + entities.size() + "条数据！");
        } catch (Exception e) {
            logger.error("删除多个实体类异常", e);
            vReturnMessage.setResult(false);
            vReturnMessage.setMessage("删除出错，请重新操作或联系管理员。");
        }
        return vReturnMessage;
    }

    @Override
    public VReturnMessage deleteOneById(ID id) {
        VReturnMessage vReturnMessage = new VReturnMessage();
        try {
            baseJPA.delete(id);
            vReturnMessage.setResult(true);
            vReturnMessage.setMessage("您已经成功删除1条数据！");
        } catch (Exception e) {
            logger.error("通过主键删除信息出错！主键值：" + id, e);
            vReturnMessage.setResult(false);
            vReturnMessage.setMessage("删除出错，请重新操作或联系管理员。");
        }
        return vReturnMessage;
    }

    @Override
    public VReturnMessage logicDeleteOneById(ID id, String fieldName, String value) {
        VReturnMessage vReturnMessage = new VReturnMessage();
        vReturnMessage.setResult(true);
        T targetObject = baseJPA.findOne(id);
        if (null != targetObject) {
            try {
                targetObject.setFieldValue(fieldName, value);
            } catch (Exception e) {
                vReturnMessage.setResult(false);
                String errorMessage = String.format("逻辑删除对象方法中，赋值错误，字段名字为%s,字段值为%s", fieldName, value);
                vReturnMessage.setMessage(errorMessage);
                logger.error(errorMessage, e);
            }
        } else {
            vReturnMessage.setResult(false);
            vReturnMessage.setMessage("不存在的实体，id:" + id);
        }
        return vReturnMessage;
    }

    @Override
    public T getEntityById(ID id) {
        return baseJPA.findOne(id);
    }

    @Override
    public Page<T> getEntitiesByCondition(SimplePageRequestBody<T> body, ExampleMatcher matcher) {
        Pageable pageable = body.buildPageable();
        Example<T> example = Example.of(body.getCondition(), matcher);
        return baseJPA.findAll(example, pageable);
    }

    @Override
    public Page<T> getEntitiesByCondition(String baseSql, SimplePageRequestBody<T> body) {
        /*
         * String searchCondition = body.getSearchCondition(); String
         * searchTable = body.getEntityName(); StringBuilder sBuilder = new
         * StringBuilder(); sBuilder.append(baseSql); String[] conditions =
         * null; int identification = 0; int sqlValue = 1; if
         * (StringUtils.hasText(searchCondition)) { conditions =
         * searchCondition.split(",");
         * Arrays.asList(conditions).forEach(condition -> { String[]
         * entityFields = condition.split("_");
         * sBuilder.append(String.parse(SqlConditionEnum
         * .getSqlCondition(entityFields[identification]).getSqlSentence(),
         * entityFields[sqlValue]));
         * 
         * }); }
         */
        return null;
    }

    @Override
    public GridData<T> getEntitiesByCondition(SimplePageRequestBody<T> body) {
        Page<T> result = null;
        GridData<T> gridData = new GridData<>();
        try {
            result = baseJPA.findAll(body.buildCriteria(true), body.buildPageable());
            gridData.setValue(result);
            gridData.setColumn(GridTableUtil.createGridTable(this.clazz));
        } catch (Exception e) {
            logger.error(e);
        }
        return gridData;
    }
}
