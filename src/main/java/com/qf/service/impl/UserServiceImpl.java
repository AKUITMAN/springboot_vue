package com.qf.service.impl;

import com.qf.commom.BaseResponse;
import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisUtils redisUtils;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public BaseResponse sendMail(String email) {
        BaseResponse baseResponse = new BaseResponse();
        if (email!=null){
            //去数据库进行查询，保证邮箱的唯一性
            User user=userMapper.findByEmail(email);
            if (user!=null){
                baseResponse.setCode(201);
                baseResponse.setMessage("该邮箱已被注册");
                return baseResponse;
            }
            //user为空，该邮箱可以进行注册
            //定义发送的验证码
            Random random=new Random();
            StringBuffer code=new StringBuffer();
            for (int i=0;i<4;i++){
                int a=random.nextInt(10);
                code.append(a);
            }
            //进行验证码发送
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("12306中国铁路验证码");
            simpleMailMessage.setText(code.toString());
            javaMailSender.send(simpleMailMessage);
            //发送成功后，将验证码存入redis,redis用key与value进行存储
            redisUtils.set(email,code.toString());
            //设置过期时间
            redisUtils.expire(email,300);
            baseResponse.setCode(200);
            baseResponse.setMessage("验证码发送成功");
            return baseResponse;
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("请填写正确的邮箱");
        return baseResponse;
    }
}
