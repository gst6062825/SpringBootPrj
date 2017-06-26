package com.pocketdigi.web.controller;

import com.google.gson.Gson;
import com.pocketdigi.biz.FlowRenterBiz;
import com.pocketdigi.biz.bo.FlowRenterBO;
import com.pocketdigi.util.Constants;
import com.pocketdigi.web.vo.FlowRenterVO;
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
 * Created by gaosh on 2017/6/26.
 */
@RestController
@Slf4j
public class FlowRenterController {

    @Autowired
    private FlowRenterBiz flowRenterBiz;

    @RequestMapping(value = "renter/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO addFlowRenter(@RequestBody FlowRenterVO flowRenterVO){
        log.debug("添加房主信息：{}", new Gson().toJson(flowRenterVO));

        if (null == flowRenterVO){
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        FlowRenterBO flowRenterBO = BeanConverter.convertObj(flowRenterVO, FlowRenterBO.class);

        Integer result = flowRenterBiz.addFlowRenter(flowRenterBO);

        ResultDTO resultDTO = null;
        if (null == result){
            return ResultDTO.wrapError(Constants.CODE_DB_CRUD_ERR, Constants.MSG_DB_CRUD_ERR);
        }else{
            resultDTO = ResultDTO.wrapSuccess(result);
        }
        return resultDTO;
    }

    @RequestMapping(value = "renter/del", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultDTO delFlowRenter(@PathVariable Integer id){
        log.debug("删除房主信息：{}", id);

        if (null == id){
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        Integer result = flowRenterBiz.delFlowRenter(id);

        ResultDTO resultDTO = null;

        if (null == result){
            resultDTO = ResultDTO.wrapError(Constants.CODE_DB_CRUD_ERR, Constants.MSG_DB_CRUD_ERR);
        }else{
            resultDTO = ResultDTO.wrapSuccess(result);
        }

        return resultDTO;
    }

    @RequestMapping(value = "renter/detail")
    @ResponseBody
    public ResultDTO<FlowRenterVO> getFlowRenter(Integer id){
        log.debug("获取房主信息：{}", id);

        if (null == id){
            return ResultDTO.wrapError(Constants.CODE_NET_ERR, Constants.MSG_NET_ERR);
        }

        FlowRenterBO flowRenterBO = flowRenterBiz.getFlowRenter(id);

        if (null == flowRenterBO){
            return ResultDTO.wrapError(Constants.CODE_RECORD_NOT_FIND, Constants.MSG_DB_CRUD_ERR);
        }

        return ResultDTO.wrapSuccess(BeanConverter.convertObj(flowRenterBO, FlowRenterVO.class));
    }

}
