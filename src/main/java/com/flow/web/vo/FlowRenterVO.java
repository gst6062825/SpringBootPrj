package com.flow.web.vo;

/**
 * Created by gaosh on 2017/6/24.
 */

import com.flow.biz.bo.FlowPeopleBO;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class FlowRenterVO {
    private Integer id;

    /** 是否删除,N:未删除，Y:删除 **/
    private String isDeleted;

    /** 记录创建时间 **/
    private Date gmtCreate;

    /** 记录修改时间,如果时间是1970年则表示纪录未修改 **/
    private Date gmtModified;

    /** 房东姓名 **/
    private String landlordName;

    /** 房东手机 **/
    private String landlordPh;

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

    /** 租客 */
    private List<FlowPeopleBO> flowPeopleBOS;
}
