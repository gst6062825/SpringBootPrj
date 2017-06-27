package com.flow.web.controller;

import com.google.gson.Gson;
import com.flow.biz.FlowPeopleBiz;
import com.flow.biz.FlowRenterBiz;
import com.flow.biz.bo.FlowPeopleBO;
import com.flow.biz.bo.FlowRenterBO;
import com.flow.util.Constants;
import com.flow.util.Utils;
import com.flow.web.vo.FlowListVO;
import com.flow.web.vo.FlowPeopleVO;
import com.quanmin110.ResultDTO;
import com.quanmin110.common.converter.BeanConverter;
import com.quanmin110.common.util.DateUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gaosh on 2017/6/24.
 */
@RestController
@Slf4j
public class FlowPeopleController {

    @Autowired
    private FlowPeopleBiz flowPeopleBiz;

    @Autowired
    private FlowRenterBiz flowRenterBiz;

    /** 流动人口类型 **/
    private final Integer FLOW_PEOPLE = 1;

    /** 房屋类型 **/
    private final Integer FLOW_RENTER = 2;

    @RequestMapping(value = "peoples", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO addFlowPeople(@RequestBody FlowPeopleVO flowPeopleVO,
        HttpServletRequest request) {
        log.debug("添加流动人口：{}", new Gson().toJson(flowPeopleVO));
        if (null == flowPeopleVO) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        Integer uid = request.getIntHeader("uid");
        uid = uid == null ? Utils.getUid(uid, request.getCookies()) : uid;

        FlowPeopleBO flowPeoplePO = BeanConverter.convertObj(flowPeopleVO, FlowPeopleBO.class);
        flowPeoplePO.setUserId(uid);
        Integer result = flowPeopleBiz.addFlowPeople(flowPeoplePO);
        ResultDTO resultDTO = null;

        if (null == result) {
            resultDTO = ResultDTO.wrapError(Constants.CODE_DB_CRUD_ERR, Constants.MSG_DB_CRUD_ERR);
        } else {
            resultDTO = ResultDTO.wrapSuccess(result);
        }

        return resultDTO;
    }

    @RequestMapping(value = "peoples/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultDTO delFlowPeople(@PathVariable Integer id) {
        log.debug("删除流动人口：{}", id);
        if (null == id) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        Integer result = flowPeopleBiz.delFlowPeople(id);

        ResultDTO resultDTO = null;
        if (null == result) {
            resultDTO = ResultDTO.wrapError(Constants.CODE_DB_CRUD_ERR, Constants.MSG_DB_CRUD_ERR);
        } else {
            resultDTO = ResultDTO.wrapSuccess(result);
        }
        return resultDTO;
    }

    @RequestMapping(value = "peoples/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<FlowPeopleVO> getFlowPeople(@PathVariable Integer id,
        HttpServletRequest request) {
        log.debug("查询流动人口详情：{}", id);
        if (null == id) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        Integer uid = request.getIntHeader("uid");
        uid = uid == null ? Utils.getUid(uid, request.getCookies()) : uid;

        FlowPeopleBO peopleBO = new FlowPeopleBO();
        peopleBO.setId(id);
        peopleBO.setUserId(uid);

        FlowPeopleBO flowPeopleBO = flowPeopleBiz.getFlowPeople(peopleBO);
        if (null == flowPeopleBO) {
            return ResultDTO
                .wrapError(Constants.CODE_RECORD_NOT_FIND, Constants.MSG_RECORD_NOT_FIND);
        }

        ResultDTO resultDTO = ResultDTO
            .wrapSuccess(BeanConverter.convertObj(flowPeopleBO, FlowPeopleVO.class));

        return resultDTO;
    }

    @RequestMapping(value = "people/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<FlowPeopleVO>> getFlowPeoples(HttpServletRequest request) {
        Integer uid = request.getIntHeader("uid");
        uid = uid == null ? Utils.getUid(uid, request.getCookies()) : uid;

        log.debug("流动信息：{}", uid);
        if (null == uid || uid < 1) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        FlowPeopleBO flowPeopleBO = new FlowPeopleBO();
        flowPeopleBO.setUserId(uid);

        List<FlowPeopleBO> flowPeopleBOList = flowPeopleBiz.getFlowPeoples(flowPeopleBO);
        if (null == flowPeopleBOList) {
            return ResultDTO
                .wrapError(Constants.CODE_RECORD_NOT_FIND, Constants.MSG_RECORD_NOT_FIND);
        }

        return ResultDTO
            .wrapSuccess(BeanConverter.convertArrayList(flowPeopleBOList, FlowPeopleVO.class));
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<FlowListVO>> getFlowList(HttpServletRequest request) {
        Integer uid = request.getIntHeader("uid");
        uid = uid == null ? Utils.getUid(uid, request.getCookies()) : uid;

        log.debug("列表信息：{}", uid);
        /*if (null == uid || uid < 1) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }*/

        FlowPeopleBO flowPeopleBO = new FlowPeopleBO();
        flowPeopleBO.setUserId(uid);
        List<FlowPeopleBO> flowPeopleBOS = flowPeopleBiz.getFlowPeoples(flowPeopleBO);

        FlowRenterBO flowRenterBO = new FlowRenterBO();
        flowRenterBO.setUserId(uid);
        List<FlowRenterBO> flowRenterBOS = flowRenterBiz.getFlowRenterList(flowRenterBO);

        List<FlowListVO> flowListVOS = new ArrayList<>();

        if (null != flowPeopleBOS && !flowPeopleBOS.isEmpty()){
            for (FlowPeopleBO peopleBO: flowPeopleBOS){
                FlowListVO flowListVO = BeanConverter.convertObj(peopleBO, FlowListVO.class);
                flowListVO.setGmtCreateStr(DateUtils.date2String(flowListVO.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
                flowListVO.setType(FLOW_PEOPLE);
                flowListVOS.add(flowListVO);
            }
        }

        if (null != flowRenterBOS && !flowRenterBOS.isEmpty()){
            for (FlowRenterBO renterBO: flowRenterBOS){
                FlowListVO flowListVO = BeanConverter.convertObj(renterBO, FlowListVO.class);
                flowListVO.setGmtCreateStr(DateUtils.date2String(flowListVO.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
                flowListVO.setType(FLOW_RENTER);
                flowListVOS.add(flowListVO);
            }
        }

        return ResultDTO.wrapSuccess(flowListVOS);
    }

}
