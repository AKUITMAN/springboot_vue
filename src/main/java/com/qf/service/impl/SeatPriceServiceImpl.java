package com.qf.service.impl;

import com.qf.commom.BaseResponse;
import com.qf.dao.SeatPriceRepository;
import com.qf.pojo.SeatPrice;
import com.qf.service.SeatPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatPriceServiceImpl implements SeatPriceService {

    @Autowired
    SeatPriceRepository repository;

    @Override
    public BaseResponse findAll() {
        List all = repository.findAll();
        BaseResponse baseResponse = new BaseResponse();
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
    public BaseResponse saveOrFlush(SeatPrice seatPrice) {
        BaseResponse baseResponse = new BaseResponse();
        if (seatPrice!=null){
            SeatPrice o = repository.saveAndFlush(seatPrice);
            baseResponse.setData(o);
            if (seatPrice.getId()!=null&&o!=null){
                baseResponse.setCode(200);
                baseResponse.setMessage("更新成功!!!");
                return baseResponse;
            }else if (seatPrice.getId()==null&&o!=null){
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
