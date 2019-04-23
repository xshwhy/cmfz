package com.baizhi.controller;


import com.baizhi.entity.Master;
import com.baizhi.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("master")
public class MasterController {

    @Autowired
    MasterService masterService;


    @RequestMapping("selectAllMaster")
    public List<Master>selectAllMaster(){
        List<Master> masters = masterService.SelectAllMaster();
        return masters;
    }




}
