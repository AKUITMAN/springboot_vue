package com.qf.service;

import com.qf.commom.BaseResponse;
import com.qf.pojo.User;
import com.qf.pojo.req.UserReq;

public interface UserService {
    BaseResponse sendMail(String email);

    BaseResponse registry(UserReq userReq);

    BaseResponse login(UserReq user);

    BaseResponse updatePassword(User user);
}
