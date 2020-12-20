package com.qf.dao;

import com.qf.pojo.Rider;
import com.qf.pojo.req.RiderReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RiderMapper {
    List<RiderReq> findAll();
    List<Rider> findByUid(@Param("userId") Integer userId);
    Rider findById(@Param("id") Integer id);
}
