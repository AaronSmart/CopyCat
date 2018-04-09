/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.annotation;

import net.evecom.gsmp.core.utils.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 拼接前端所需的html页面，为前端资源打点的dialog服务
 *
 * @author Nick Lv
 * @created 2017 /04/24 19:36:56
 */
public class TableInfoUtil {
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TableInfoUtil.class);
    /**
     * th的开始标签
     */
    private static final String BEGIN_TH = "<th";
    /**
     * th的结束标签
     */
    private static final String END_TH = "</th>";
    /**
     * td的开始标签
     */
    private static final String BEGIN_TD = "<td";
    /**
     * td的结束标签
     */
    private static final String END_TD = "</td>";
    /**
     * tr的开始标签
     */
    private static final String BEGIN_TR = "<tr>";
    /**
     * tr的结束标签
     */
    private static final String END_TR = "</tr>";
    /**
     * table的开始标签
     */
    private static final String BEGIN_TABLE = "<table class='detail-table'>";
    /**
     * table的结束标签
     */
    private static final String END_TABLE = "</table>";
    /**
     * ul和li的开始标签
     */
    private static final String BEGIN_UL_LI = "<ul><li>";
    /**
     * li和Ul的结束标签
     */
    private static final String END_UL_LI = "</li></ul>";
    /**
     * div的开始标签
     */
    private static final String BEGIN_DIV = "<div class=table-head>";
    /**
     * div的结束标签
     */
    private static final String END_DIV = "</div>";
    /**
     * 标签的关闭括号
     */
    private static final String CLOSE_BRACKETS = ">";
    /**
     * 自定义注解中如果为-1则不显示
     */
    private static final String ROW_NOT_DISPLAY = "-1";

    /**
     * The constant BEGIN_IMG.
     */
    private static final String BEGIN_IMG = "<img src=";


    /**
     * The constant BEGIN_SPAN.
     */
    private static final String BEGIN_SPAN = "<span>";

    /**
     * The constant END_SPAN.
     */
    private static final String END_SPAN = "</span>";
    /**
     * 默认图片
     */
    private static final String DEFAULT_IMAGE = "./assets/imgs/user.jpg";
    /**
     * colspan
     */
    private static final String BEGIN_COLSPAN = " colspan='";
    /**
     * rowspan
     */
    private static final String BEGIN_ROWSPAN = " rowspan='";
    /**
     * 单引号
     */
    private static final String END_SINGLE_QUOTE = "' ";
    /**
     * The constant imageInterface.
     */
    //private static String imageInterface = "http://192.168.56.177:8252/detail/image?imageName=";
    private static final String IMAGE_INTERFACE = "";
    /**
     * 建立html内容
     *
     * @param sourceMap the source map
     * @return the list
     * @throws IllegalAccessException the illegal access exception
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Create table info list.
     */
    @Deprecated
    public static List<String> createTableInfo(Map<String, Object> sourceMap) throws IllegalAccessException {
        Iterator<Map.Entry<String, Object>> entries = sourceMap.entrySet().iterator();
        List<String> results = new ArrayList<>();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            if (null != entry.getValue()) {
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                // 利用treeMap来排序
                Map<Integer, String> allRowsMap = new TreeMap<>();
                for (Field field : fields) {
                    StringBuilder tempOfrow = new StringBuilder();
                    field.setAccessible(true);
                    TableInfo tableInfo = field.getAnnotation(TableInfo.class);
                    if (null != tableInfo) {
                        if (!tableInfo.rowIndex().equals(ROW_NOT_DISPLAY)) {
                            tempOfrow.append(BEGIN_TH).append(tableInfo.align()).append(tableInfo.colSpan())
                                    .append(tableInfo.rowSpan()).append(CLOSE_BRACKETS).append(tableInfo.cnName())
                                    .append(END_TH)
                                    .append(BEGIN_TD).append(tableInfo.align()).append(tableInfo.colSpan())
                                    .append(tableInfo.rowSpan()).append(CLOSE_BRACKETS)
                                    .append(field.get(entry.getValue()))
                                    .append(END_TD);
                            //判断map中是否已经有rowIndex所对应的key了，如果为false，则为新增
                            if (allRowsMap.containsKey(Integer.valueOf(tableInfo.rowIndex()))) {
                                allRowsMap.put(Integer.valueOf(tableInfo.rowIndex()),
                                        allRowsMap.get(Integer.valueOf(tableInfo.rowIndex())) + tempOfrow.toString());
                            } else {
                                allRowsMap.put(Integer.valueOf(tableInfo.rowIndex()), tempOfrow.toString());
                            }
                        }
                    }
                }
                StringBuilder oneTable = appendTRtag(allRowsMap);
                oneTable.append(BEGIN_TABLE).append(oneTable).append(END_TABLE);
                results.add(oneTable.toString());
            }
        }
        return results;
    }


    /**
     * 分组拼接句子
     *
     * @param sourceMap        the source map
     * @return the list
     * @throws IllegalAccessException the illegal access exception
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Create table info groups list.
     */
    public static List<Map<String, String>> createTableInfoGroups(
            Map<Integer, Object> sourceMap) {
        Iterator<Map.Entry<Integer, Object>> entries = sourceMap.entrySet().iterator();
        List<Map<String, String>> results = new ArrayList<>();
        while (entries.hasNext()) {
            Map.Entry<Integer, Object> entry = entries.next();
            if (null != entry.getValue()) {
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                Map<String, String> aResult = new TreeMap<>();
                Map<Integer, Map<Integer, String>> groups = new TreeMap<>();
                Map<Integer, String> groupNames = new TreeMap<>();
                for (Field field : fields) {
                    Map<Integer, String> groupMember = new TreeMap<>();
                    field.setAccessible(true);
                    TableInfo tableInfo = field.getAnnotation(TableInfo.class);
                    try {
                        if (null != tableInfo) {
                            // 如果分组的key已经存在
                            if (groups.containsKey(Integer.valueOf(tableInfo.groupIndex()))) {
                                groupMember = groups.get(Integer.valueOf(tableInfo.groupIndex()));
                                groupMember = updateGroupMember(field, tableInfo, entry, groupMember);
                                groups.put(Integer.valueOf(tableInfo.groupIndex()), groupMember);
                                groupNames.put(Integer.valueOf(tableInfo.groupIndex()), tableInfo.groupName());
                            } else {
                                // 如果分组的key不存在
                                groupMember = updateGroupMember(field, tableInfo, entry, groupMember);
                                groups.put(Integer.valueOf(tableInfo.groupIndex()), groupMember);
                                groupNames.put(Integer.valueOf(tableInfo.groupIndex()), tableInfo.groupName());
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.error("生成html页面异常", e);
                    }
                }
                Iterator<Map.Entry<Integer, Map<Integer, String>>> groupEntries = groups.entrySet().iterator();
                while (groupEntries.hasNext()) {
                    Map.Entry<Integer, Map<Integer, String>> groupEntry = groupEntries.next();
                    StringBuilder oneTable = new StringBuilder();
                    oneTable.append(BEGIN_DIV)
                            .append(BEGIN_SPAN).append(groupNames.get(groupEntry.getKey())).append(END_SPAN)
                            .append(END_DIV)
                            .append(BEGIN_TABLE).append(appendTRtag(groupEntry.getValue())).append(END_TABLE);
                    aResult.put(groupNames.get(groupEntry.getKey()), oneTable.toString());
                }
                results.add(aResult);
            }
        }
        return results;
    }


    /**
     * 修改同一组别的内容
     *
     * @param field       the field
     * @param tableInfo   the table info
     * @param entry       the entry
     * @param groupMember the group member
     * @return the map
     * @throws IllegalAccessException the illegal access exception
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Update group member map.
     */
    private static Map<Integer, String> updateGroupMember(
            Field field, TableInfo tableInfo, Map.Entry<Integer, Object> entry,
            Map<Integer, String> groupMember) throws IllegalAccessException {
        StringBuilder sBuilder = null;
        if (!tableInfo.rowIndex().equals(ROW_NOT_DISPLAY)) {
            // 拼接句子
            sBuilder = completeSentence(field, tableInfo, entry);
            // 将句子放入到Map<Integer, String>中
            if (groupMember.containsKey(Integer.valueOf(tableInfo.rowIndex()))) {
                groupMember.put(Integer.valueOf(tableInfo.rowIndex()),
                        groupMember.get(Integer.valueOf(tableInfo.rowIndex())) + sBuilder.toString());
            } else {
                groupMember.put(Integer.valueOf(tableInfo.rowIndex()), sBuilder.toString());
            }
        }
        return groupMember;
    }

    /**
     * 拼接句子
     *
     * @param field     the field
     * @param tableInfo the table info
     * @param entry     the entry
     * @return the string builder
     * @throws IllegalAccessException the illegal access exception
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Complete sentence string builder.
     */
    private static StringBuilder completeSentence(
            Field field, TableInfo tableInfo, Map.Entry<Integer, Object> entry)
            throws IllegalAccessException {
        StringBuilder sBuilder = new StringBuilder();
        if (tableInfo.isImage()) {
            sBuilder.append(BEGIN_TD).append(tableInfo.align()).append(tableInfo.colSpan())
                    .append(tableInfo.rowSpan()).append(CLOSE_BRACKETS)
                    .append(BEGIN_IMG).append(IMAGE_INTERFACE)
                    .append(replaceDefaultImage(tableInfo, Reflections.invokeGetter(entry.getValue(),field.getName())))
                    .append(CLOSE_BRACKETS).append(END_TD);
        } else {
            sBuilder.append(BEGIN_TH).append(tableInfo.align()).append(tableInfo.colSpan())
                    .append(tableInfo.rowSpan()).append(CLOSE_BRACKETS).append(tableInfo.cnName())
                    .append(END_TH)
                    .append(BEGIN_TD).append(tableInfo.align()).append(tableInfo.colSpan())
                    .append(tableInfo.rowSpan()).append(CLOSE_BRACKETS)
                    .append(replaceNull(tableInfo, Reflections.invokeGetter(entry.getValue(),field.getName())))
                    .append(END_TD);
        }
        return sBuilder;
    }

    /**
     * 转换为默认图片
     *
     * @param tableInfo  the table info
     * @param fieldValue the field value
     * @return string string
     * @author Nick Lv
     * @created 2017 /05/24 16:55:13 Replace default image string.
     */
    private static String replaceDefaultImage(TableInfo tableInfo, Object fieldValue) {
        if (null == fieldValue) {
            return DEFAULT_IMAGE;
        } else {
            return fieldValue.toString();
        }
    }

    /**
     * 将结果中的null值全部替换，不然会出现null出现在字串中
     *
     * @param tableInfo  the table info
     * @param fieldValue the field value
     * @return the string
     * @author Nick Lv
     * @created 2017 /04/24 19:36:57 Replace null string.
     */
    private static String replaceNull(TableInfo tableInfo, Object fieldValue) {
        if (null == fieldValue) {
            return tableInfo.defaultValue();
        } else if (fieldValue instanceof Timestamp) {
            SimpleDateFormat sdf = new SimpleDateFormat(tableInfo.dataFormat());
            return sdf.format(fieldValue);
        }
        return fieldValue.toString();
    }


    /**
     * 添加<tr></tr>换行标签
     *
     * @param allRowsMap the all rows map
     * @return the string builder
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Append t rtag string builder.
     */
    private static StringBuilder appendTRtag(Map<Integer, String> allRowsMap) {
        Iterator<Map.Entry<Integer, String>> entries = allRowsMap.entrySet().iterator();
        StringBuilder sBuilder = new StringBuilder();
        while (entries.hasNext()) {
            Map.Entry<Integer, String> entry = entries.next();
            sBuilder.append(BEGIN_TR).append(entry.getValue()).append(END_TR);
        }
        return sBuilder;
    }

    // --------------------------------------分割线-------------------------------------------------------------
    /**
     * 分组拼接句子
     *
     * @param sourceMap        the source map
     * @return the list
     * @throws IllegalAccessException the illegal access exception
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Create table info groups list.
     */
    public static List<Map<String, String>> createTableInfoGroupsNew(
            Map<Integer, Object> sourceMap) {
        Iterator<Map.Entry<Integer, Object>> entries = sourceMap.entrySet().iterator();
        List<Map<String, String>> results = new ArrayList<>();
        while (entries.hasNext()) {
            Map.Entry<Integer, Object> entry = entries.next();
            if (null != entry.getValue()) {
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                Map<String, String> aResult = new TreeMap<>();
                Map<Integer, Map<Integer, String>> groups = new TreeMap<>();
                Map<Integer, String> groupNames = new TreeMap<>();
                for (Field field : fields) {
                    Map<Integer, String> groupMember = new TreeMap<>();
                    field.setAccessible(true);
                    TableInfo tableInfo = field.getAnnotation(TableInfo.class);
                    try {
                        if (null != tableInfo) {
                            // 如果分组的key已经存在
                            if (groups.containsKey(Integer.valueOf(tableInfo.groupIndex()))) {
                                groupMember = groups.get(Integer.valueOf(tableInfo.groupIndex()));
                                groupMember = updateGroupMemberNew(field, tableInfo, entry, groupMember);
                                groups.put(Integer.valueOf(tableInfo.groupIndex()), groupMember);
                                groupNames.put(Integer.valueOf(tableInfo.groupIndex()), tableInfo.groupName());
                            } else {
                                // 如果分组的key不存在
                                groupMember = updateGroupMemberNew(field, tableInfo, entry, groupMember);
                                groups.put(Integer.valueOf(tableInfo.groupIndex()), groupMember);
                                groupNames.put(Integer.valueOf(tableInfo.groupIndex()), tableInfo.groupName());
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.error("生成html页面异常", e);
                    }
                }
                Iterator<Map.Entry<Integer, Map<Integer, String>>> groupEntries = groups.entrySet().iterator();
                while (groupEntries.hasNext()) {
                    Map.Entry<Integer, Map<Integer, String>> groupEntry = groupEntries.next();
                    StringBuilder oneTable = new StringBuilder();
                    oneTable.append(BEGIN_DIV)
                            .append(BEGIN_SPAN).append(groupNames.get(groupEntry.getKey())).append(END_SPAN)
                            .append(END_DIV)
                            .append(BEGIN_TABLE).append(appendTRtag(groupEntry.getValue())).append(END_TABLE);
                    aResult.put(groupNames.get(groupEntry.getKey()), oneTable.toString());
                }
                results.add(aResult);
            }
        }
        return results;
    }


    /**
     * 修改同一组别的内容
     *
     * @param field       the field
     * @param tableInfo   the table info
     * @param entry       the entry
     * @param groupMember the group member
     * @return the map
     * @throws IllegalAccessException the illegal access exception
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Update group member map.
     */
    private static Map<Integer, String> updateGroupMemberNew(
            Field field, TableInfo tableInfo, Map.Entry<Integer, Object> entry,
            Map<Integer, String> groupMember) throws IllegalAccessException {
        StringBuilder sBuilder = null;
        if (!tableInfo.rowIndex().equals(ROW_NOT_DISPLAY)) {
            // 拼接句子
            sBuilder = completeSentenceNew(field, tableInfo, entry);
            // 将句子放入到Map<Integer, String>中
            if (groupMember.containsKey(Integer.valueOf(tableInfo.rowIndex()))) {
                groupMember.put(Integer.valueOf(tableInfo.rowIndex()),
                        groupMember.get(Integer.valueOf(tableInfo.rowIndex())) + sBuilder.toString());
            } else {
                groupMember.put(Integer.valueOf(tableInfo.rowIndex()), sBuilder.toString());
            }
        }
        return groupMember;
    }

    /**
     * 拼接句子
     *
     * @param field     the field
     * @param tableInfo the table info
     * @param entry     the entry
     * @return the string builder
     * @throws IllegalAccessException the illegal access exception
     * @author Nick Lv
     * @created 2017 /04/20 15:50:43 Complete sentence string builder.
     */
    private static StringBuilder completeSentenceNew(
            Field field, TableInfo tableInfo, Map.Entry<Integer, Object> entry)
            throws IllegalAccessException {
        StringBuilder sBuilder = new StringBuilder();
        if (tableInfo.isImage()) {
            sBuilder.append(BEGIN_TD)
                    .append(BEGIN_COLSPAN).append(tableInfo.tdColSpan()).append(END_SINGLE_QUOTE)
                    .append(BEGIN_ROWSPAN).append(tableInfo.tdRowSpan()).append(END_SINGLE_QUOTE)
                    .append(CLOSE_BRACKETS)
                    .append(BEGIN_IMG)
                    .append(replaceDefaultImage(tableInfo, Reflections.invokeGetter(entry.getValue(),field.getName())))
                    .append(CLOSE_BRACKETS).append(END_TD);
        } else {
            sBuilder.append(BEGIN_TH)
                    .append(tableInfo.align())
                    .append(BEGIN_COLSPAN).append(tableInfo.thColSpan()).append(END_SINGLE_QUOTE)
                    .append(BEGIN_ROWSPAN).append(tableInfo.thRowSpan()).append(END_SINGLE_QUOTE)
                    .append(CLOSE_BRACKETS)
                    .append(tableInfo.cnName())
                    .append(END_TH)
                    .append(BEGIN_TD)
                    .append(tableInfo.align())
                    .append(BEGIN_COLSPAN).append(tableInfo.tdColSpan()).append(END_SINGLE_QUOTE)
                    .append(BEGIN_ROWSPAN).append(tableInfo.tdRowSpan()).append(END_SINGLE_QUOTE)
                    .append(CLOSE_BRACKETS)
                    .append(replaceNull(tableInfo, Reflections.invokeGetter(entry.getValue(),field.getName())))
                    .append(END_TD);
        }
        return sBuilder;
    }



}



