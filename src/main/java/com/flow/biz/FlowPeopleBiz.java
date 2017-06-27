package com.flow.biz;

import com.flow.biz.bo.FlowPeopleBO;
import java.util.List;

/**
 * Created by gaosh on 2017/6/24.
 */
public interface FlowPeopleBiz {

    /**
     * 添加流动人口
     * @param flowPeoplePO
     * @return
     */
    Integer addFlowPeople(FlowPeopleBO flowPeoplePO);

    /**
     * 注销流动人口(改标记)
     * @param id
     * @return
     */
    Integer delFlowPeople(Integer id);

    /**
     * 查询流动人口
     * @param flowPeopleBO
     * @return
     */
    FlowPeopleBO getFlowPeople(FlowPeopleBO flowPeopleBO);

    /**
     * 查询流动人口信息
     * @param flowPeopleBO
     * @return
     */
    List<FlowPeopleBO> getFlowPeoples(FlowPeopleBO flowPeopleBO);

    /**
     * 通过房主信息查询租客
     * @param flowPeopleBO
     * @return
     */
    List<FlowPeopleBO> getFlowPeoplesByRenter(FlowPeopleBO flowPeopleBO);
}
