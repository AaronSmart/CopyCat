/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 统一用户管理系统Model：系统信息
 * @author Iverson Cai
 * @created 2017 /08/28 11:40:50
 */
public class Application {

    /**
     * id
     */
    private String id;
    /**
     * 系统名称中文
     */
    private String label;
    /**
     * 系统名称英文
     */
    private String name;
    /**
     * clientId
     */
    private String clientId;
    /**
     * clientSecret
     */
    private String clientSecret;
    /**
     * 操作权限
     */
    private List<Operation> operations;

    /**
     * 描述 Gets id.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 描述 Gets label.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 描述 Sets label.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 描述 Gets name.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets client id.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @return the client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 描述 Sets client id.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @param clientId the client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 描述 Gets client secret.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @return the client secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 描述 Sets client secret.
     * @author Iverson Cai
     * @created 2017 /08/28 11:40:50
     * @param clientSecret the client secret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
