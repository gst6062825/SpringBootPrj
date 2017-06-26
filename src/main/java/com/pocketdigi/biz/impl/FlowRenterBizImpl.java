package com.pocketdigi.biz.impl;

import com.pocketdigi.biz.FlowPeopleBiz;
import com.pocketdigi.biz.FlowRenterBiz;
import com.pocketdigi.biz.bo.FlowPeopleBO;
import com.pocketdigi.biz.bo.FlowRenterBO;
import com.pocketdigi.dal.mapper.FlowRenterPOMapper;
import com.pocketdigi.dal.po.FlowRenterPO;
import com.quanmin110.common.converter.BeanConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gaosh on 2017/6/26.
 */
@Service
public class FlowRenterBizImpl implements FlowRenterBiz {

    @Autowired
    private FlowRenterPOMapper flowRenterPOMapper;

    @Autowired
    private FlowPeopleBiz flowPeopleBiz;

    @Override
    public Integer addFlowRenter(FlowRenterBO flowRenterBO) {
        FlowRenterPO flowRenterPO = BeanConverter.convertObj(flowRenterBO, FlowRenterPO.class);
        return flowRenterPOMapper.insert(flowRenterPO);
    }

    @Override
    public Integer delFlowRenter(Integer id) {
        return flowRenterPOMapper.logicallyDeleteByPrimaryKey(id);
    }

    @Override
    public FlowRenterBO getFlowRenter(Integer id) {
        FlowRenterPO flowRenterPO = flowRenterPOMapper.selectByPrimaryKey(id);
        FlowRenterBO flowRenterBO = BeanConverter.convertObj(flowRenterPO, FlowRenterBO.class);

        // 查询租客信息
        FlowPeopleBO flowPeopleBO = new FlowPeopleBO();
        flowPeopleBO.setLandlordName(flowRenterBO.getLandlordName());
        flowPeopleBO.setLandlordPh(flowRenterBO.getLandlordPh());
        List<FlowPeopleBO> flowPeopleBOS = flowPeopleBiz.getFlowPeoplesByRenter(flowPeopleBO);

        flowRenterBO.setFlowPeopleBOS(flowPeopleBOS);

        return flowRenterBO;
    }
}
