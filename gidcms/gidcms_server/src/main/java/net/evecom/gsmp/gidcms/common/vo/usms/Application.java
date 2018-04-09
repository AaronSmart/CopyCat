/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.vo.usms;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 所属应用
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /10/09 08:43:19
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class Application {
    /**
     * id
     */
    private Long id;
    /**
     * 标签
     */
    private String label;
    /**
     * 名字
     */
    private String name;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 操作集合
     */
    private List<Operations> operations;


    /**
     * 描述 Gets label.
     *
     * @return the label
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * 描述 Sets label.
     *
     * @param label the label
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public String getName() {
        return this.name;
    }

    /**
     * 描述 Sets name.
     *
     * @param name the name
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets client id.
     *
     * @return the client id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public String getClientId() {
        return this.clientId;
    }

    /**
     * 描述 Sets client id.
     *
     * @param clientId the client id
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 描述 Gets client secret.
     *
     * @return the client secret
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public String getClientSecret() {
        return this.clientSecret;
    }

    /**
     * 描述 Sets client secret.
     *
     * @param clientSecret the client secret
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * 描述 Gets operations.
     *
     * @return the operations
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public List<Operations> getOperations() {
        return this.operations;
    }

    /**
     * 描述 Sets operations.
     *
     * @param operations the operations
     * @author Nick Lv
     * @created 2017 /10/09 08:43:19
     */
    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }
    /**
     * 描述 override toString.
     *
     * @author Submarine Lin
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 描述 override equals.
     *
     * @author Submarine Lin
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * 描述 override hashCode.
     *
     * @author Submarine Lin
     * @created 2017 /04/24 10:08:43
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}



