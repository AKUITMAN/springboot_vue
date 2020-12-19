package com.qf.controller;

import com.qf.commom.BaseResponse;
import com.qf.service.AdminOrderService;
import com.qf.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/adminorder")
public class AdminOrderController {
    @Autowired
    private AdminOrderService adminOrderService;
    @RequestMapping("/findAll")
    public BaseResponse findAll(){
        return adminOrderService.findAll();
    }
    @RequestMapping(value = "deleteById",method = RequestMethod.POST)
    @RequiresPermissions(value = {"/adminorder/deleteById"})
    public BaseResponse deleteById(@RequestBody Map map){
        return adminOrderService.deleteById((Integer)map.get("id"));
    }
}
