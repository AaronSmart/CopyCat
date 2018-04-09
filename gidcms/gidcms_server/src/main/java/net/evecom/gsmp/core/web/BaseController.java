/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.web;

import net.evecom.gsmp.core.annotation.GridData;
import net.evecom.gsmp.core.persitence.BaseEntity;
import net.evecom.gsmp.core.service.BaseService;
import net.evecom.gsmp.core.utils.VReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * 控制层基础类
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 * @author Nick Lv
 * @created 2017 /7/4 15:11
 */
public class BaseController<T extends BaseEntity, ID extends Serializable> {

    /**
     * 泛型BaseService
     */
    @Autowired
    BaseService<T, ID> baseService;

    /**
     * 保存单个实体类.
     *
     * @param entity the entity
     * @return the t
     * @author Nick Lv
     * @created 2017 /07/10 09:17:31 Save entity t.
     */
    @RequestMapping("/saveEntity")
    public T saveEntity(@RequestBody T entity) {
        return baseService.saveEntity(entity);
    }

    /**
     * 批量保存实体类.
     *
     * @param entities the entities
     * @return the list
     * @author Nick Lv
     * @created 2017 /07/10 09:17:32 Save entities list.
     */
    @RequestMapping("/saveEntities")
    public List<T> saveEntities(@RequestBody List<T> entities) {
        return baseService.saveEntities(entities);
    }

    /**
     * 修改单个实体类.
     *
     * @param entity the entity
     * @return the t
     * @author Nick Lv
     * @created 2017 /07/10 09:17:32 Update entity t.
     */
    @RequestMapping("/updateEntity")
    public T updateEntity(@RequestBody T entity) {
        return baseService.updateEntity(entity);
    }

    /**
     * 批量修改实体类
     *
     * @param entities the entities
     * @return the list
     * @author Nick Lv
     * @created 2017 /07/10 09:17:32 Update entites list.
     */
    @RequestMapping("/updateEntities")
    public List<T> updateEntites(@RequestBody List<T> entities) {
        return baseService.updateEntities(entities);
    }

    /**
     * 根据单个id删除实体类
     *
     * @param id the id
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /07/10 09:17:32 Delete one by id v return message.
     */
    @RequestMapping("/deleteEntity")
    public VReturnMessage deleteOneById(ID id) {
        return baseService.deleteOneById(id);
    }

    /**
     * 根据实体类集合删除实体类
     *
     * @param ids the ids
     * @return the v return message
     * @author Nick Lv
     * @created 2017 /07/10 09:17:32 Delete entities v return message.
     */
    @RequestMapping("/deleteEntities")
    public VReturnMessage deleteEntities(@RequestBody List<T> ids) {
        return baseService.deleteEntities(ids);
    }

    /**
     * 根据id获取实体类
     *
     * @param id the id
     * @return the entity by id
     * @author Nick Lv
     * @created 2017 /07/10 09:17:32
     */
    @RequestMapping("/getEntity")
    public T getEntityById(@RequestParam("id") ID id) {
        return baseService.getEntityById(id);
    }

    /**
     * 根据条件获取实体类（集合）
     *
     * @param body the body
     * @return the entities by condition
     * @author Nick Lv
     * @created 2017 /07/10 09:17:32
     */
    @RequestMapping("/getEntities")
    public GridData<T> getEntitiesByCondition(@RequestBody SimplePageRequestBody body) {
        return baseService.getEntitiesByCondition(body);
    }
}
