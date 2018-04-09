/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

import java.util.Date;

/**
 * 统一用户管理系统Model：用户信息
 * @author Iverson Cai  
 * @created 2017 /08/28 11:37:39
 */
public class Staff {

    /**
     * 用户ID
     */
    private String id;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 固定电话
     */
    private String tel;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 手机电话
     */
    private String mobile;
    /**
     * 内网邮箱
     */
    private String email;
    /**
     * 外网邮箱
     */
    private String extranetEmail;
    /**
     * 别名
     */
    private String aliasNames;
    /**
     * 性别
     */
    private String sex;
    /**
     * 职称
     */
    private String professionalTitle;
    /**
     * 职责
     */
    private String officalPost;
    /**
     * 职务
     */
    private String officalDuty;
    /**
     * 员工类型
     */
    private String employeeType;
    /**
     * 员工工号
     */
    private String employeeNo;
    /**
     * 出生日期，注意：这里返回的日期为数值类型的时间戳，需要通过代码转化为日期类型
     */
    private Date birthday;
    /**
     * 居住地行政区划编号
     */
    private String adminDivisionCode;
    /**
     * 居住地行政区划
     */
    private String adminDivision;
    /**
     * 现居住地址
     */
    private String curResidence;
    /**
     * 备注说明
     */
    private String remarks;
    /**
     * 身份证号码
     */
    private String citizenIdNumber;
    /**
     * 照片路径
     */
    private String pictureUrl;

    /**
     * 描述 Gets id.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 描述 Gets login name.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @return the login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 描述 Sets login name.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @param loginName the login name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 描述 Gets name.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets tel.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * 描述 Sets tel.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:39  
     * @param tel the tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 描述 Gets zip code.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 描述 Sets zip code.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 描述 Gets mobile.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 描述 Sets mobile.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @param mobile the mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 描述 Gets email.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 描述 Sets email.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 描述 Gets extranet email.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @return the extranet email
     */
    public String getExtranetEmail() {
        return extranetEmail;
    }

    /**
     * 描述 Sets extranet email.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @param extranetEmail the extranet email
     */
    public void setExtranetEmail(String extranetEmail) {
        this.extranetEmail = extranetEmail;
    }

    /**
     * 描述 Gets alias names.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @return the alias names
     */
    public String getAliasNames() {
        return aliasNames;
    }

    /**
     * 描述 Sets alias names.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @param aliasNames the alias names
     */
    public void setAliasNames(String aliasNames) {
        this.aliasNames = aliasNames;
    }

    /**
     * 描述 Gets sex.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:40  
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 描述 Sets sex.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @param sex the sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 描述 Gets professional title.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @return the professional title
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    /**
     * 描述 Sets professional title.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @param professionalTitle the professional title
     */
    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    /**
     * 描述 Gets offical post.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @return the offical post
     */
    public String getOfficalPost() {
        return officalPost;
    }

    /**
     * 描述 Sets offical post.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @param officalPost the offical post
     */
    public void setOfficalPost(String officalPost) {
        this.officalPost = officalPost;
    }

    /**
     * 描述 Gets offical duty.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @return the offical duty
     */
    public String getOfficalDuty() {
        return officalDuty;
    }

    /**
     * 描述 Sets offical duty.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @param officalDuty the offical duty
     */
    public void setOfficalDuty(String officalDuty) {
        this.officalDuty = officalDuty;
    }

    /**
     * 描述 Gets employee type.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @return the employee type
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * 描述 Sets employee type.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:41  
     * @param employeeType the employee type
     */
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * 描述 Gets employee no.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @return the employee no
     */
    public String getEmployeeNo() {
        return employeeNo;
    }

    /**
     * 描述 Sets employee no.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @param employeeNo the employee no
     */
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    /**
     * 描述 Gets birthday.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 描述 Sets birthday.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @param birthday the birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 描述 Gets admin division code.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @return the admin division code
     */
    public String getAdminDivisionCode() {
        return adminDivisionCode;
    }

    /**
     * 描述 Sets admin division code.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @param adminDivisionCode the admin division code
     */
    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    /**
     * 描述 Gets admin division.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @return the admin division
     */
    public String getAdminDivision() {
        return adminDivision;
    }

    /**
     * 描述 Sets admin division.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @param adminDivision the admin division
     */
    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    /**
     * 描述 Gets cur residence.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @return the cur residence
     */
    public String getCurResidence() {
        return curResidence;
    }

    /**
     * 描述 Sets cur residence.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @param curResidence the cur residence
     */
    public void setCurResidence(String curResidence) {
        this.curResidence = curResidence;
    }

    /**
     * 描述 Gets remarks.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 描述 Sets remarks.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @param remarks the remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 描述 Gets citizen id number.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:42  
     * @return the citizen id number
     */
    public String getCitizenIdNumber() {
        return citizenIdNumber;
    }

    /**
     * 描述 Sets citizen id number.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:43  
     * @param citizenIdNumber the citizen id number
     */
    public void setCitizenIdNumber(String citizenIdNumber) {
        this.citizenIdNumber = citizenIdNumber;
    }

    /**
     * 描述 Gets picture url.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:43  
     * @return the picture url
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * 描述 Sets picture url.
     * @author Iverson Cai  
     * @created 2017 /08/28 11:37:43  
     * @param pictureUrl the picture url
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Staff staff = (Staff) o;

        if (id != null ? !id.equals(staff.id) : staff.id != null)
            return false;
        if (loginName != null ? !loginName.equals(staff.loginName) : staff.loginName != null)
            return false;
        if (name != null ? !name.equals(staff.name) : staff.name != null)
            return false;
        if (tel != null ? !tel.equals(staff.tel) : staff.tel != null)
            return false;
        if (zipCode != null ? !zipCode.equals(staff.zipCode) : staff.zipCode != null)
            return false;
        if (mobile != null ? !mobile.equals(staff.mobile) : staff.mobile != null)
            return false;
        if (email != null ? !email.equals(staff.email) : staff.email != null)
            return false;
        if (extranetEmail != null ? !extranetEmail.equals(staff.extranetEmail) : staff.extranetEmail != null)
            return false;
        if (aliasNames != null ? !aliasNames.equals(staff.aliasNames) : staff.aliasNames != null)
            return false;
        if (sex != null ? !sex.equals(staff.sex) : staff.sex != null)
            return false;
        if (professionalTitle != null ? !professionalTitle.equals(staff.professionalTitle)
                : staff.professionalTitle != null)
            return false;
        if (officalPost != null ? !officalPost.equals(staff.officalPost) : staff.officalPost != null)
            return false;
        if (officalDuty != null ? !officalDuty.equals(staff.officalDuty) : staff.officalDuty != null)
            return false;
        if (employeeType != null ? !employeeType.equals(staff.employeeType) : staff.employeeType != null)
            return false;
        if (employeeNo != null ? !employeeNo.equals(staff.employeeNo) : staff.employeeNo != null)
            return false;
        if (birthday != null ? !birthday.equals(staff.birthday) : staff.birthday != null)
            return false;
        if (adminDivisionCode != null ? !adminDivisionCode.equals(staff.adminDivisionCode)
                : staff.adminDivisionCode != null)
            return false;
        if (adminDivision != null ? !adminDivision.equals(staff.adminDivision) : staff.adminDivision != null)
            return false;
        if (curResidence != null ? !curResidence.equals(staff.curResidence) : staff.curResidence != null)
            return false;
        if (remarks != null ? !remarks.equals(staff.remarks) : staff.remarks != null)
            return false;
        if (citizenIdNumber != null ? !citizenIdNumber.equals(staff.citizenIdNumber) : staff.citizenIdNumber != null)
            return false;
        return pictureUrl != null ? pictureUrl.equals(staff.pictureUrl) : staff.pictureUrl == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (extranetEmail != null ? extranetEmail.hashCode() : 0);
        result = 31 * result + (aliasNames != null ? aliasNames.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (professionalTitle != null ? professionalTitle.hashCode() : 0);
        result = 31 * result + (officalPost != null ? officalPost.hashCode() : 0);
        result = 31 * result + (officalDuty != null ? officalDuty.hashCode() : 0);
        result = 31 * result + (employeeType != null ? employeeType.hashCode() : 0);
        result = 31 * result + (employeeNo != null ? employeeNo.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (adminDivisionCode != null ? adminDivisionCode.hashCode() : 0);
        result = 31 * result + (adminDivision != null ? adminDivision.hashCode() : 0);
        result = 31 * result + (curResidence != null ? curResidence.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (citizenIdNumber != null ? citizenIdNumber.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" + "id='" + id + '\'' + ", loginName='" + loginName + '\'' + ", name='" + name + '\''
                + ", tel='" + tel + '\'' + ", zipCode='" + zipCode + '\'' + ", mobile='" + mobile + '\'' + ", email='"
                + email + '\'' + ", extranetEmail='" + extranetEmail + '\'' + ", aliasNames='" + aliasNames + '\''
                + ", sex='" + sex + '\'' + ", professionalTitle='" + professionalTitle + '\'' + ", officalPost='"
                + officalPost + '\'' + ", officalDuty='" + officalDuty + '\'' + ", employeeType='" + employeeType
                + '\'' + ", employeeNo='" + employeeNo + '\'' + ", birthday=" + birthday + ", adminDivisionCode='"
                + adminDivisionCode + '\'' + ", adminDivision='" + adminDivision + '\'' + ", curResidence='"
                + curResidence + '\'' + ", remarks='" + remarks + '\'' + ", citizenIdNumber='" + citizenIdNumber
                + '\'' + ", pictureUrl='" + pictureUrl + '\'' + '}';
    }
}
