package com.qf.service.impl;

import com.qf.commom.BaseResponse;
import com.qf.dao.OrderRepository;
import com.qf.pojo.Order;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository repository;

    @Override
    public BaseResponse findAll() {
        BaseResponse baseResponse = new BaseResponse();
        List<Order> all = repository.findAll();
        baseResponse.setData(all);
        baseResponse.setCode(200);
        baseResponse.setMessage("查询成功");
        return baseResponse;
    }

    @Override
    public BaseResponse findById(Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        if (id!=null){
            Optional byId = repository.findById(id);
            if (byId.isPresent()){
                baseResponse.setData(byId.get());
                baseResponse.setCode(200);
                baseResponse.setMessage("查询成功!!!");
                return baseResponse;
            }
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("查询失败!!!");
        return baseResponse;
    }

    @Override
    public BaseResponse deleteById(Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        if(id!=null){
            repository.deleteById(id);
            baseResponse.setCode(200);
            baseResponse.setMessage("删除成功!!!");
            return baseResponse;
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("删除失败!!!");
        return baseResponse;
    }

    @Override
    public BaseResponse saveOrFlush(Order order) {
        BaseResponse baseResponse = new BaseResponse();
        if (order!=null){
            Order o = repository.saveAndFlush(order);
            baseResponse.setData(o);
            if (order.getId()!=null&&o!=null){
                baseResponse.setCode(200);
                baseResponse.setMessage("更新成功!!!");
                return baseResponse;
            }else if (order.getId()==null&&o!=null){
                baseResponse.setCode(200);
                baseResponse.setMessage("新增成功!!!");
                return  baseResponse;
            }
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("新增或更新失败!!!");
        return baseResponse;
    }
}
