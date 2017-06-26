package com.pocketdigi.biz;

import com.pocketdigi.biz.bo.FlowPeopleBO;
import com.pocketdigi.dal.po.FlowPeoplePO;
import com.quanmin110.ResultDTO;
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
     * @param id
     * @return
     */
    FlowPeopleBO getFlowPeople(Integer id);

    /**
     * 通过房主信息查询租客
     * @param flowPeopleBO
     * @return
     */
    List<FlowPeopleBO> getFlowPeoplesByRenter(FlowPeopleBO flowPeopleBO);
}
