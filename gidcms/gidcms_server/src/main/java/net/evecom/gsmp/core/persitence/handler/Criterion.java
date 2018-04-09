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

/**
 * 描述 TODO..
 *
 * @author Nick Lv
 * @created 2017 /01/11 10:39
 */
public interface Criterion {
    /**
     * 描述 create.
     *
     * @param root    the root
     * @param query   the query
     * @param builder the builder
     * @return the predicate
     * @author Warren Chen
     * @created 2017 /01/11 10:40:28 To predicate predicate.
     */
    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}
