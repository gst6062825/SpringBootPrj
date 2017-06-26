package com.pocketdigi.web.controller;

import com.google.gson.Gson;
import com.pocketdigi.biz.FlowPeopleBiz;
import com.pocketdigi.biz.bo.FlowPeopleBO;
import com.pocketdigi.util.Constants;
import com.pocketdigi.web.vo.FlowPeopleVO;
import com.quanmin110.ResultDTO;
import com.quanmin110.common.converter.BeanConverter;
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

    @RequestMapping(value = "people/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO addFlowPeople(@RequestBody FlowPeopleVO flowPeopleVO) {
        log.debug("添加流动人口：{}", new Gson().toJson(flowPeopleVO));
        if (null == flowPeopleVO) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        FlowPeopleBO flowPeoplePO = BeanConverter.convertObj(flowPeopleVO, FlowPeopleBO.class);
        Integer result = flowPeopleBiz.addFlowPeople(flowPeoplePO);
        ResultDTO resultDTO = null;

        if (null == result) {
            resultDTO = ResultDTO.wrapError(Constants.CODE_DB_CRUD_ERR, Constants.MSG_DB_CRUD_ERR);
        } else {
            resultDTO = ResultDTO.wrapSuccess(result);
        }

        return resultDTO;
    }

    @RequestMapping(value = "people/del/{id}", method = RequestMethod.DELETE)
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

    @RequestMapping(value = "people/detail")
    @ResponseBody
    public ResultDTO<FlowPeopleVO> getFlowPeople(Integer id) {
        log.debug("查询流动人口详情：{}", id);
        if (null == id) {
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        FlowPeopleBO flowPeopleBO = flowPeopleBiz.getFlowPeople(id);
        if (null == flowPeopleBO) {
            return ResultDTO
                .wrapError(Constants.CODE_RECORD_NOT_FIND, Constants.MSG_RECORD_NOT_FIND);
        }

        ResultDTO resultDTO = ResultDTO
            .wrapSuccess(BeanConverter.convertObj(flowPeopleBO, FlowPeopleVO.class));

        return resultDTO;
    }

}
