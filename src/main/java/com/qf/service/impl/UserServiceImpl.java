package com.qf.service.impl;

import com.qf.commom.BaseResponse;
import com.qf.dao.UserMapper;
import com.qf.dao.UserRepository;
import com.qf.pojo.User;
import com.qf.pojo.req.UserReq;
import com.qf.service.UserService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserRepository userRepository;

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

    @Override
    public BaseResponse registry(UserReq userReq) {
        BaseResponse baseResponse = new BaseResponse();
        //判断用户名是否已经被注册
        String userName=userReq.getUserName();
        User user=userRepository.findByUserName(userName);
        if (user!=null){
            baseResponse.setCode(202);
            baseResponse.setMessage("用户名已存在");
            return baseResponse;
        }
        //可以进行注册，进行验证码判断
        String code=userReq.getCode();
        String email=userReq.getEmail();
        //从redis中通过email获得code值进行对比
        String res= (String) redisUtils.get(email);
        if (res!=null&&code.equals(res)){
            //验证码验证通过，进行注册
            User user1 = new User();
            //进行数据赋值
            BeanUtils.copyProperties(userReq,user1);
            //将注册的新用户信息添加到数据库
            userRepository.saveAndFlush(user1);
            baseResponse.setCode(200);
            baseResponse.setMessage("注册成功");
            return baseResponse;
        }
        baseResponse.setCode(204);
        baseResponse.setMessage("注册失败");
        return baseResponse;
    }

    @Override
    public BaseResponse login(UserReq user) {
        BaseResponse baseResponse = new BaseResponse();
        //通过用户名查询密码
        String userName=user.getUserName();
        User user1=userRepository.findByUserName(userName);
        //进行密码的比对
        if (user.getPassword()!=null&&user.getPassword().equals(user1.getPassword())){
            //密码比对通过，放行，将用户信息存入到redis中，设置token值
            //生成用户唯一标识符
            UUID uuid=UUID.randomUUID();
            redisUtils.set(uuid.toString(),user);
            baseResponse.setCode(200);
            baseResponse.setData(uuid.toString());
            baseResponse.setMessage("登陆成功");
            return baseResponse;
        }
        baseResponse.setCode(204);
        baseResponse.setMessage("密码或账号错误");
        return baseResponse;
    }
}
