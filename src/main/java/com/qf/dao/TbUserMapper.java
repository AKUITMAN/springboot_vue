package com.qf.dao;

import com.qf.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserMapper {
    public TbUser findByUserName(String username);
}
