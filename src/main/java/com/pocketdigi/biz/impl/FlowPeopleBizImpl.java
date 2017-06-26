package com.pocketdigi.biz.impl;

import com.pocketdigi.biz.FlowPeopleBiz;
import com.pocketdigi.biz.bo.FlowPeopleBO;
import com.pocketdigi.dal.mapper.FlowPeoplePOMapper;
import com.pocketdigi.dal.po.FlowPeoplePO;
import com.pocketdigi.dal.po.FlowPeoplePOExample;
import com.quanmin110.common.converter.BeanConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gaosh on 2017/6/24.
 */
@Service
public class FlowPeopleBizImpl implements FlowPeopleBiz{

    @Autowired
    private FlowPeoplePOMapper flowPeoplePOMapper;


    @Override
    public Integer addFlowPeople(FlowPeopleBO flowPeopleBO) {
        FlowPeoplePO flowPeoplePO = BeanConverter.convertObj(flowPeopleBO, FlowPeoplePO.class);
        return flowPeoplePOMapper.insert(flowPeoplePO);
    }

    @Override
    public Integer delFlowPeople(Integer id) {
        return flowPeoplePOMapper.logicallyDeleteByPrimaryKey(id);
    }

    @Override
    public FlowPeopleBO getFlowPeople(Integer id) {
        FlowPeoplePO flowPeoplePO = flowPeoplePOMapper.selectByPrimaryKey(id);
        return BeanConverter.convertObj(flowPeoplePO, FlowPeopleBO.class);
    }

    @Override
    public List<FlowPeopleBO> getFlowPeoplesByRenter(FlowPeopleBO flowPeopleBO) {
        FlowPeoplePOExample flowPeoplePOExample = new FlowPeoplePOExample();
        FlowPeoplePOExample.Criteria criteria = flowPeoplePOExample.or();
        criteria.andLandlordNameEqualTo(flowPeopleBO.getLandlordName());

        FlowPeoplePOExample.Criteria criteria1 = flowPeoplePOExample.or();
        criteria1.andLandlordPhEqualTo(flowPeopleBO.getLandlordPh());

        List<FlowPeoplePO> flowPeoplePOList = flowPeoplePOMapper.selectByExample(flowPeoplePOExample);

        return BeanConverter.convertArrayList(flowPeoplePOList, FlowPeopleBO.class);

    }
}
