/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence;

import net.evecom.gsmp.core.utils.SqlUtil;
import net.evecom.gsmp.core.utils.EntityConverterUtil;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.*;

/**
 * <p>
 * <B>Description: </B> 数据层基础类
 * </P>
 * Revision Trail: (Date/Author/Description)
 *
 * @param <T>
 *            the type parameter
 * @param <ID>
 *            the type parameter
 * @author Nick Lv
 * @version 1.0
 * @created 2017 /02/16 16:53:23
 */
@Repository
public abstract class BaseJPAImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
    /**
     * select
     */
    protected final String SELECT = " SELECT ";
    /**
     * where
     */
    protected final String WHERE = " WHERE ";
    /**
     * count(*)
     */
    protected final String COUNT_ALL = " COUNT(*) ";

    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(BaseJPAImpl.class);

    /**
     *
     * @see EntityManager
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * 泛型类
     */
    private Class<T> clazz;

    /**
     * 构造方法
     */
    protected BaseJPAImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /**
     * 描述： 根据语句去获取可执行的query对象
     *
     * @param values
     *            the values
     * @return the execute query
     * @throws Exception
     *             the exception
     * @author Nick Lv
     * @created 2017 /02/24 20:04:00
     */
    private Query getExecuteQuery(String sql, Object[] values) {
        Query query = em.createNativeQuery(sql);
        if (values != null && values.length > 0) {
            for (int i = 0, j = 1; i < values.length; i++, j++) {
                if (values[i] instanceof Date) {
                    query.setParameter(j, (Date) values[i], TemporalType.DATE);
                } else {
                    query.setParameter(j, values[i]);
                }
            }
        }
        return query;
    }

    /**
     * 描述： 根据语句去获取可执行的query对象,分页功能
     *
     * @param values
     *            the values
     * @return the execute query
     * @throws Exception
     *             the exception
     * @author Nick Lv
     * @created 2017 /02/24 20:04:00
     */
    private Query getExecuteQuery(String sql, Object[] values, int page, int pageSize) {
        Query query = this.getExecuteQuery(sql, values);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query;
    }

    /**
     * 将数组列表转化为T类型的对象列表。
     *
     * @param mClazz
     *            对象类型
     * @param list
     *            数组列表
     * @return T类型的对象列表
     * @throws Exception
     */
    private <M> List<M> arrayListToClassList(Class<M> mClazz, Object[] sqlParams, List<Object> list) throws Exception {
        List<M> resultList = new ArrayList<M>();
        for (Object object : list) {
            if (object instanceof Object[]) {
                Object[] objs = (Object[]) object;
                resultList.add(arrayToClass(mClazz, sqlParams, objs));
            } else {
                throw new RuntimeException("发现非数组类型返回!");
            }
        }
        return resultList;
    }

    /**
     * 将OBJECT对象数组转化为对象M（如MODEL或VMODEL），数组的记录映射到对象的属性，记录顺序与查询时的SQL参数一一对应，
     * 但不需和对象属性对应
     *
     * @param mClazz
     *            对象M的类，如MODEL或VMODEL
     * @param sqlParams
     *            SQL语句的参数
     * @param objs
     *            待转换的对象数组
     * @return 对象M
     * @throws Exception
     */
    private <M> M arrayToClass(Class<M> mClazz, Object[] sqlParams, Object[] objs) throws Exception {
        Field[] fields = mClazz.getDeclaredFields();
        // 较验SQL参数与返回值数组中的长度是否一致。
        if (objs.length > sqlParams.length) {
            // 分页查询结果会在数组最后多出rownum列，所以将数组最后一个元素删除后再进行判断。
            objs = Arrays.copyOf(objs, objs.length - 1);
            if (objs.length != sqlParams.length) {
                throw new RuntimeException(
                        "参数数组与返回值数组中长度不同,SQL参数长度为" +
                                sqlParams.length + ";返回值数组长度为" + objs.length + ".");
            }
        }
        M result = mClazz.newInstance();
        // 将数组中的值赋给类字段。
        for (int i = 0; i < objs.length; i++) {
            if (null == objs[i])
                continue;
            for (Field field : fields) {
                if (field.getName().toUpperCase().equals(sqlParams[i])) {
                    field.setAccessible(true);
                    Class typeClass = field.getType();
                    /**
                     * 类型转换各种情况判断： String ,Timestamp,Integer,BigDecimal
                     */
                    // System.out.println(typeClass.getName());
                    // System.out.println(objs[i]);
                    if (String.class.getName().equals(typeClass.getName())) {
                        // System.out.println(objs[i]);

                        /**
                         * 修改原因：java查询oracle常数字符串会出现出现cannot cast错误
                         * 若出错，可还原并在SQL中使用 ：CAST('字段' AS VARCHAR2(1)) 亦可解决
                         * Revision Trail: (Date/Author/Description) 2016-9-13
                         * Church Lin CREATE
                         *
                         * @author Church Lin
                         */
                        // field.set(result, (String) (objs[i]));
                        field.set(result, (objs[i].toString()));
                    } else if (Timestamp.class.getName().equals(typeClass.getName())) {
                        field.set(result, objs[i]);
                    } else if (BigDecimal.class.getName().equals(typeClass.getName())) {
                        field.set(result, objs[i]);
                    } else if (Long.class.getName().equals(typeClass.getName())) {
                        // field.set(result, new Long(((BigDecimal)
                        // (objs[i])).longValue()));
                        field.set(result, Long.valueOf(String.valueOf(objs[i])));
                    } else if (Integer.class.getName().equals(typeClass.getName())) {
                        field.set(result, Integer.valueOf(String.valueOf(objs[i])));
                    } else if (boolean.class.getName().equals(typeClass.getName())) {
                        field.set(result, Boolean.parseBoolean(objs[i].toString()));
                    } else if (Clob.class.getName().equals(typeClass.getName())) {
                        field.set(result, objs[i]);
                    } else if (Date.class.getName().equals(typeClass.getName())) {
                        field.set(result, objs[i]);
                    } else if (int.class.getName().equals(typeClass.getName())) {
                        field.set(result, objs[i]);
                    } else if (Double.class.getName().equals(typeClass.getName())) {
                        field.set(result, new Double(((BigDecimal) (objs[i])).doubleValue()));
                    } else {
                        Method valueOfMethod = typeClass.getMethod("valueOf", String.class);
                        field.set(result, valueOfMethod.invoke(typeClass, (String) (objs[i])));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public long countBySql(String sqlString, Object[] values) {
        Query query = this.getExecuteQuery(sqlString, values);
        return Long.parseLong(query.getSingleResult().toString());
    }

    @Override
    public long getCountBySql(String sqlString, Object[] values) {
        String allSql = "select count(*) from ( " + sqlString + " )";
        return this.countBySql(allSql, values);
    }

    @Override
    public int executeSql(String sqlString, Object[] values) throws Exception {
        return this.getExecuteQuery(sqlString, values).executeUpdate();
    }

    @Override
    public List query(String sqlString, Object[] values) {
        return this.getExecuteQuery(sqlString, values).getResultList();
    }

    @Override
    public List query(String sqlString, Object[] values, int page, int pageSize) {
        return this.getExecuteQuery(sqlString, values, page, pageSize).getResultList();
    }

    @Override
    public Page<T> queryByPage(String sqlString, Object[] values, int page, int pageSize) {
        return this.queryByPage(this.clazz, sqlString, values, page, pageSize);
    }

    @Override
    public <M> Page<M> queryByPage(Class<M> tClass, String sqlString, Object[] values, int page, int pageSize) {
        return new PageImpl(this.queryBySql(tClass, sqlString, values, page, pageSize), new PageRequest(page, pageSize),
                this.getCountBySql(sqlString, values));
    }

    @Override
    public List<Map<String, Object>> queryListMap(String sqlString, Object[] values) {
        Query query = this.getExecuteQuery(sqlString, values);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> queryListMap(String sqlString, Object[] values, int page, int pageSize) {
        Query query = this.getExecuteQuery(sqlString, values, page, pageSize);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    @Override
    public <M> List<M> queryEntityBySql(Class<M> tClass, String sqlString, Object[] values) {
        try {
            return EntityConverterUtil.transform(this.queryListMap(sqlString, values), tClass);
        } catch (Exception e) {
            logger.error("转换List<Map>至实体类异常", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <M> List<M> queryBySql(Class<M> mClazz, String sqlString, Object[] values) {
        try {
            return arrayListToClassList(mClazz, SqlUtil.getParamArray(sqlString, mClazz), query(sqlString, values));
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<T> queryBySql(String sqlString, Object[] values) {
        return this.queryBySql(this.clazz, sqlString, values);
    }

    @Override
    public <M> List<M> queryBySql(Class<M> mClazz, String sqlString, Object[] values, int page, int pageSize) {
        try {
            return arrayListToClassList(mClazz, SqlUtil.getParamArray(sqlString, mClazz),
                    query(sqlString, values, page, pageSize));
        } catch (InstantiationException e) {
            logger.error(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<T> queryBySql(String sqlString, Object[] values, int page, int pageSize) {
        return this.queryBySql(this.clazz, sqlString, values, page, pageSize);
    }

    @Override
    public <M> M queryUniqueBySql(Class<M> mClazz, String sqlString, Object[] values) throws Exception {
        Query query = this.getExecuteQuery(sqlString, values);
        query.setMaxResults(1);
        List<M> list = arrayListToClassList(mClazz, SqlUtil.getParamArray(sqlString, mClazz), query.getResultList());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public T queryUniqueBySql(String sqlString, Object[] values) throws Exception {
        return this.queryUniqueBySql(this.clazz, sqlString, values);
    }

    @Override
    public <M> M queryUnique(Class<M> clazz, Map<String, Object> searchParams, String sql) throws Exception {
        Map<String, Object> sqlAndValue = SqlUtil.getSqlAndValue(searchParams, sql);
        return queryUniqueBySql(clazz, sqlAndValue.get("sql").toString(), (Object[]) sqlAndValue.get("value"));
    }

    @Override
    public long count(Map<String, Object> searchParams, String sql) {
        Map<String, Object> sqlAndValue = SqlUtil.getSqlAndValue(searchParams, sql);
        return countBySql(sqlAndValue.get("sql").toString(), (Object[]) sqlAndValue.get("value"));
    }

    @Override
    public <M> List<M> queryForList(Class<M> clazz, Map<String, Object> searchParams, String sql) {
        Map<String, Object> sqlAndValue = SqlUtil.getSqlAndValue(searchParams, sql);
        return queryBySql(clazz, sqlAndValue.get("sql").toString(), (Object[]) sqlAndValue.get("value"));
    }

    @Override
    public <M> List<M> queryForList(Class<M> clazz, Map<String, Object> searchParams, String sql, int page,
            int pageSize) {
        Map<String, Object> sqlAndValue = SqlUtil.getSqlAndValue(searchParams, sql);
        return queryBySql(clazz, sqlAndValue.get("sql").toString(), (Object[]) sqlAndValue.get("value"), page,
                pageSize);
    }
}
