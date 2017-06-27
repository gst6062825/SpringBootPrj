package com.flow.web.vo;

import java.util.Date;
import lombok.Data;

/**
 * Created by gaosh on 2017/6/26.
 */
@Data
public class FlowListVO {

    private Integer id;

    /** 记录创建时间 **/
    private Date gmtCreate;

    /** 记录创建时间-格式化 **/
    private String gmtCreateStr;

    /** 记录类型 1:流动人口  2：房屋信息 **/
    private Integer type;
}
