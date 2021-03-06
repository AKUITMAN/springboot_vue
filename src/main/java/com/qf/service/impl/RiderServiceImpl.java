package com.qf.service.impl;

import com.qf.commom.BaseResponse;
import com.qf.dao.RiderMapper;
import com.qf.dao.RiderRepository;
import com.qf.pojo.Rider;
import com.qf.pojo.Train;
import com.qf.pojo.req.PeopleList;
import com.qf.pojo.req.RideList;
import com.qf.pojo.req.RiderReq;
import com.qf.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    RiderRepository repository;

    @Autowired
    RiderMapper riderMapper;

    @Override
    public BaseResponse findAll() {
        List<RiderReq> all = riderMapper.findAll();
        List<PeopleList> peopleList=new ArrayList<>();
        for (RiderReq riderReq:all
        ) {
            PeopleList peopleList1 = new PeopleList();
            peopleList1.setId(riderReq.getId());
            peopleList1.setIdCard(riderReq.getIdCard());
            peopleList1.setRiderName(riderReq.getRiderName());
            peopleList1.setUserName(riderReq.getUserName());
            if (riderReq.getGender()==1){
                peopleList1.setGender("男");
            }else if (riderReq.getGender()==0){
                peopleList1.setGender("女");
            }if (riderReq.getRole().equals("1")){
                peopleList1.setRole("成人");
            }else if (riderReq.getRole().equals("0")){
                peopleList1.setRole("学生");
            }
            peopleList.add(peopleList1);

        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(peopleList);
        baseResponse.setCode(200);
        baseResponse.setMessage("查询成功");
        return baseResponse;
    }

    @Override
    public BaseResponse findById(Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        Rider byId =riderMapper.findById(id);
        if (byId!=null){
            RideList rideList = new RideList();
            rideList.setId(byId.getId());

            rideList.setIdCard(byId.getIdCard());
            rideList.setRiderName(byId.getRiderName());
            rideList.setRole(byId.getRole());
            if (byId.getGender() == 1) {
                rideList.setGender("男");
            } else if (byId.getGender() == 0) {
                rideList.setGender("女");
            }
            baseResponse.setData(rideList);
            baseResponse.setCode(200);
            baseResponse.setMessage("查询成功!!!");
            return baseResponse;

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
    public BaseResponse saveOrFlush(Rider rider) {
        BaseResponse baseResponse = new BaseResponse();
        if (rider!=null){
            Rider o = repository.saveAndFlush(rider);
            baseResponse.setData(o);
            if (rider.getId()!=null&&o!=null){
                baseResponse.setCode(200);
                baseResponse.setMessage("更新成功!!!");
                return baseResponse;
            }else if (rider.getId()==null&&o!=null){
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
    public BaseResponse findByUid(Integer userId) {
        BaseResponse baseResponse = new BaseResponse();
        List<Rider> all = riderMapper.findByUid(userId);
        ArrayList<RideList> objects = new ArrayList<>();
        for (Rider rider : all
        ) {
            RideList rideList = new RideList();
            rideList.setId(rider.getId());
            rideList.setIdCard(rider.getIdCard());
            rideList.setRiderName(rider.getRiderName());

            if (rider.getGender() == 1) {
                rideList.setGender("男");
            } else if (rider.getGender() == 0) {
                rideList.setGender("女");
            }
            if (rider.getRole().equals("1")) {
                rideList.setRole("成人");
            } else if (rider.getRole().equals("0")) {
                rideList.setRole("学生");
            }
            objects.add(rideList);
        }
        baseResponse.setData(objects);
        baseResponse.setCode(200);
        baseResponse.setMessage("查询成功");
        return baseResponse;
    }
}
