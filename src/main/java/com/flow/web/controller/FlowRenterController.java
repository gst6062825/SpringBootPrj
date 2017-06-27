package com.flow.web.controller;

import com.flow.biz.FlowPeopleBiz;
import com.flow.biz.bo.FlowPeopleBO;
import com.flow.web.vo.FlowPeopleVO;
import com.google.gson.Gson;
import com.flow.biz.FlowRenterBiz;
import com.flow.biz.bo.FlowRenterBO;
import com.flow.util.Constants;
import com.flow.util.Utils;
import com.flow.web.vo.FlowRenterVO;
import com.quanmin110.ResultDTO;
import com.quanmin110.common.converter.BeanConverter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gaosh on 2017/6/26.
 */
@RestController
@Slf4j
public class FlowRenterController {

    @Autowired
    private FlowPeopleBiz flowPeopleBiz;

    @RequestMapping(value = "renters", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO addFlowRenter(@RequestBody FlowPeopleVO flowPeopleVO,
        HttpServletRequest request) {
        log.debug("添加房主信息：{}", new Gson().toJson(flowPeopleVO));

        if (null == flowPeopleVO) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        Integer uid = request.getIntHeader("uid");
        uid = uid == null ? Utils.getUid(uid, request.getCookies()) : uid;

        FlowPeopleBO flowPeopleBO = BeanConverter.convertObj(flowPeopleVO, FlowPeopleBO.class);

        flowPeopleBO.setUserId(uid);

        if (null == flowPeopleBO.getType()){
            flowPeopleBO.setType(Constants.FLOW_RENTER);
        }

        Integer result = flowPeopleBiz.addFlowPeople(flowPeopleBO);

        ResultDTO resultDTO = null;
        if (null == result) {
            return ResultDTO.wrapError(Constants.CODE_DB_CRUD_ERR, Constants.MSG_DB_CRUD_ERR);
        } else {
            resultDTO = ResultDTO.wrapSuccess(result);
        }
        return resultDTO;
    }

    @RequestMapping(value = "renters/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultDTO delFlowRenter(@PathVariable Integer id) {
        log.debug("删除房主信息：{}", id);

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

    @RequestMapping(value = "renters/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<FlowPeopleVO> getFlowRenter(@PathVariable Integer id,
        HttpServletRequest request) {
        log.debug("获取房主信息：{}", id);

        if (null == id) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        Integer uid = request.getIntHeader("uid");
        uid = uid == null ? Utils.getUid(uid, request.getCookies()) : uid;

        FlowPeopleBO peopleBO = new FlowPeopleBO();
        peopleBO.setId(id);
        peopleBO.setUserId(uid);
        peopleBO.setType(Constants.FLOW_RENTER);

        FlowPeopleBO flowPeopleBO = flowPeopleBiz.getFlowPeople(peopleBO);

        if (null == flowPeopleBO) {
            return ResultDTO.wrapError(Constants.CODE_RECORD_NOT_FIND, Constants.MSG_DB_CRUD_ERR);
        }

        return ResultDTO.wrapSuccess(BeanConverter.convertObj(flowPeopleBO, FlowPeopleVO.class));
    }

    @RequestMapping(value = "renter/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<FlowRenterVO>> getRenters(HttpServletRequest request) {
        Integer uid = request.getIntHeader("uid");
        uid = uid == null ? Utils.getUid(uid, request.getCookies()) : uid;

        log.debug("房主信息：{}", uid);
        if (null == uid || uid < 1) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        FlowRenterBO flowRenterBO = new FlowRenterBO();
        flowRenterBO.setUserId(uid);

        //
        return null;
    }

}
