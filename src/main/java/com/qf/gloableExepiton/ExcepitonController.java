package com.qf.gloableExepiton;

import com.qf.commom.BaseResponse;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExcepitonController {
    @ExceptionHandler(UnauthorizedException.class)
    public BaseResponse AuthonewrizationEx(){
        BaseResponse baseResult = new BaseResponse();
        baseResult.setCode(300);
        baseResult.setMessage("无权限访问");
        return baseResult;
    }
    @ExceptionHandler(UnauthenticatedException.class)
    public BaseResponse unauthenticatedException(){
        BaseResponse baseResult = new BaseResponse();
        baseResult.setCode(400);
        baseResult.setMessage("未登录");
        return baseResult;
    }
}
