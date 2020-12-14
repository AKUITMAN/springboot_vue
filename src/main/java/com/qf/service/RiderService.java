package com.qf.service;


import com.qf.commom.BaseResponse;
import com.qf.pojo.Rider;

public interface RiderService{
    BaseResponse findAll();
    BaseResponse findById(Integer id);
    BaseResponse deleteById(Integer id);
    BaseResponse saveOrFlush(Rider rider);
}
