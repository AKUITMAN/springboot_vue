package com.qf.dao;

import com.qf.pojo.req.OrderReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminOrderMapper {
    List<OrderReq> findAll();
}
