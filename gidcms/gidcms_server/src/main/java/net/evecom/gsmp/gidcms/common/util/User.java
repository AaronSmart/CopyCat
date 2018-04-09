/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;


import java.util.List;

/**
 * 统一用户管理系统Model：登录用户信息
 *
 * @author Iverson Cai      
 * @created 2017 /05/31 16:02:17
 */
public class User {

    /**
     * id
     */
    Long id;
    /**
     * 登入名
     */
    String loginName;
    /**
     * 姓名
     */
    String name;
    /**
     * 关联的员工信息
     */
    Staff staff;
    /**
     * 所属机构信息
     */
    List<Institution> institutions;
    /**
     * 角色
     */
    List<Role> roles;
    /**
     * 应用信息
     */
    Application application;

    /**
     * 描述 Gets id.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述 Gets login name.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @return the login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 描述 Sets login name.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @param loginName the login name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 描述 Gets name.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets staff.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @return the staff
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * 描述 Sets staff.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @param staff the staff
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     * 描述 Gets institutions.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @return the institutions
     */
    public List<Institution> getInstitutions() {
        return institutions;
    }

    /**
     * 描述 Sets institutions.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @param institutions the institutions
     */
    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }



    /**
     * 描述 Gets roles.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:23 
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * 描述 Sets roles.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:24 
     * @param roles the roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 描述 Gets application.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:24 
     * @return the application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * 描述 Sets application.
     * @author Iverson Cai 
     * @created 2017 /08/28 11:37:24 
     * @param application the application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

}
