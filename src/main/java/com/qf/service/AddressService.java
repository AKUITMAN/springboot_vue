package com.qf.service;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.qf.commom.BaseResponse;
import com.qf.pojo.Address;

public interface AddressService {
    BaseResponse findAll();
    BaseResponse findById(Integer id);
    BaseResponse deleteById(Integer id);
    BaseResponse saveOrFlush(Address address);
}
