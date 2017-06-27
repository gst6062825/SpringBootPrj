package com.flow.biz.impl;

import com.flow.biz.FlowPeopleBiz;
import com.flow.biz.FlowRenterBiz;
import com.flow.biz.bo.FlowPeopleBO;
import com.flow.biz.bo.FlowRenterBO;
import com.flow.dal.mapper.FlowRenterPOMapper;
import com.flow.dal.po.FlowRenterPO;
import com.flow.dal.po.FlowRenterPOExample;
import com.quanmin110.common.converter.BeanConverter;
import java.util.Date;
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
        flowRenterPO.setGmtCreate(new Date());
        return flowRenterPOMapper.insertSelective(flowRenterPO);
    }

    @Override
    public Integer delFlowRenter(Integer id) {
        return flowRenterPOMapper.logicallyDeleteByPrimaryKey(id);
    }

    @Override
    public FlowRenterBO getFlowRenter(FlowRenterBO renterBO) {
        FlowRenterPOExample flowRenterPOExample = new FlowRenterPOExample();
        FlowRenterPOExample.Criteria criteria = flowRenterPOExample.or();

        criteria.andIsDeletedEqualTo("N");

        if (null != renterBO.getId()){
            criteria.andIdEqualTo(renterBO.getId());
        }
        if (null != renterBO.getUserId()){
            criteria.andUserIdEqualTo(renterBO.getUserId());
        }

        FlowRenterPO flowRenterPO = flowRenterPOMapper.selectOneByExample(flowRenterPOExample);
        if (null == flowRenterPO){
            return null;
        }
        FlowRenterBO flowRenterBO = BeanConverter.convertObj(flowRenterPO, FlowRenterBO.class);

        // 查询租客信息
        /*FlowPeopleBO flowPeopleBO = new FlowPeopleBO();
        flowPeopleBO.setLandlordName(flowRenterBO.getLandlordName());
        flowPeopleBO.setLandlordPh(flowRenterBO.getLandlordPh());
        flowPeopleBO.setUserId(renterBO.getUserId());
        List<FlowPeopleBO> flowPeopleBOS = flowPeopleBiz.getFlowPeoplesByRenter(flowPeopleBO);

        flowRenterBO.setFlowPeopleBOS(flowPeopleBOS);*/

        return flowRenterBO;
    }

    @Override
    public List<FlowRenterBO> getFlowRenterList(FlowRenterBO flowRenterBO) {
        if (null == flowRenterBO) {
            return null;
        }

        FlowRenterPOExample flowRenterPOExample = new FlowRenterPOExample();
        FlowRenterPOExample.Criteria criteria = flowRenterPOExample.or();

        if (null != flowRenterBO.getId()){
            criteria.andIdEqualTo(flowRenterBO.getUserId());
        }
        if (null != flowRenterBO.getUserId()){
            criteria.andUserIdEqualTo(flowRenterBO.getUserId());
        }

        List<FlowRenterPO>  flowRenterPOList = flowRenterPOMapper.selectByExample(flowRenterPOExample);

        if (null == flowRenterPOList){
            return null;
        }

        return BeanConverter.convertArrayList(flowRenterPOList, FlowRenterBO.class);
    }
}
