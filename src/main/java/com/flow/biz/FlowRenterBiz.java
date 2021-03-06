package com.flow.biz;

import com.flow.biz.bo.FlowRenterBO;
import java.util.List;

/**
 * Created by gaosh on 2017/6/24.
 */
public interface FlowRenterBiz {

    /**
     * 添加房主信息
     * @param flowRenterBO
     * @return
     */
    Integer addFlowRenter(FlowRenterBO flowRenterBO);

    /**
     * 删除房主信息
     * @param id
     * @return
     */
    Integer delFlowRenter(Integer id);

    /**
     * 查看房主信息
     * @param flowRenterBO
     * @return
     */
    FlowRenterBO getFlowRenter(FlowRenterBO flowRenterBO);

    /**
     * 获取列表
     * @param flowRenterBO
     * @return
     */
    List<FlowRenterBO> getFlowRenterList(FlowRenterBO flowRenterBO);

}
