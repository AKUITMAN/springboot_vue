package com.qf.service.impl;

import com.qf.commom.BaseResponse;
import com.qf.dao.AddressMapper;
import com.qf.dao.AddressRepository;
import com.qf.pojo.Address;
import com.qf.pojo.req.AddressReq;
import com.qf.pojo.req.MapData;
import com.qf.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    AddressMapper addressMapper;

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
    public BaseResponse saveOrFlush(Address address) {
        BaseResponse baseResponse = new BaseResponse();
        if (address!=null){
            Address o = (Address)repository.saveAndFlush(address);
            baseResponse.setData(o);
            if (address.getId()!=null&&o!=null){
                baseResponse.setCode(200);
                baseResponse.setMessage("更新成功!!!");
                return baseResponse;
            }else if (address.getId()==null&&o!=null){
                baseResponse.setCode(200);
                baseResponse.setMessage("新增成功!!!");
                return  baseResponse;
            }
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("新增或更新失败!!!");
        return baseResponse;
    }

    @Override
    public BaseResponse findMap() {
        BaseResponse baseResponse = new BaseResponse();
        List<AddressReq> all=addressMapper.findMap();
        ArrayList<MapData> objects = new ArrayList<>();
        for (AddressReq aa:all
             ) {
            MapData mapData = new MapData();
            mapData.setName(aa.getProvince());
            mapData.setValue(aa.getHeat());
            objects.add(mapData);
        }
        if (all!=null){
            baseResponse.setData(objects);
            baseResponse.setCode(200);
            return baseResponse;
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("错误");
        return baseResponse ;
    }
}
