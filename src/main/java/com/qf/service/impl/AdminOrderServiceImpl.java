package com.qf.service.impl;

import com.qf.commom.BaseResponse;
import com.qf.dao.AdminOrderMapper;
import com.qf.dao.OderdelMapper;
import com.qf.dao.OrderRepository;
import com.qf.pojo.req.OrderReq;
import com.qf.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    private AdminOrderMapper adminOrderMapper;
    @Autowired
    private OderdelMapper oderdelMapper;

    @Override
    public BaseResponse findAll() {
        BaseResponse baseResponse = new BaseResponse();
        List<OrderReq> all=adminOrderMapper.findAll();
        if (all!=null){
            baseResponse.setData(all);
            baseResponse.setCode(200);
            return baseResponse;
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("查询失败");
        return baseResponse;
    }

    @Override
    public BaseResponse deleteById(Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        int res = oderdelMapper.deleteById(id);
        if (res == 1) {
            baseResponse.setMessage("删除成功");
            baseResponse.setCode(200);
            return baseResponse;
        }else{
            baseResponse.setMessage("删除失败");
            baseResponse.setCode(201);
            return baseResponse;
        }
    }
}
