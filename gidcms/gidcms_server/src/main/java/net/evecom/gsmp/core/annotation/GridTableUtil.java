/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.annotation;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建列表工具类
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/26 10:09:22
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class GridTableUtil {

    /**
     * 创建列表
     *
     * @param sourceObject the source object
     * @return the list
     * @author Nick Lv
     * @created 2017 /05/26 10:09:22 Create grid table list.
     */
    public static List<VGridTable> createGridTable(Class<?> sourceObject)
            throws IllegalAccessException, InstantiationException {
        Object newObject =  sourceObject.newInstance();
        Field[] fields = newObject.getClass().getDeclaredFields();
        List<VGridTable> results = new LinkedList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            GridTable gridTable = field.getAnnotation(GridTable.class);
            if (null != gridTable) {
                VGridTable vGridTable = new VGridTable();
                vGridTable.setCnName(gridTable.cnName());
                vGridTable.setEnName(gridTable.enName());
                vGridTable.setStyle(gridTable.style());
                vGridTable.setStyleClass(gridTable.styleClass());
                vGridTable.setIsNotDate(Integer.parseInt(gridTable.isNotDate()));
                vGridTable.setViewOrder(Integer.parseInt(gridTable.viewOrder()));
                results.add(vGridTable);
            }
        }
        results.sort(new Comparator<VGridTable>() {
            @Override
            public int compare(VGridTable o1, VGridTable o2) {
                if (o1.getViewOrder() > o2.getViewOrder()) {
                    return 1;
                } else if (o1.getViewOrder() < o2.getViewOrder()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return results;
    }
}



