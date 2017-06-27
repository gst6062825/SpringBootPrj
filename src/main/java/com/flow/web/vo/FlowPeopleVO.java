package com.flow.web.vo;

import java.util.Date;
import lombok.Data;

/**
 * Created by gaosh on 2017/6/24.
 */
@Data
public class FlowPeopleVO {
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

    /** 人员类型 **/
    private Integer type;
}
