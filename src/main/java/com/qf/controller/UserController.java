package com.qf.controller;

import com.qf.commom.BaseResponse;
import com.qf.pojo.User;
import com.qf.pojo.req.UserReq;
import com.qf.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/sendMail",method = RequestMethod.POST)
    public BaseResponse sendMail(@RequestBody  Map map){
        return userService.sendMail(map.get("email").toString());

    }
    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public BaseResponse registry(@RequestBody UserReq userReq){
        return userService.registry(userReq);
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponse login(@RequestBody UserReq user){
        return userService.login(user);
    }

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestBody User user){
        return userService.updatePassword(user);
    }
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @RequiresPermissions(value = {"/user/findAll"})
    public BaseResponse findAll(){
        return userService.findAll();
    }
    @RequestMapping(value = "deleteByuserName",method =RequestMethod.POST)
    @RequiresPermissions(value = {"/user/deleteByUserName"})
    public BaseResponse deleteByUserName(@RequestBody Map map){
        Integer id= (Integer) map.get("id");
        System.out.println(id);
        return userService.deleteByUserName(id);
    }
}
