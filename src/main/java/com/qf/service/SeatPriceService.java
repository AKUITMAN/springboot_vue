package com.qf.service;


import com.qf.commom.BaseResponse;
import com.qf.pojo.SeatPrice;

public interface SeatPriceService{
    BaseResponse findAll();
    BaseResponse findById(Integer id);
    BaseResponse deleteById(Integer id);
    BaseResponse saveOrFlush(SeatPrice seatPrice);
}
