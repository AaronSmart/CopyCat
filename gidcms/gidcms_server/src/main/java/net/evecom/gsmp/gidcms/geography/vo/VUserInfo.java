/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 用户基础信息对象
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /06/01 10:31:01
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class VUserInfo {
    /**
     * 用户ID
     */
    private String id;
    /**
     * 登录名.
     */
    private String loginName;
    /**
     * 关联员工的具体信息
     */
    private VStaff staff;

    /**
     * 该用户所属的组织机构信息
     */
    private List<VInstitutions> institutions;
    /**
     * 该用户所拥有的操作信息
     */
    private List<VOperations> operations;
    /**
     * 当前接入系统的应用信息，里面包含着操作信息
     */
    private List<VApplications> applications;

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /06/01 10:31:01
     */
    public String getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /06/01 10:31:01
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 描述 Gets login name.
     *
     * @return the login name
     * @author Nick Lv
     * @created 2017 /06/01 10:31:01
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 描述 Sets login name.
     *
     * @param loginName the login name
     * @author Nick Lv
     * @created 2017 /06/01 10:31:02
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 描述 Gets staff.
     *
     * @return the staff
     * @author Nick Lv
     * @created 2017 /06/01 10:31:02
     */
    public VStaff getStaff() {
        return staff;
    }

    /**
     * 描述 Sets staff.
     *
     * @param staff the staff
     * @author Nick Lv
     * @created 2017 /06/01 10:31:02
     */
    public void setStaff(VStaff staff) {
        this.staff = staff;
    }

    /**
     * 描述 Gets operations.
     *
     * @return the operations
     * @author Nick Lv
     * @created 2017 /06/01 10:31:02
     */
    public List<VOperations> getOperations() {
        return operations;
    }

    /**
     * 描述 Sets operations.
     *
     * @param operations the operations
     * @author Nick Lv
     * @created 2017 /06/01 10:31:02
     */
    public void setOperations(List<VOperations> operations) {
        this.operations = operations;
    }

    /**
     * 描述 Gets applications.
     *
     * @return the applications
     * @author Nick Lv
     * @created 2017 /06/01 10:31:02
     */
    public List<VApplications> getApplications() {
        return applications;
    }

    /**
     * 描述 Sets applications.
     *
     * @param applications the applications
     * @author Nick Lv
     * @created 2017 /06/01 10:31:02
     */
    public void setApplications(List<VApplications> applications) {
        this.applications = applications;
    }

    /**
     * 描述 Gets institutions.
     *
     * @return the institutions
     * @author Nick Lv
     * @created 2017 /06/02 10:27:39
     */
    public List<VInstitutions> getInstitutions() {
        return institutions;
    }

    /**
     * 描述 Sets institutions.
     *
     * @param institutions the institutions
     * @author Nick Lv
     * @created 2017 /06/02 10:27:39
     */
    public void setInstitutions(List<VInstitutions> institutions) {
        this.institutions = institutions;
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



