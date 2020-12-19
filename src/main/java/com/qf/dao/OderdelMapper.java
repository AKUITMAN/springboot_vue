package com.qf.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OderdelMapper {
    Integer deleteById(@Param("id") Integer id);
}
