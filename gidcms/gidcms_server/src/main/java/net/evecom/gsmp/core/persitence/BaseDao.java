/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 数据层基础类接口
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 * @author Nick Lv
 * @version 1.0
 * @created 2017 /02/16 18:07:46
 */
public interface BaseDao<T, ID extends Serializable> {

    /**
     * 返回sql语句的记录数
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return long long
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Count by sql long.
     */
    long countBySql(String sqlString, Object[] values);

    /**
     * 查询数据总量
     *
     * @param sqlString sql语句
     * @param values    参数值
     * @return count by sql
     * @author Nick Lv
     * @created 2017 /02/16 18:07:46
     */
    long getCountBySql(String sqlString, Object[] values);

    /**
     * 根据sql语句查询，返回List
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return list list
     * @author Nick Lv
     * @created 2017 /02/16 18:07:46 Query list.
     */
    List query(String sqlString, Object[] values);

    /**
     * 事务执行sql语句，也可执行无返回结果的存储过程
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return 受影响的记录数 int
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Execute sql int.
     */
    int executeSql(String sqlString, Object[] values) throws Exception;

    /**
     * 根据sql语句分页查询，返回List
     *
     * @param sqlString sql语句
     * @param values    参数值
     * @param page      当前页码，从0开始
     * @param pageSize  每页条数
     * @return 查询结果 list
     * @author Nick Lv
     * @created 2017 /02/16 18:07:46 Query list.
     */
    List query(String sqlString, Object[] values, int page, int pageSize);

    /**
     * 描述 根据sql语句分页查询，返回Page
     *
     * @param <M>       the type parameter
     * @param tClass    the t class
     * @param sqlString the sql string
     * @param values    the values
     * @param page      当前页码，从0开始
     * @param pageSize  每页条数
     * @return the page
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Query by page page.
     */
    <M> Page<M> queryByPage(Class<M> tClass, String sqlString, Object[] values, int page, int pageSize);

    /**
     * 描述 根据sql语句分页查询，返回Page
     *
     * @param sqlString the sql string
     * @param values    the values
     * @param page      当前页码，从0开始
     * @param pageSize  每页条数
     * @return the page
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Query by page page.
     */
    Page<T> queryByPage(String sqlString, Object[] values, int page, int pageSize);

    /**
     * 描述 create.
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return the list
     * @author Nick Lv
     * @created 2017 /03/01 10:32:44 Query list map list.
     */
    List<Map<String, Object>> queryListMap(String sqlString, Object[] values);

    /**
     * 描述 根据sql语句分页查询，返回List<Map<String, Object>>
     *
     * @param sqlString the sql string
     * @param values    the values
     * @param page      当前页码，从0开始
     * @param pageSize  每页条数
     * @return the list
     * @author Nick Lv
     * @created 2017 /03/01 10:32:44 Query list map list.
     */
    List<Map<String, Object>> queryListMap(String sqlString, Object[] values, int page, int pageSize);

    /**
     * 描述 create.
     *
     * @param <M>       the type parameter
     * @param tClass    the t class
     * @param sqlString the sql string
     * @param values    the values
     * @return the list
     * @author Nick Lv
     * @created 2017 /03/01 10:32:44 Query list map list.
     */
    <M> List<M> queryEntityBySql(Class<M> tClass, String sqlString, Object[] values);

    /**
     * 根据sql语句查询。也可以执行返回数据集的存储过程, 适用于VModel
     *
     * @param <M>       the type parameter
     * @param clazz     the clazz
     * @param sqlString the sql string
     * @param values    the values
     * @return list list
     * @author Nick Lv
     * @created 2017 /02/16 18:07:46 Query by sql list.
     */
    <M> List<M> queryBySql(Class<M> clazz, String sqlString, Object[] values);

    /**
     * 根据sql语句查询。也可以执行返回数据集的存储过程 , 适用于Model
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return list list
     * @author Nick Lv
     * @created 2017 /02/16 18:07:46 Query by sql list.
     */
    List<T> queryBySql(String sqlString, Object[] values);

    /**
     * 根据sql语句查询（分页）, 适用于VModel
     *
     * @param <M>       the type parameter
     * @param c         the c
     * @param sqlString the sql string
     * @param values    the values
     * @param page      当前页码，从0开始
     * @param pageSize  每页条数
     * @return list list
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Query by sql list.
     */
    <M> List<M> queryBySql(Class<M> c, String sqlString, Object[] values, int page, int pageSize);

    /**
     * 根据sql语句查询（分页）, 适用于Model
     *
     * @param sqlString the sql string
     * @param values    the values
     * @param page      当前页码，从0开始
     * @param pageSize  每页条数
     * @return list list
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Query by sql list.
     */
    List<T> queryBySql(String sqlString, Object[] values, int page, int pageSize);

    /**
     * 根据sql语句查询，并返回一条记录, 适用于VModel
     *
     * @param <M>       the type parameter
     * @param c         the c
     * @param sqlString the sql string
     * @param values    the values
     * @return m m
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Query unique by sql m.
     */
    <M> M queryUniqueBySql(Class<M> c, String sqlString, Object[] values) throws Exception;

    /**
     * 根据sql语句查询，并返回一条记录, 适用于Model
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return t t
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /02/16 18:07:47 Query unique by sql t.
     */
    T queryUniqueBySql(String sqlString, Object[] values) throws Exception;

    /**
     * 描述 create.
     *
     * @param <M>          the type parameter
     * @param clazz        the clazz
     * @param searchParams the search params
     * @param sql          the sql
     * @return the m
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /07/07 13:46:22 Query unique m.
     */
    <M> M queryUnique(Class<M> clazz, Map<String, Object> searchParams, String sql) throws Exception;

    /**
     * 描述 create.
     *
     * @param searchParams the search params
     * @param sql          the sql
     * @return the long
     * @author Nick Lv
     * @created 2017 /07/07 13:46:22 Count long.
     */
    long count(Map<String, Object> searchParams, String sql);

    /**
     * 描述 create.
     *
     * @param <M>          the type parameter
     * @param clazz        the clazz
     * @param searchParams the search params
     * @param sql          the sql
     * @return the list
     * @author Nick Lv
     * @created 2017 /07/07 13:46:22 Query for list list.
     */
    <M> List<M> queryForList(Class<M> clazz, Map<String, Object> searchParams, String sql);

    /**
     * 描述 create.
     *
     * @param <M>          the type parameter
     * @param clazz        the clazz
     * @param searchParams the search params
     * @param sql          the sql
     * @param page         the page
     * @param pageSize     the page size
     * @return the list
     * @author Nick Lv
     * @created 2017 /07/07 13:46:22 Query for list list.
     */
    <M> List<M> queryForList(Class<M> clazz, Map<String, Object> searchParams, String sql, int page, int pageSize);
}
