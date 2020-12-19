package com.qf.service;

import com.qf.commom.BaseResponse;

public interface AdminOrderService {
    BaseResponse findAll();

    BaseResponse deleteById(Integer id);
}
