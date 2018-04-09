/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.service;

import net.evecom.gsmp.core.annotation.GridData;
import net.evecom.gsmp.core.persitence.BaseEntity;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 基础service
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 * @author Nick Lv
 * @created 2017 /7/4 15:14
 */
public interface BaseService<T extends BaseEntity, ID extends Serializable> {
    /**
     * 新增实体类
     *
     * @param entity the entity
     * @return t t
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02 Save entity t.
     */
    T saveEntity(T entity);

    /**
     * 批量保存实体类
     *
     * @param entities the entities
     * @return list list
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02 Save entities list.
     */
    List<T> saveEntities(List<T> entities);

    /**
     * 修改实体类
     *
     * @param entity the entity
     * @return t t
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02 Update entity t.
     */
    T updateEntity(T entity);

    /**
     * 批量修改实体类
     *
     * @param entities the entities
     * @return the list
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02 Update entities list.
     */
    List<T> updateEntities(List<T> entities);

    /**
     * 根据集合删除实体
     *
     * @param entities the entities
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02 Delete entities v return message.
     */
    VReturnMessage deleteEntities(List<T> entities);

    /**
     * 根据id删除实体
     *
     * @param id the id
     * @return v return message
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02 Delete one by id v return message.
     */
    VReturnMessage deleteOneById(ID id);

    /**
     * 逻辑删除当前对象
     *
     * @param id        the id
     * @param fieldName the field name
     * @param value     the value
     * @return v return message
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02 Logic delete one by id v return message.
     */
    VReturnMessage logicDeleteOneById(ID id, String fieldName, String value);

    /**
     * 根据id获取实体类
     *
     * @param id the id
     * @return the one by id
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02
     */
    T getEntityById(ID id);

    /**
     * 根据条件查询实体类
     *
     * @param body    the body
     * @param matcher the matcher
     * @return the entities by condition
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02
     */
    Page<T> getEntitiesByCondition(SimplePageRequestBody<T> body, ExampleMatcher matcher);

    /**
     * 此方法为解析sql句子方法
     *
     * @param baseSql the base sql
     * @param body    the body
     * @return entities by condition
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02
     */
    Page<T> getEntitiesByCondition(String baseSql,SimplePageRequestBody<T> body);

    /**
     * 此为单表查询
     *
     * @param body the body
     * @return entities by condition
     * @author Nick Lv
     * @created 2017 /07/10 09:23:02
     */
    GridData<T> getEntitiesByCondition(SimplePageRequestBody<T> body);

}
