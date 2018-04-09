/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.gsmp.core.web;

import net.evecom.gsmp.core.persitence.handler.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询对象
 *
 * @version --添加版本信息
 * @author Nick Lv
 * @since Version 1.0
 * @see --添加类中引用的相关类和接口
 */
public class QueryRequestBody<T> {

    /**
     * 过滤条件
     */
    private List<ConditionFilter> conditionFilters;
    /**
     * 排序条件
     */
    private List<SortFilter> sortFilters;

    /**
     * 条件过滤
     *
     * @return the condition filters
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public List<ConditionFilter> getConditionFilters() {
        if (this.conditionFilters == null) {
            this.conditionFilters = new ArrayList<>();
        }
        return conditionFilters;
    }

    /**
     * 条件过滤集合
     *
     * @param conditionFilters
     *            the condition filters
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public void setConditionFilters(List<ConditionFilter> conditionFilters) {
        this.conditionFilters = conditionFilters;
    }

    /**
     * 获取排序过滤
     *
     * @return the sort filters
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public List<SortFilter> getSortFilters() {
        if (this.sortFilters == null) {
            this.sortFilters = new ArrayList<>();
        }
        return sortFilters;
    }

    /**
     * 获取排序过滤集合
     *
     * @param sortFilters
     *            the sort filters
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public void setSortFilters(List<SortFilter> sortFilters) {
        this.sortFilters = sortFilters;
    }

    /**
     * 获取排序
     *
     * @return the sort
     * @author Phil He
     * @created 2017 /07/06 10:36:36 Build sort sort.
     */
    public Sort buildSort() {
        List<Sort.Order> orders = new ArrayList<>();
        for (SortFilter sortFilter : this.getSortFilters()) {
            Sort.Direction direction = Sort.Direction.fromStringOrNull(sortFilter.getDirection());
            String filedName = sortFilter.getFiledName();
            if (direction != null && StringUtils.isNotEmpty(filedName)) {
                orders.add(new Sort.Order(direction, filedName));
            }
        }
        return new Sort(orders);
    }

    /**
     * 获取查询条件<code>Predicate</code>
     *
     * @return the criteria
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30 Build criteria criteria.
     */
    public Criteria<T> buildCriteria(boolean ignoreNull) {
        if(null == conditionFilters) {
            return null;
        }
        Criteria<T> criteria = new Criteria<>();
        for (ConditionFilter conditionFilter : conditionFilters) {
            String fileName = conditionFilter.getFieldName();
            Operator operator = Operator.fromStringOrNull(conditionFilter.getOperator());
            Object value = conditionFilter.getValue();

            if (StringUtils.isNotEmpty(fileName) && operator != null) {
                switch (operator) {
                    case EQ:
                        criteria.add(Restrictions.eq(fileName, value, ignoreNull));
                        break;
                    case NE:
                        criteria.add(Restrictions.ne(fileName, value, ignoreNull));
                        break;
                    case LIKE:
                        criteria.add(Restrictions.like(fileName, String.valueOf(value), ignoreNull));
                        break;
                    case LT:
                        criteria.add(Restrictions.lt(fileName, value, ignoreNull));
                        break;
                    case GT:
                        criteria.add(Restrictions.gt(fileName, value, ignoreNull));
                        break;
                    case LTE:
                        criteria.add(Restrictions.lte(fileName, value, ignoreNull));
                        break;
                    case GTE:
                        criteria.add(Restrictions.gte(fileName, value, ignoreNull));
                        break;
                    default:
                }
            }
        }
        return criteria;
    }

    /**
     * 描述： 查询是否存在某个过滤条件
     *
     * @param field
     *            the field
     * @param operator
     *            the operator
     * @return the condition filter
     * @author Nick Lv
     * @created 2017 /07/06 10:14:13 Find condition condition filter.
     */
    public ConditionFilter findCondition(String field, String operator) {
        for (ConditionFilter filter : this.getConditionFilters()) {
            if (field.equals(filter.getFieldName()) && operator.equals(filter.getOperator())) {
                return filter;
            }
        }
        return null;
    }

    /**
     * 增加过滤条件
     *
     * @param filter
     *            the filter
     * @author Nick Lv
     * @created 2017 /07/06 10:26:29 Add condition.
     */
    public void addCondition(ConditionFilter filter) {
        this.getConditionFilters().add(filter);
    }

    /**
     * 增加排序条件
     *
     * @param sort
     *            the sort
     * @author Phil He
     * @created 2017 /07/06 10:26:32 Add sort.
     */
    public void addSort(SortFilter sort) {
        this.getSortFilters().add(sort);
    }

}
