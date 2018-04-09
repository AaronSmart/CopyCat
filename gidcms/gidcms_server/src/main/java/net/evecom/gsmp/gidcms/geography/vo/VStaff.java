/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.geography.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 基础信息
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /06/01 10:31:01
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class VStaff {
    /**
     * 员工ID
     */
    private long id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 固定电话号
     */
    private String tel;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 内网邮箱
     */
    private String email;
    /**
     * 外网邮箱
     */
    private String extranetEmail;
    /**
     * 别名,多个用逗号隔开
     */
    private String aliasNames;
    /**
     * 性别  1;男  2:女
     */
    private Long sex;
    /**
     * 职称
     */
    private String professionalTitle;
    /**
     * 职务
     */
    private String officalPost;
    /**
     * 职责
     */
    private String officalDuty;
    /**
     * 员工类别
     */
    private String employeeType;
    /**
     * 员工工号
     */
    private String employeeNo;
    /**
     * 身份证号码
     */
    private String citizenIdNumber;
    /**
     * 出生日期
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
     * 照片地址
     */
    private String pictureUrl;

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public long getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 描述 Gets name.
     *
     * @return the name
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public String getName() {
        return name;
    }

    /**
     * 描述 Sets name.
     *
     * @param name the name
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述 Gets tel.
     *
     * @return the tel
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public String getTel() {
        return tel;
    }

    /**
     * 描述 Sets tel.
     *
     * @param tel the tel
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 描述 Gets mobile.
     *
     * @return the mobile
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 描述 Sets mobile.
     *
     * @param mobile the mobile
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 描述 Gets zip code.
     *
     * @return the zip code
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 描述 Sets zip code.
     *
     * @param zipCode the zip code
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 描述 Gets email.
     *
     * @return the email
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public String getEmail() {
        return email;
    }

    /**
     * 描述 Sets email.
     *
     * @param email the email
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 描述 Gets extranet email.
     *
     * @return the extranet email
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public String getExtranetEmail() {
        return extranetEmail;
    }

    /**
     * 描述 Sets extranet email.
     *
     * @param extranetEmail the extranet email
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public void setExtranetEmail(String extranetEmail) {
        this.extranetEmail = extranetEmail;
    }

    /**
     * 描述 Gets alias names.
     *
     * @return the alias names
     * @author Nick Lv
     * @created 2017 /06/02 11:08:22
     */
    public String getAliasNames() {
        return aliasNames;
    }

    /**
     * 描述 Sets alias names.
     *
     * @param aliasNames the alias names
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public void setAliasNames(String aliasNames) {
        this.aliasNames = aliasNames;
    }

    /**
     * 描述 Gets sex.
     *
     * @return the sex
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public Long getSex() {
        return sex;
    }

    /**
     * 描述 Sets sex.
     *
     * @param sex the sex
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public void setSex(Long sex) {
        this.sex = sex;
    }

    /**
     * 描述 Gets professional title.
     *
     * @return the professional title
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    /**
     * 描述 Sets professional title.
     *
     * @param professionalTitle the professional title
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    /**
     * 描述 Gets offical post.
     *
     * @return the offical post
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public String getOfficalPost() {
        return officalPost;
    }

    /**
     * 描述 Sets offical post.
     *
     * @param officalPost the offical post
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public void setOfficalPost(String officalPost) {
        this.officalPost = officalPost;
    }

    /**
     * 描述 Gets offical duty.
     *
     * @return the offical duty
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public String getOfficalDuty() {
        return officalDuty;
    }

    /**
     * 描述 Sets offical duty.
     *
     * @param officalDuty the offical duty
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public void setOfficalDuty(String officalDuty) {
        this.officalDuty = officalDuty;
    }

    /**
     * 描述 Gets employee type.
     *
     * @return the employee type
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * 描述 Sets employee type.
     *
     * @param employeeType the employee type
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * 描述 Gets employee no.
     *
     * @return the employee no
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public String getEmployeeNo() {
        return employeeNo;
    }

    /**
     * 描述 Sets employee no.
     *
     * @param employeeNo the employee no
     * @author Nick Lv
     * @created 2017 /06/02 11:08:23
     */
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    /**
     * 描述 Gets citizen id number.
     *
     * @return the citizen id number
     * @author Nick Lv
     * @created 2017 /06/02 11:08:26
     */
    public String getCitizenIdNumber() {
        return citizenIdNumber;
    }

    /**
     * 描述 Sets citizen id number.
     *
     * @param citizenIdNumber the citizen id number
     * @author Nick Lv
     * @created 2017 /06/02 11:08:26
     */
    public void setCitizenIdNumber(String citizenIdNumber) {
        this.citizenIdNumber = citizenIdNumber;
    }

    /**
     * 描述 Gets birthday.
     *
     * @return the birthday
     * @author Nick Lv
     * @created 2017 /06/02 11:08:26
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 描述 Sets birthday.
     *
     * @param birthday the birthday
     * @author Nick Lv
     * @created 2017 /06/02 11:08:26
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 描述 Gets admin division code.
     *
     * @return the admin division code
     * @author Nick Lv
     * @created 2017 /06/02 11:08:26
     */
    public String getAdminDivisionCode() {
        return adminDivisionCode;
    }

    /**
     * 描述 Sets admin division code.
     *
     * @param adminDivisionCode the admin division code
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    /**
     * 描述 Gets admin division.
     *
     * @return the admin division
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public String getAdminDivision() {
        return adminDivision;
    }

    /**
     * 描述 Sets admin division.
     *
     * @param adminDivision the admin division
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    /**
     * 描述 Gets cur residence.
     *
     * @return the cur residence
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public String getCurResidence() {
        return curResidence;
    }

    /**
     * 描述 Sets cur residence.
     *
     * @param curResidence the cur residence
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public void setCurResidence(String curResidence) {
        this.curResidence = curResidence;
    }

    /**
     * 描述 Gets remarks.
     *
     * @return the remarks
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 描述 Sets remarks.
     *
     * @param remarks the remarks
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 描述 Gets picture url.
     *
     * @return the picture url
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * 描述 Sets picture url.
     *
     * @param pictureUrl the picture url
     * @author Nick Lv
     * @created 2017 /06/02 11:08:27
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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



