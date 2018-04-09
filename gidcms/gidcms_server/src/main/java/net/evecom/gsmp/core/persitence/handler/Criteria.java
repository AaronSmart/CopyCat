/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述 定义一个查询条件容器
 *
 * @param <T> the type parameter
 * @author Nick Lv
 * @created 2017 /01/11 10:42
 */
public class Criteria<T> implements Specification<T> {

    /**
     * 准则.
     */
    private List<Criterion> criterions = new ArrayList<>();

    /**
     * 描述 create.
     *
     * @param root  the root
     * @param query the query
     * @param cb    the cb
     * @return the predicate
     * @author Nick Lv
     * @created 2017 /01/11 10:43:24 To predicate predicate.
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (Criterion c : criterions) {
                predicates.add(c.toPredicate(root, query, cb));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return cb.conjunction();
    }

    /**
     * 增加简单条件表达式
     *
     * @param criterion the criterion
     * @return the criteria
     * @author Nick Lv
     * @created 2017 /01/11 10:43:24 Add.
     * @Methods Name add
     */
    public Criteria<T> add(Criterion criterion) {
        if (criterion != null) {
            criterions.add(criterion);
        }
        return this;
    }
}
