package com.qf.dao;

import com.qf.pojo.req.RiderReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RiderMapper {
    List<RiderReq> findAll();
}
