/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence.handler;


import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author Nick Lv
 * @created 2016 /12/19 0:24
 */
public class JoinExpression implements Criterion {

    /**
     * 左连接表
     */
    private String leftJoinTable;

    /**
     * 连接条件
     */
    private List<ConditionFilter> conditions = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        root.join(leftJoinTable, JoinType.LEFT);
        return null;
    }

    /**
     * 描述 Gets left join table.
     *
     * @return the left join table
     * @author Nick Lv
     * @created 2016 /12/19 00:26:31
     */
    public String getLeftJoinTable() {
        return leftJoinTable;
    }

    /**
     * 描述 Sets left join table.
     *
     * @param leftJoinTable the left join table
     * @author Nick Lv
     * @created 2016 /12/19 00:26:31
     */
    public void setLeftJoinTable(String leftJoinTable) {
        this.leftJoinTable = leftJoinTable;
    }

    /**
     * 描述 Gets conditions.
     *
     * @return the conditions
     * @author Nick Lv
     * @created 2016 /12/19 00:48:26
     */
    public List<ConditionFilter> getConditions() {
        return conditions;
    }

    /**
     * 描述 Sets conditions.
     *
     * @param conditions the conditions
     * @author Nick Lv
     * @created 2016 /12/19 00:48:26
     */
    public void setConditions(List<ConditionFilter> conditions) {
        this.conditions = conditions;
    }
}
