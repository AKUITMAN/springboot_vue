package com.qf.controller;

import com.qf.commom.BaseResponse;
import com.qf.pojo.Order;
import com.qf.pojo.Rider;
import com.qf.service.OrderService;
import com.qf.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;
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
    public BaseResponse saveOrFlush(@RequestBody Order order){
        return service.saveOrFlush(order);
    }
}
