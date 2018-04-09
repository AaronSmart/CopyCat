/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.core.annotation;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * GridTable对象
 *
 * @param <T> the type parameter
 * @author Nick Lv
 * @created 2017 /7/7 11:20
 */
public class GridData<T> {
    /**
     * 数据
     */
    private Page<T> value;
    /**
     * 列名
     */
    private List<VGridTable> column;

    /**
     * Gets value.
     *
     * @return the value
     */
    public Page<T> getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(Page<T> value) {
        this.value = value;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public List<VGridTable> getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(List<VGridTable> column) {
        this.column = column;
    }
}
