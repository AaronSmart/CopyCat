/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service.impl;


import net.evecom.gsmp.core.service.BaseServiceImpl;
import net.evecom.gsmp.core.utils.CommUtil;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.dao.LocDistrictsJPA;
import net.evecom.gsmp.gidcms.geography.dao.LocStreetsJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocDistricts;
import net.evecom.gsmp.gidcms.geography.entity.LocStreets;
import net.evecom.gsmp.gidcms.geography.service.LocStreetsService;
import net.evecom.gsmp.core.annotation.GridTableUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 街巷信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:29
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Service
@Transactional
public class LocStreetsServiceImpl extends BaseServiceImpl<LocStreets, Long> implements LocStreetsService {
    /**
     * 街巷jpa
     */
    @Autowired
    private LocStreetsJPA locStreetsJPA;

    /**
     * The Loc districts jpa.
     */
    @Autowired
    private LocDistrictsJPA locDistrictsJPA;
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocStreetsServiceImpl.class);

    @Override
    public LocStreets getaStreet(String streetId) {
        return locStreetsJPA.findOne(Long.parseLong(streetId));
    }

    @Override
    public Map<String, Object> getStreets(SimplePageRequestBody<LocStreets> body) {
        Map<String, Object> result = new HashMap<>();

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("streetName", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<LocStreets> example = Example.of(body.getCondition(), matcher);
        result.put("value", locStreetsJPA.findAll(example, body.buildPageable()));
        try {
            result.put("column", GridTableUtil.createGridTable(LocStreets.class));
        } catch (Exception e) {
            LOGGER.error("获取街巷信息异常", e);
        }
        return result;
    }

    @Override
    public VReturnMessage addOrUpdateStreet(LocStreets locStreets) {
        boolean isInsert = false;
        if (locStreets.getId() == null){
            isInsert = true;
        }
        try {
            if (locStreets.getStreetCode() == null || locStreets.getStreetCode().length() == 0) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"编码不能为空");
            }
            if (!StringUtils.isNumeric(locStreets.getStreetCode())) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"编码只能为数字");
            }
            if (locStreets.getStreetCode().length() != 18) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"编码长度不正确");
            }
            if (!locStreets.getStreetCode().substring(0, 12).equals(locStreets.getCommunityCode())) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"编码前缀不符合社区编码");
            }
            List<LocStreets> list = locStreetsJPA.checkRepeatStreetsNoId(locStreets.getStreetCode());
            if (list != null && list.size() != 0) {
                if (null == locStreets.getId() || !locStreets.getId().equals(list.get(0).getId())) {
                    return new VReturnMessage(false,VReturnMessage.WARN_CODE,"编码已存在");
                }
            }
            if (locStreets.getStreetName() == null || locStreets.getStreetName().trim().length() == 0) {
                return new VReturnMessage(false,VReturnMessage.WARN_CODE,"街巷名称不能为空");
            }
            List<LocStreets> locStreetses = locStreetsJPA.getNameByCommunityCode(locStreets.getCommunityCode(),
                    locStreets.getStreetName());
            if (locStreetses != null && locStreetses.size() > 0) {
                for (LocStreets locStreet : locStreetses) {
                    if (null == locStreets.getId() || !locStreets.getId().equals(locStreet.getId())) {
                        return new VReturnMessage(false,VReturnMessage.WARN_CODE,"街巷名称已经存在");
                    }
                }
            }
            if (null == locStreets.getId()) {
                locStreets.setUuid(CommUtil.uuidGenerator());
            }
            locStreets.setIsValid(1);
            locStreetsJPA.save(locStreets);
            return new VReturnMessage(true,VReturnMessage.SUCCESS_CODE,isInsert?"新增成功":"修改成功");
        } catch (Exception e) {
            LOGGER.error("新增或修改街道信息异常", e);
            return new VReturnMessage(false,VReturnMessage.ERROR_CODE,isInsert?"新增异常":"修改异常");
        }
    }

    @Override
    public VReturnMessage<LocStreets> addStreet(LocStreets locStreets) {
        if (StringUtils.isEmpty(locStreets.getStreetName())) {
            return new VReturnMessage(false, VReturnMessage.WARN_CODE, "街巷名称不能为空");
        }
        List<LocStreets> locStreetses = locStreetsJPA.getNameByCommunityCode(locStreets.getCommunityCode(),
                locStreets.getStreetName());
        if (locStreetses != null && locStreetses.size() > 0) {
            return new VReturnMessage(false, VReturnMessage.WARN_CODE, "街巷名称已经存在");
        }
        LocDistricts locDistricts = locDistrictsJPA.getParentDistrictsByCode(locStreets.getCommunityCode());
        locStreets.setTownCode(locDistricts.getDistrictCode());
        locStreets.setTownName(locDistricts.getDistrictName());
        locStreets.setUuid(CommUtil.uuidGenerator());
        String streetCode = locStreetsJPA.buildStreetCodeByCommunityCode(locStreets.getCommunityCode());
        if ("0".equals(streetCode)) {
            return new VReturnMessage(false, VReturnMessage.WARN_CODE, "街巷编码超出范围，请联系管理员");
        }
        locStreets.setStreetCode(streetCode);
        locStreets.setIsValid(1);
        LocStreets saveLocStreets = locStreetsJPA.save(locStreets);
        VReturnMessage<LocStreets> vReturnMessage = new VReturnMessage(true, VReturnMessage.SUCCESS_CODE, "新增成功");
        vReturnMessage.setDataT(saveLocStreets);
        return vReturnMessage;
    }

    @Override
    public VReturnMessage removeStreets(String[] idArray) {
        try {
            Arrays.asList(idArray).forEach(street -> {
                locStreetsJPA.removeStreet(Long.valueOf(street));
            });
            return new VReturnMessage(true, VReturnMessage.SUCCESS_CODE, "删除成功");
        } catch (Exception e) {
            LOGGER.error("删除街道信息异常", e);
            return new VReturnMessage(false, VReturnMessage.ERROR_CODE, "删除异常");
        }
    }

    @Override
    public List<LocStreets> getAllStreetsByDistrictCode(String districtCode, String level) {
        LocStreets condition = new LocStreets();
        if ("6".equals(level) || "06".equals(level)) {
            condition.setCommunityCode(districtCode);
        } else if ("5".equals(level) || "05".equals(level)) {
            condition.setTownCode(districtCode);
           /* LocDistricts locDistricts = new LocDistricts();
            locDistrictsJPA.findAll()*/
        }
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<LocStreets> example = Example.of(condition, matcher);
        return locStreetsJPA.findAll(example);
    }
}
