package com.qf.controller;

import com.qf.commom.BaseResponse;
import com.qf.pojo.SeatPrice;
import com.qf.pojo.Train;
import com.qf.service.SeatPriceService;
import com.qf.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/seat")
public class SeatPriceController {

    @Autowired
    SeatPriceService service;
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
    public BaseResponse saveOrFlush(@RequestBody SeatPrice seatPrice){
        return service.saveOrFlush(seatPrice);
    }
}
