package com.qf.pojo.req;

import lombok.Data;


@Data
public class RiderReq {
    private Integer id;

    private String riderName;

    private Integer idCard;

    private Integer gender;

    private String role;

    private Integer userId;

    private String userName;
}
