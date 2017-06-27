package com.flow.web.controller;

import com.flow.dal.mapper.ConfigPOMapper;
import com.flow.dal.po.ConfigPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Exception on 2017/4/26.
 */
@RestController
public class IndexController {
    @Autowired
    ConfigPOMapper configPOMapper;

    @RequestMapping(value = "/")
    public Object index() {
        ConfigPO configPO = configPOMapper.selectByPrimaryKey(1);
        return configPO;
    }
}
