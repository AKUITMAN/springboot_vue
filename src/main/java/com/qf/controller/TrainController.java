package com.qf.controller;

import com.qf.commom.BaseResponse;
import com.qf.pojo.Train;
import com.qf.service.TrainService;
import com.qf.service.impl.AddressServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    TrainService service;
    @RequestMapping(value = "/findAll/{page}/{size}",method = RequestMethod.GET)
    //@RequiresPermissions(value = {"findAll"})
    public BaseResponse findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        return service.findAll(page,size);
    }
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public BaseResponse findById(@RequestBody Map map){
        return service.findById((Integer)map.get("id"));
    }
    @RequiresPermissions(value = {"deleteById"})
    @RequestMapping(value = "deleteById",method = RequestMethod.POST)
    public BaseResponse deleteById(@RequestBody Map map){
        return service.deleteById((Integer)map.get("id"));
    }
    @RequestMapping(value = "/saveOrFlush",method = RequestMethod.POST)
    public BaseResponse saveOrFlush(@RequestBody Train train){
        return service.saveOrFlush(train);
    }
}
