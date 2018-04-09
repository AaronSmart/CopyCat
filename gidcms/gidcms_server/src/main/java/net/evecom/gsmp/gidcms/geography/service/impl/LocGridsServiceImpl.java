/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service.impl;

import net.evecom.gsmp.core.service.BaseServiceImpl;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.dao.LocGridTypesJPA;
import net.evecom.gsmp.gidcms.geography.dao.LocGridsJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocGridTypes;
import net.evecom.gsmp.gidcms.geography.entity.LocGrids;
import net.evecom.gsmp.gidcms.geography.service.LocGridsService;
import net.evecom.gsmp.core.annotation.GridTableUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网格信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:29
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Service
@Transactional
public class LocGridsServiceImpl extends BaseServiceImpl<LocGrids, Long> implements LocGridsService {
    /**
     * 网格jpa
     */
    @Autowired
    LocGridsJPA locGridsJPA;
    /**
     * 网格类型jpa
     */
    @Autowired
    LocGridTypesJPA locGridTypesJPA;
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocGridsServiceImpl.class);

    @Override
    public Map<String, Object> getLocGirds(SimplePageRequestBody<LocGrids> body) {
        Long enable = 1L;
        body.getCondition().setEnabled(enable);
        Map<String, Object> result = new HashMap<>();
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<LocGrids> example = Example.of(body.getCondition(), matcher);
        result.put("value", locGridsJPA.findAll(example, body.buildPageable()));
        try {
            result.put("column", GridTableUtil.createGridTable(LocGrids.class));
        } catch (Exception e) {
            LOGGER.error("获取网格信息异常", e);
        }
        return result;
    }

    @Override
    public LocGrids getaGrid(String gridId) {
        return locGridsJPA.findOne(Long.parseLong(gridId));
    }

    @Override
    public VReturnMessage addOrUpdateGird(LocGrids locGrids) {
        boolean isInsert = false;
        if (locGrids.getId() == null){
            isInsert = true;
        }
        try {
            if (!StringUtils.isNumeric(locGrids.getCode())) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"网格编码只能为数字");
            }
            // 综治网格编码验证
            if (locGrids.getGridType() == 1) {
                int codeLength = locGrids.getCode().length();
                if (locGrids.getCode() == null || (locGrids.getLvl() == 1 && codeLength != 4)
                        || (locGrids.getLvl() == 2 && codeLength != 6) ||
                        (locGrids.getLvl() == 3 && codeLength != 9) || (locGrids.getLvl() == 4 && codeLength != 12)) {
                    return new VReturnMessage(false,VReturnMessage.WARN_CODE,"网格编码长度不正确");
                }
                if (locGridsJPA.checkCodePrefix(locGrids.getParentId(), locGrids.getCode()) == 0) {
                    return new VReturnMessage(false,VReturnMessage.WARN_CODE,"网格编码前缀不符合上级网格编码");
                }
            }
            List<LocGrids> listByCode = locGridsJPA.checkRepeatByCode(locGrids.getCode());
            if (null != listByCode && listByCode.size() > 0
                    && (null == locGrids.getId() || !listByCode.get(0).getId().equals(locGrids.getId()))) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"网格编码已存在");
            }

            if (locGrids.getName() == null || locGrids.getName().trim().length() == 0) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"网格名称不能为空");
            }
            List<LocGrids> list = locGridsJPA.getNameByParentId(locGrids.getParentId());
            if (list != null && list.size() != 0) {
                for (LocGrids grid : list) {
                    if (locGrids.getName().equals(grid.getName())
                            && (null == locGrids.getId() || !grid.getId().equals(locGrids.getId()))) {
                        return new VReturnMessage(false,VReturnMessage.WARN_CODE,"网格名称已存在");
                    }
                }
            }
            locGridsJPA.save(locGrids);
            return new VReturnMessage(true,VReturnMessage.SUCCESS_CODE,isInsert?"新增成功":"修改成功");
        } catch (Exception e) {
            LOGGER.error("新增或修改网格信息异常", e);
            return new VReturnMessage(false,VReturnMessage.ERROR_CODE,isInsert?"新增异常":"修改异常");
        }
    }

    @Override
    public VReturnMessage removeGrid(String[] idArray) {
        for (int i = 0; i < idArray.length; i++) {
            List<LocGrids> childrenGrids = locGridsJPA.hasChildrenGrids(Long.valueOf(idArray[i]));
            if (null != childrenGrids && childrenGrids.size() > 0) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"当前所选网格存在下一级网格");
            }
        }
        try {
            Arrays.asList(idArray).forEach(gridId -> {
                locGridsJPA.removeGrid(Long.valueOf(gridId));
            });
            return new VReturnMessage(true,VReturnMessage.SUCCESS_CODE,"删除成功");
        } catch (Exception e) {
            LOGGER.error("删除网格信息异常", e);
            return new VReturnMessage(false,VReturnMessage.ERROR_CODE,"删除网格信息异常");
        }
    }

    @Override
    public List<LocGridTypes> getAllGridTypes() {
        return locGridTypesJPA.findAll();
    }

    @Override
    public List<LocGrids> getGridsByLvl(String level) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(level);
        List<LocGrids> locGrids = new ArrayList<>();
        if (isNum.matches()) {
            Long iLevel = Long.valueOf(level);
            if (iLevel > 1L) {
                locGrids = locGridsJPA.getGridsByLevel(iLevel - 1);
            }
        }
        return locGrids;
    }
}
