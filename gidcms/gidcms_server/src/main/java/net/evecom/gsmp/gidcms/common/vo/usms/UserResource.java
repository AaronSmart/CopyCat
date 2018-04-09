/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.vo.usms;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 用户信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /10/09 08:52:33
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class UserResource {
    /**
     * id
     */
    private Long id;
    /**
     * 登陆名
     */
    private String loginName = "";
    /**
     * 名字
     */
    private String name = "";
    /**
     * 机构id
     */
    private String orgId;
    /**
     * 机构名字
     */
    private String orgName = "";
    /**
     * 机构标签
     */
    private String orgLabel = "";
    /**
     * 管理人员
     */
    private StaffBean staff;
    /**
     * 机构
     */
    List<Institutions> institutions;
    /**
     * 应用信息
     */
    private Application application;
    /**
     * 角色信息
     */
    private List<Role> roles;
    /**
     * 附加条件
     */
    private Map<String, String> addition = new HashMap();


    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /10/09 08:52:33
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /10/09 08:52:33
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets login name.
     *
     * @return the login name
     * @author Nick Lv
     * @created 2017 /10/09 08:52:33
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * 描述 Sets login name.
     *
     * @param loginName the login name
     * @author Nick Lv
     * @created 2017 /10/09 08:52:33
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 描述 Gets staff.
     *
     * @return the staff
     * @author Nick Lv
     * @created 2017 /10/09 08:52:33
     */
    public StaffBean getStaff() {
        return this.staff;
    }

    /**
     * 描述 Sets staff.
     *
     * @param staff the staff
     * @author Nick Lv
     * @created 2017 /10/09 08:52:33
     */
    public void setStaff(StaffBean staff) {
        this.staff = staff;
    }

    /**
     * 描述 Gets institutions.
     *
     * @return the institutions
     * @author Nick Lv
     * @created 2017 /10/09 08:52:33
     */
    public List<Institutions> getInstitutions() {
        return this.institutions;
    }

    /**
     * 描述 Sets institutions.
     *
     * @param institutions the institutions
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setInstitutions(List<Institutions> institutions) {
        this.institutions = institutions;
    }

    /**
     * 描述 Gets org id.
     *
     * @return the org id
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public String getOrgId() {
        StringBuilder result = new StringBuilder();
        Iterator var2 = this.getInstritution().iterator();

        while (var2.hasNext()) {
            Institutions institutions = (Institutions) var2.next();
            if (!result.toString().contains(String.valueOf(institutions.getId()))) {
                result.append(institutions.getId() + ",");
            }
        }

        String subResult = "";
        if (!StringUtils.isEmpty(result.toString())) {
            subResult = result.substring(0, result.length() - 1);
        }

        return subResult;
    }

    /**
     * 描述 Sets org id.
     *
     * @param orgId the org id
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public String getName() {
        return this.name;
    }

    /**
     * 描述 Sets name.
     *
     * @param name the name
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets org name.
     *
     * @return the org name
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public String getOrgName() {
        StringBuilder result = new StringBuilder();
        Iterator var2 = this.getInstritution().iterator();

        while (var2.hasNext()) {
            Institutions institutions = (Institutions) var2.next();
            if (!result.toString().contains(institutions.getLabel())) {
                result.append(institutions.getName() + ",");
            }
        }

        String subResult = "";
        if (!StringUtils.isEmpty(result.toString())) {
            subResult = result.substring(0, result.length() - 1);
        }

        return subResult;
    }

    /**
     * 描述 Sets org name.
     *
     * @param orgName the org name
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 描述 Gets instritution.
     *
     * @return the instritution
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    private List<Institutions> getInstritution() {
        List<Institutions> result = new ArrayList();
        if (this.institutions == null) {
            return result;
        } else {
            Iterator var2 = this.institutions.iterator();

            while (var2.hasNext()) {
                Institutions institutions = (Institutions) var2.next();
                result.add(institutions);
            }

            return result;
        }
    }

    /**
     * 描述 Gets application.
     *
     * @return the application
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public Application getApplication() {
        return this.application;
    }

    /**
     * 描述 Sets application.
     *
     * @param application the application
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * 描述 Gets roles.
     *
     * @return the roles
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public List<Role> getRoles() {
        return this.roles;
    }

    /**
     * 描述 Sets roles.
     *
     * @param roles the roles
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 描述 Gets org label.
     *
     * @return the org label
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public String getOrgLabel() {
        StringBuilder result = new StringBuilder();
        Iterator var2 = this.getInstritution().iterator();

        while (var2.hasNext()) {
            Institutions institutions = (Institutions) var2.next();
            if (!result.toString().contains(institutions.getLabel())) {
                result.append(institutions.getLabel() + ",");
            }
        }

        String subResult = "";
        if (!StringUtils.isEmpty(result.toString())) {
            subResult = result.substring(0, result.length() - 1);
        }

        return subResult;
    }

    /**
     * 描述 Sets org label.
     *
     * @param orgLabel the org label
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setOrgLabel(String orgLabel) {
        this.orgLabel = orgLabel;
    }

    /**
     * 描述 Gets addition.
     *
     * @return the addition
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public Map<String, String> getAddition() {
        return this.addition;
    }

    /**
     * 描述 Sets addition.
     *
     * @param addition the addition
     * @author Nick Lv
     * @created 2017 /10/09 08:52:34
     */
    public void setAddition(Map<String, String> addition) {
        this.addition = addition;
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



