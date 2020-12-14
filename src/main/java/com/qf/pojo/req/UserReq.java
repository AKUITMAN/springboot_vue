package com.qf.pojo.req;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserReq {
    private Integer id;

    private String userName;

    private String password;

    private  String email;

    private Integer gender;

    private Integer idCard;

    private Integer level;

    private String code;
}
