package com.flow.dal.po;

import java.util.Date;

public class FlowPeoplePO {
    private Integer id;

    /** 是否删除,N:未删除，Y:删除 **/
    private String isDeleted;

    /** 记录创建时间 **/
    private Date gmtCreate;

    /** 记录修改时间,如果时间是1970年则表示纪录未修改 **/
    private Date gmtModified;

    /** 姓名 **/
    private String name;

    /** 身份证 **/
    private String idCard;

    /** 手机号 **/
    private String phone;

    /** 所在地区 **/
    private String district;

    /** 详细地址 **/
    private String addr;

    /** 从业单位 **/
    private String department;

    /** 房东姓名 **/
    private String landlordName;

    /** 房东手机 **/
    private String landlordPh;

    /** 用户id **/
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 是否删除,N:未删除，Y:删除 **/
    public String getIsDeleted() {
        return isDeleted;
    }

    /** 是否删除,N:未删除，Y:删除 **/
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /** 记录创建时间 **/
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /** 记录创建时间 **/
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /** 记录修改时间,如果时间是1970年则表示纪录未修改 **/
    public Date getGmtModified() {
        return gmtModified;
    }

    /** 记录修改时间,如果时间是1970年则表示纪录未修改 **/
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /** 姓名 **/
    public String getName() {
        return name;
    }

    /** 姓名 **/
    public void setName(String name) {
        this.name = name;
    }

    /** 身份证 **/
    public String getIdCard() {
        return idCard;
    }

    /** 身份证 **/
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /** 手机号 **/
    public String getPhone() {
        return phone;
    }

    /** 手机号 **/
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 所在地区 **/
    public String getDistrict() {
        return district;
    }

    /** 所在地区 **/
    public void setDistrict(String district) {
        this.district = district;
    }

    /** 详细地址 **/
    public String getAddr() {
        return addr;
    }

    /** 详细地址 **/
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /** 从业单位 **/
    public String getDepartment() {
        return department;
    }

    /** 从业单位 **/
    public void setDepartment(String department) {
        this.department = department;
    }

    /** 房东姓名 **/
    public String getLandlordName() {
        return landlordName;
    }

    /** 房东姓名 **/
    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    /** 房东手机 **/
    public String getLandlordPh() {
        return landlordPh;
    }

    /** 房东手机 **/
    public void setLandlordPh(String landlordPh) {
        this.landlordPh = landlordPh;
    }

    /** 用户id **/
    public Integer getUserId() {
        return userId;
    }

    /** 用户id **/
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}