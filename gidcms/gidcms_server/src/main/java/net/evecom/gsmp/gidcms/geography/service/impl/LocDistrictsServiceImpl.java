/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service.impl;

import net.evecom.gsmp.core.service.BaseServiceImpl;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.dao.LocDistrictsJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocDistricts;
import net.evecom.gsmp.gidcms.geography.service.LocDistrictsService;
import net.evecom.gsmp.core.annotation.GridTableUtil;
import net.evecom.gsmp.gidcms.geography.vo.VDistrictTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区划信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:29
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Service
@Transactional
public class LocDistrictsServiceImpl extends BaseServiceImpl<LocDistricts, Long> implements LocDistrictsService {
    /**
     * 区划jpa
     */
    @Autowired
    LocDistrictsJPA locDistrictsJPA;
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocDistrictsServiceImpl.class);

    @Override
    public Map<String, Object> getLocDistricts(SimplePageRequestBody<LocDistricts> body) {
        Map<String, Object> result = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.DESC, "dateModified");
        Pageable pageable = new PageRequest(body.getPage(), body.getSize(), sort);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("districtName", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<LocDistricts> example = Example.of(body.getCondition(), matcher);
        result.put("value", locDistrictsJPA.findAll(example, pageable));
        try {
            result.put("column", GridTableUtil.createGridTable(LocDistricts.class));
        } catch (Exception e) {
            LOGGER.error("获取网格数据异常");
        }
        return result;
    }

    @Override
    public LocDistricts getaLocDistrict(String districtId) {
        return locDistrictsJPA.findOne(Long.parseLong(districtId));
    }


    @Override
    public List<LocDistricts> getAllDistricts(String level) {
        LocDistricts condition = new LocDistricts();
        condition.setDistrictLvl(level);
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<LocDistricts> example = Example.of(condition, matcher);
        return locDistrictsJPA.findAll(example);
    }


    @Override
    public List<VDistrictTree> getDistrictsByParentId(String parentId) {
        List<VDistrictTree> result = new ArrayList<>();
        if ("".equals(parentId)) {
            locDistrictsJPA.getDistrictsNoParentId().forEach(district -> {
                VDistrictTree vDistrictTree = new VDistrictTree();
                vDistrictTree.setLabel(district.getDistrictName());
                vDistrictTree.setData(String.valueOf(district.getId()));
                vDistrictTree.setDistrictCode(district.getDistrictCode());
                vDistrictTree.setLevel(district.getDistrictLvl());
                vDistrictTree.setExpanded(true);
                vDistrictTree.setNamePath(district.getNamePath());
                List<VDistrictTree> childTrees = new ArrayList<>();
                locDistrictsJPA.getDistrictsByLevel(district.getId()).forEach(aDistrict -> {
                    VDistrictTree childTree = new VDistrictTree();
                    childTree.setLabel(aDistrict.getDistrictName());
                    childTree.setData(String.valueOf(aDistrict.getId()));
                    childTree.setDistrictCode(aDistrict.getDistrictCode());
                    childTree.setLevel(aDistrict.getDistrictLvl());
                    childTree.setNamePath(aDistrict.getNamePath());
                    childTrees.add(childTree);
                });
                vDistrictTree.setChildren(childTrees);
                result.add(vDistrictTree);
            });
        } else {
            locDistrictsJPA.getDistrictsByLevel(Long.parseLong(parentId)).forEach(district -> {
                VDistrictTree vDistrictTree = new VDistrictTree();
                vDistrictTree.setLabel(district.getDistrictName());
                vDistrictTree.setData(String.valueOf(district.getId()));
                vDistrictTree.setDistrictCode(district.getDistrictCode());
                vDistrictTree.setLevel(district.getDistrictLvl());
                vDistrictTree.setNamePath(district.getNamePath());
                result.add(vDistrictTree);
            });
        }
        return result;
    }

    @Override
    @Deprecated
    public List<VDistrictTree> getDistrictsForAddrByParentId(String parentId) {
        List<VDistrictTree> result = new ArrayList<>();
        if ("".equals(parentId)) {
            locDistrictsJPA.getDistrictsNoParentId().forEach(district -> {
                VDistrictTree vDistrictTree = new VDistrictTree();

                vDistrictTree.setLabel(district.getDistrictName());
                vDistrictTree.setData(String.valueOf(district.getId()));
                vDistrictTree.setDistrictCode(district.getDistrictCode());
                vDistrictTree.setSelectable(false);
                vDistrictTree.setNamePath(district.getNamePath());
                result.add(vDistrictTree);
            });
        } else {
            locDistrictsJPA.getDistrictsByLevel(Long.parseLong(parentId)).forEach(district -> {
                VDistrictTree vDistrictTree = new VDistrictTree();
                vDistrictTree.setLabel(district.getDistrictName());
                vDistrictTree.setData(String.valueOf(district.getId()));
                vDistrictTree.setDistrictCode(district.getDistrictCode());
                vDistrictTree.setNamePath(district.getNamePath());
                if (Long.parseLong(district.getDistrictLvl()) < 5) {
                    vDistrictTree.setSelectable(false);
                }
                result.add(vDistrictTree);
            });
        }
        return result;
    }

    @Override
    public List<VDistrictTree> getDistrictsForGridByParentId(String parentId) {
        List<VDistrictTree> result = new ArrayList<>();
        if ("".equals(parentId)) {
            Long pingtanId = 12522L;
            LocDistricts locDistricts = locDistrictsJPA.findOne(pingtanId);
            VDistrictTree vDistrictTree = new VDistrictTree();
            vDistrictTree.setLabel(locDistricts.getDistrictName());
            vDistrictTree.setLevel(locDistricts.getDistrictLvl());
            vDistrictTree.setExpanded(true);
            vDistrictTree.setData(String.valueOf(locDistricts.getId()));
            vDistrictTree.setNamePath(locDistricts.getNamePath());
            vDistrictTree.setDistrictCode(locDistricts.getDistrictCode());
            List<VDistrictTree> childTrees = new ArrayList<>();
            locDistrictsJPA.getDistrictsByLevel(locDistricts.getId()).forEach(aDistrict -> {
                VDistrictTree childTree = new VDistrictTree();
                childTree.setLabel(aDistrict.getDistrictName());
                childTree.setData(String.valueOf(aDistrict.getId()));
                childTree.setLevel(aDistrict.getDistrictLvl());
                childTree.setNamePath(aDistrict.getNamePath());
                childTree.setDistrictCode(aDistrict.getDistrictCode());
                childTrees.add(childTree);
            });
            vDistrictTree.setChildren(childTrees);
            result.add(vDistrictTree);
        } else {
            locDistrictsJPA.getDistrictsByLevel(Long.parseLong(parentId)).forEach(district -> {
                VDistrictTree vDistrictTree = new VDistrictTree();
                vDistrictTree.setLabel(district.getDistrictName());
                vDistrictTree.setData(String.valueOf(district.getId()));
                vDistrictTree.setDistrictCode(String.valueOf(district.getDistrictCode()));
                vDistrictTree.setLevel(district.getDistrictLvl());
                vDistrictTree.setNamePath(district.getNamePath());
                if ("6".equals(district.getDistrictLvl())) {
                    vDistrictTree.setLeaf(true);
                }
                result.add(vDistrictTree);
            });
        }
        return result;
    }

    @Override
    public List<LocDistricts> getDistrictsByTown(String districtCode) {
        // 先通过code找到自己的id
        ExampleMatcher tempMatcher = ExampleMatcher.matching();
        LocDistricts tempDistrict = new LocDistricts();
        tempDistrict.setDistrictCode(districtCode);
        Example<LocDistricts> tempExample = Example.of(tempDistrict, tempMatcher);
        LocDistricts locDistricts = locDistrictsJPA.findOne(tempExample);
        LocDistricts condition = new LocDistricts();
        // 用自己的id去找子集合
        condition.setParentId(locDistricts.getId());
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<LocDistricts> example = Example.of(condition, matcher);
        return locDistrictsJPA.findAll(example);
    }
}
