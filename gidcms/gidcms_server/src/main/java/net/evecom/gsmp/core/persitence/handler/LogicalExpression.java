/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述  逻辑条件表达式 用于复杂条件时使用，如但属性多对应值的OR查询等
 *
 * @author Nick Lv
 * @created 2016 /12/15 11:01
 */
public class LogicalExpression implements Criterion {

    /**
     * The Criterion.逻辑表达式中包含的表达式
     */
    private Criterion[] criterion;
    /**
     * The Operator.计算符
     */
    private Operator operator;

    /**
     *
     * @param criterions
     * @param operator
     * @author Nick Lv
     */
    public LogicalExpression(Criterion[] criterions, Operator operator) {
        this.criterion = criterions;
        this.operator = operator;
    }

    /**
     * 描述 create.
     *
     * @param root    the root
     * @param query   the query
     * @param builder the builder
     * @return the predicate
     * @author Nick Lv
     * @created 2016 /12/15 11:01:34 To predicate predicate.
     */
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (int i = 0; i < this.criterion.length; i++) {
            predicates.add(this.criterion[i].toPredicate(root, query, builder));
        }
        switch (operator) {
            case OR:
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            default:
                return null;
        }
    }

}
