/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service.impl;

import net.evecom.gsmp.core.service.BaseServiceImpl;
import net.evecom.gsmp.gidcms.geography.dao.LocDistrictDetailsJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocDistrictDetails;
import net.evecom.gsmp.gidcms.geography.service.LocDistrictDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区划介绍服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:29
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Service
@Transactional
public class LocDistrictDetailsServiceImpl
        extends BaseServiceImpl<LocDistrictDetails, Long> implements LocDistrictDetailsService {
    /**
     * 区划介绍JPA
     */
    @Autowired
    LocDistrictDetailsJPA locDistrictDetailsJPA;

    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocDistrictDetailsServiceImpl.class);
    @Override
    public LocDistrictDetails getLocDistrictsDetail(LocDistrictDetails condition) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<LocDistrictDetails> example = Example.of(condition, matcher);
        return locDistrictDetailsJPA.findOne(example);
    }
}
