/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.web;

import org.springframework.data.domain.PageRequest;

/**
 * 请求对象
 *
 * @param <T>
 *            the type parameter
 * @author Nick Lv
 * @created 2017 /03/01 10:39:30
 */
public class PageRequestBody<T> extends QueryRequestBody {
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页行数
     */
    private int size;

    /**
     * 获取当前页面
     *
     * @return the current page
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public int getPage() {
        return page;
    }

    /**
     * 获取当前页面集合.
     *
     * @param page
     *            the current page
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 获取当前页面行数
     *
     * @return the rows per page
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取当前页面行数集合
     *
     * @param size
     *            the rows per page
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获取分页查询请求
     *
     * @return the page request
     * @author Nick Lv
     * @created 2017 /03/01 10:39:30 Build pageable page request.
     */
    public PageRequest buildPageable() {
        // 分页
        int currentPage = this.page;
        int rowsPerPage = this.size;
        if (this.size <= 0) {
            rowsPerPage = 10;
        }
        return this.getSortFilters().size() > 0 ? new PageRequest(currentPage, rowsPerPage, this.buildSort())
                : new PageRequest(currentPage, rowsPerPage);
    }
}
