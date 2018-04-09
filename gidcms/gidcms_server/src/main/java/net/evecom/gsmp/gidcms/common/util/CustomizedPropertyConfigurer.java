/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import org.apache.log4j.helpers.FileWatchdog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件信息获取类
 * @author Iverson Cai         
 * @created 2017 /09/06 15:11:28
 */
public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {

    /**
     * 保存配置文件中的配置信息
     */
    private static Map<String, String> propertiesMap;

    /**
     * 配置文件信息  set注入
     */
    private Resource[] locations;

    /**
     * 需要注入的常量类名数组
     */
    private String[] configureClasses;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        super.processProperties(beanFactory, props);
        propertiesMap = new HashMap();
        putMap(props);
        setConstants();
        monitorFile();

    }

    /**
     * 描述 监测文件配置信息变化.
     * @author Iverson Cai    
     * @created 2017 /09/06 16:28:50 Monitor file.
     */
    public void monitorFile() {
        //Log4j中的线程狗监测文件变化
        for (Resource location : this.locations) {
            String filepath = "";
            try {
                filepath = location.getFile().getPath();
                new FileWatchdog(filepath) {
                    @Override
                    protected void doOnChange() {
                        Properties p = new Properties();
                        try {
                            loadProperties(p);
                        } catch (Exception e) {
                            logger.error("加载配置文件出错", e);
                        }
                        putMap(p);
                        setConstants();
                    }
                }.start();
            } catch (Exception e) {
                logger.info("FileWatch监测出错异常,文件路径为：" + filepath, e);
            }
        }
    }

    /**
     * 描述 获取配置信息
     * @author Iverson Cai       
     * @created 2017 /09/06 15:11:28       
     * @param key the key       
     * @return the property
     */
    public static String getProperty(String key) {
        return propertiesMap.get(key);
    }

    /**
     * 描述 将配置信息保存到map中.
     * @author Iverson Cai         
     * @created 2017 /09/06 15:11:29 Fill map.         
     * @param props the props
     */
    private void putMap(Properties props) {
        props.forEach((key, value) -> {
            propertiesMap.put(key.toString(), value.toString());
        });
    }

    /**
     * 描述 设置类中的常量值
     * @author Iverson Cai   
     * @created 2017 /09/06 16:36:21
     */
    private void setConstants() {
        for (int i = 0; i < configureClasses.length; i++) {
            Class c;
            try {
                c = Class.forName(configureClasses[i]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            }
            Field[] fields = c.getFields();
            this.propertiesMap.forEach((key, value) -> {
                String keyName = CommUtil.lineToHump(key.toString());
                Arrays.asList(fields).stream().forEach(field -> {
                    String fieldName = field.getName();
                    if (keyName.trim().equals(fieldName.trim())) {
                        try {
                            if (Integer.TYPE.equals(field.getType())) {
                                field.setInt(null, Integer.parseInt(value));
                            } else if (Long.TYPE.equals(field.getType())) {
                                field.setLong(null, Long.parseLong(value));
                            } else if (Short.TYPE.equals(field.getType())) {
                                field.setShort(null, Short.parseShort(value));
                            } else if (Double.TYPE.equals(field.getType())) {
                                field.setDouble(null, Double.parseDouble(value));
                            } else if (Float.TYPE.equals(field.getType())) {
                                field.setFloat(null, Float.parseFloat(value));
                            } else if (Boolean.TYPE.equals(field.getType())) {
                                field.setBoolean(null, Boolean.parseBoolean(value));
                            } else if (Boolean.TYPE.equals(field.getType())) {
                                field.setBoolean(null, Boolean.parseBoolean(value));
                            } else {
                                field.set(null, value);
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
            });
        }
    }

    @Override
    public void setLocations(Resource... locations) {
        super.setLocations(locations);
        this.locations = locations;

    }

    /**
     * 描述 set注入
     * @author Iverson Cai 
     * @created 2017 /09/06 16:43:40 
     * @param configureClasses the configure classes
     */
    public void setConfigureClasses(String[] configureClasses) {
        this.configureClasses = (configureClasses != null)? configureClasses.clone() : null;
    }
    

}
