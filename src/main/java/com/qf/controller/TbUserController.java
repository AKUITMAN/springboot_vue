package com.qf.controller;

import com.qf.commom.BaseResponse;
import com.qf.pojo.TbUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "tbuser",method = RequestMethod.POST)
@ResponseBody
public class TbUserController {
    //@Autowired
    //private TbUserService tbUserService;
    @RequestMapping("/login")
    public BaseResponse login(@RequestBody TbUser tbuser){
        //验证登录。从subjectUtils中获取主体
        Subject subject= SecurityUtils.getSubject();
        //构建令牌对象
        UsernamePasswordToken token=new UsernamePasswordToken(tbuser.getUserName(),tbuser.getPassword());
        try {
            subject.login(token);
            //可以在此处进行其他业务，比如放至session
        }catch (Exception e){
            System.out.println("登陆失败");
        }
        BaseResponse baseResult=new BaseResponse();
        if (subject.isAuthenticated()){
            baseResult.setCode(200);
            baseResult.setMessage("登陆成功");
            return baseResult;
        }else {
            baseResult.setCode(201);
            baseResult.setMessage("登陆失败");
            return baseResult;
        }
    }
}
