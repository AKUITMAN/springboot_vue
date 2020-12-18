package com.qf.controller;

import com.qf.commom.BaseResponse;
import com.qf.pojo.Address;
import com.qf.pojo.Order;
import com.qf.service.AddressService;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService service;
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public BaseResponse findAll(){
        return service.findAll();
    }
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public BaseResponse findById(@RequestBody Map map){
        return service.findById((Integer)map.get("id"));
    }
    @RequestMapping(value = "deleteById",method = RequestMethod.POST)
    public BaseResponse deleteById(@RequestBody Map map){
        return service.deleteById((Integer)map.get("id"));
    }
    @RequestMapping(value = "/saveOrFlush",method = RequestMethod.POST)
    public BaseResponse saveOrFlush(@RequestBody Address address){
        return service.saveOrFlush(address);
    }
    @RequestMapping(value = "/findMap",method = RequestMethod.GET)
    public BaseResponse findMap(){
        return service.findMap();
    }
}
