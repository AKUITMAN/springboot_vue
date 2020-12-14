package com.qf.service;


import com.qf.commom.BaseResponse;
import com.qf.pojo.Train;

public interface TrainService{
    BaseResponse findAll();
    BaseResponse findById(Integer id);
    BaseResponse deleteById(Integer id);
    BaseResponse saveOrFlush(Train train);
}
