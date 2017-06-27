package com.flow.biz.impl;

import com.flow.biz.FlowPeopleBiz;
import com.flow.biz.bo.FlowPeopleBO;
import com.flow.dal.mapper.FlowPeoplePOMapper;
import com.flow.dal.po.FlowPeoplePO;
import com.flow.dal.po.FlowPeoplePOExample;
import com.quanmin110.common.converter.BeanConverter;
import java.util.Date;
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
        flowPeoplePO.setGmtCreate(new Date());
        return flowPeoplePOMapper.insertSelective(flowPeoplePO);
    }

    @Override
    public Integer delFlowPeople(Integer id) {
        return flowPeoplePOMapper.logicallyDeleteByPrimaryKey(id);
    }

    @Override
    public FlowPeopleBO getFlowPeople(FlowPeopleBO flowPeopleBO) {
        if (null == flowPeopleBO){
            return null;
        }
        FlowPeoplePOExample flowPeoplePOExample = new FlowPeoplePOExample();
        FlowPeoplePOExample.Criteria criteria = flowPeoplePOExample.or();

        if (null != flowPeopleBO.getId()){
            criteria.andIdEqualTo(flowPeopleBO.getId());
        }
        if (null != flowPeopleBO.getUserId()){
            criteria.andUserIdEqualTo(flowPeopleBO.getUserId());
        }

        FlowPeoplePO flowPeoplePO = flowPeoplePOMapper.selectOneByExample(flowPeoplePOExample);
        if (null == flowPeoplePO){
            return null;
        }
        return BeanConverter.convertObj(flowPeoplePO, FlowPeopleBO.class);
    }

    @Override
    public List<FlowPeopleBO> getFlowPeoples(FlowPeopleBO flowPeopleBO) {
        if (null == flowPeopleBO){
            return null;
        }

        FlowPeoplePOExample flowPeoplePOExample = new FlowPeoplePOExample();
        FlowPeoplePOExample.Criteria criteria = flowPeoplePOExample.or();

        criteria.andIsDeletedEqualTo("N");

        if (null != flowPeopleBO.getId()){
            criteria.andIdEqualTo(flowPeopleBO.getId());
        }
        if (null != flowPeopleBO.getUserId()){
            criteria.andUserIdEqualTo(flowPeopleBO.getUserId());
        }

        List<FlowPeoplePO> flowPeoplePOList = flowPeoplePOMapper.selectByExample(flowPeoplePOExample);
        if (null == flowPeoplePOList){
            return null;
        }

        return BeanConverter.convertArrayList(flowPeoplePOList, FlowPeopleBO.class);
    }

    @Override
    public List<FlowPeopleBO> getFlowPeoplesByRenter(FlowPeopleBO flowPeopleBO) {
        FlowPeoplePOExample flowPeoplePOExample = new FlowPeoplePOExample();
        FlowPeoplePOExample.Criteria criteria = flowPeoplePOExample.or();
        criteria.andLandlordNameEqualTo(flowPeopleBO.getLandlordName());
        criteria.andUserIdEqualTo(flowPeopleBO.getUserId());

        FlowPeoplePOExample.Criteria criteria1 = flowPeoplePOExample.or();
        criteria1.andLandlordPhEqualTo(flowPeopleBO.getLandlordPh());
        criteria1.andUserIdEqualTo(flowPeopleBO.getUserId());

        List<FlowPeoplePO> flowPeoplePOList = flowPeoplePOMapper.selectByExample(flowPeoplePOExample);
        if (null == flowPeoplePOList){
            return null;
        }
        return BeanConverter.convertArrayList(flowPeoplePOList, FlowPeopleBO.class);

    }
}
