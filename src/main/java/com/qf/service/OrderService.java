package com.qf.service;


import com.qf.commom.BaseResponse;
import com.qf.pojo.Order;

public interface OrderService{
    BaseResponse findAll();
    BaseResponse findById(Integer id);
    BaseResponse deleteById(Integer id);
    BaseResponse saveOrFlush(Order order);
}
