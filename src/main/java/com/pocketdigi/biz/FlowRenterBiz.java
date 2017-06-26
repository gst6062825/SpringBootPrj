package com.pocketdigi.biz;

import com.pocketdigi.biz.bo.FlowRenterBO;
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
     * @param id
     * @return
     */
    FlowRenterBO getFlowRenter(Integer id);

}
