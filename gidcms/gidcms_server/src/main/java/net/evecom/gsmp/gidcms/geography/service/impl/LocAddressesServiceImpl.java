/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.service.impl;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.evecom.gsmp.core.service.BaseServiceImpl;
import net.evecom.gsmp.core.utils.CommUtil;
import net.evecom.gsmp.core.utils.IStringUtils;
import net.evecom.gsmp.core.utils.VReturnMessage;
import net.evecom.gsmp.core.web.SimplePageRequestBody;
import net.evecom.gsmp.gidcms.geography.dao.LocAddressesJPA;
import net.evecom.gsmp.gidcms.geography.entity.LocAddresses;
import net.evecom.gsmp.gidcms.geography.entity.LocStreets;
import net.evecom.gsmp.gidcms.geography.service.LocAddressesService;
import net.evecom.gsmp.gidcms.geography.service.LocStreetsService;
import net.evecom.gsmp.gidcms.geography.utils.DistrictEnum;
import net.evecom.gsmp.core.annotation.GridTableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 地址信息服务层
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/23 10:35:29
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
@Service
@Transactional
public class LocAddressesServiceImpl extends BaseServiceImpl<LocAddresses, Long> implements LocAddressesService {
    /**
     * 地址信息jpa
     */
    @Autowired
    LocAddressesJPA locAddressesJPA;
    /**
     * The Loc streets service.
     */
    @Autowired
    private LocStreetsService locStreetsService;
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LocAddressesServiceImpl.class);

    /**
     * 描述 Gets md 5.
     *
     * @param name
     *            the name
     * @return the md 5
     * @author Submarine Lin
     * @created 2017 /11/07 10:36:34
     */
    private String getMd5(String name) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(name.getBytes("UTF-8"));
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            LOGGER.error("获取MD5错误", e);
            return null;
        }
    }

    /**
     * 创建点.
     *
     * @param longitude
     *            the longitude
     * @param latitude
     *            the latitude
     * @return the geometry
     * @throws ParseException
     *             the parse exception
     * @author Nick Lv
     * @created 2017 /06/11 16:33:14 Create point geometry.
     */
    private Geometry createPoint(Double longitude, Double latitude) throws ParseException {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("POINT (").append(longitude).append(" ").append(latitude).append(")");
        WKTReader reader = new WKTReader();
        Geometry geometry = reader.read(sBuilder.toString());
        geometry.setSRID(4326);
        return geometry;
    }

    @Override
    public Map<String, Object> getAddresses(SimplePageRequestBody<LocAddresses> body, String level) {
        Map<String, Object> result = new HashMap<>();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("addrName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("districtCode", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIgnorePaths("geoLocation");
        if (null == body.getCondition().getStreetCode()) {
            if (null != body.getCondition().getDistrictCode()) {
                body.getCondition().setDistrictCode(body.getCondition().getDistrictCode().substring(0,
                        DistrictEnum.getSubStringLengthByLvl(Integer.parseInt(level)).getSubStringLength()));
            }
        }
        Example<LocAddresses> example = Example.of(body.getCondition(), matcher);
        result.put("value", locAddressesJPA.findAll(example, body.buildPageable()));
        try {
            result.put("column", GridTableUtil.createGridTable(LocAddresses.class));
        } catch (Exception e) {
            LOGGER.error("获取地址信息异常", e);
        }
        return result;
    }

    @Override
    public Map<String, Object> getAddressesAll(SimplePageRequestBody<LocAddresses> body, String level) {
        Map<String, Object> result = new HashMap<>();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("addrName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("streetCnName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("districtCode", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIgnorePaths("geoLocation");
        if (null == body.getCondition().getStreetCode()) {
            if (null != body.getCondition().getDistrictCode()) {
                body.getCondition().setDistrictCode(body.getCondition().getDistrictCode().substring(0,
                        DistrictEnum.getSubStringLengthByLvl(Integer.parseInt(level)).getSubStringLength()));
            }
        }
        Example<LocAddresses> example = Example.of(body.getCondition(), matcher);
        result.put("value", locAddressesJPA.findAll(example, body.buildPageable()));
        try {
            result.put("column", GridTableUtil.createGridTable(LocAddresses.class));
        } catch (Exception e) {
            LOGGER.error("获取地址信息异常", e);
        }
        return result;
    }

    @Override
    public LocAddresses getaAddress(String addressId) {
        return locAddressesJPA.findOne(Long.parseLong(addressId));
    }

    @Override
    public VReturnMessage addOrUpdateAddress(LocAddresses locAddresses) {
        boolean isAdd = locAddresses.getId() == null;
        try {
            // 若街巷编码不为空，则查询该街巷下地名与addrName 相同的地址信息
            // ，若街巷编码为空，则查询该社区下地名与addrName 相同的地址信息
            LocAddresses existLocAddresses = locAddressesJPA.getLocAddressesByAddrNameAndStreetCode(
                    locAddresses.getAddrName(), (locAddresses.getStreetCode() != null ? locAddresses.getStreetCode()
                            : locAddresses.getDistrictCode()));
            if (existLocAddresses != null
                    && (locAddresses.getId() == null || !locAddresses.getId().equals(existLocAddresses.getId()))) {
                return new VReturnMessage(false, VReturnMessage.WARN_CODE, "地址名已经存在");
            }
            if (locAddresses.getUuid() == null) {
                locAddresses.setUuid(CommUtil.uuidGenerator());
            }
            if (null != locAddresses.getLongitude() && null != locAddresses.getLatitude()) {
                locAddresses.setGeoLocation(createPoint(locAddresses.getLongitude(), locAddresses.getLatitude()));
            }
            locAddresses.setAddrMd5(getMd5(locAddresses.getAddrName()));
            locAddressesJPA.save(locAddresses);
            LOGGER.info(isAdd ? "新增成功" : "修改成功");
            return new VReturnMessage(true,VReturnMessage.SUCCESS_CODE,isAdd ? "新增成功" : "修改成功");
        } catch (Exception e) {
            LOGGER.info(isAdd ? "新增异常" : "修改异常",e);
            return new VReturnMessage(false,VReturnMessage.ERROR_CODE,isAdd ? "新增异常" : "修改异常");
        }
    }

    @Override
    public VReturnMessage addOrUpdateAddressAndStreet(LocAddresses locAddresses) {
        if (locAddresses.getStreetCode() == null && IStringUtils.isNotEmpty(locAddresses.getStreetCnName(), true)) {
            LocStreets locStreets = new LocStreets();
            locStreets.setCommunityCode(locAddresses.getDistrictCode());
            locStreets.setCommunityName(locAddresses.getDistrictCnName());
            locStreets.setStreetName(locAddresses.getStreetCnName());
            VReturnMessage<LocStreets> vReturnMessage = locStreetsService.addStreet(locStreets);
            if (!vReturnMessage.isResult()) {
                return new VReturnMessage(false, VReturnMessage.WARN_CODE, "新增门楼牌的街巷错误,"
                        + vReturnMessage.getMessage());
            }
            locAddresses.setStreetCode(vReturnMessage.getDataT().getStreetCode());
        }
        return addOrUpdateAddress(locAddresses);
    }

    @Override
    public VReturnMessage removeAddresses(String[] idArray) {
        try {
            Arrays.asList(idArray).forEach(address -> {
                locAddressesJPA.removeAddresses(Long.valueOf(address));
            });
            return new VReturnMessage(true,VReturnMessage.SUCCESS_CODE,"删除成功");
        } catch (Exception e) {
            LOGGER.error("删除地址信息异常", e);
            return new VReturnMessage(false,VReturnMessage.ERROR_CODE,"删除异常");
        }
    }
}
