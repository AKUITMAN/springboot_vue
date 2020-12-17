package com.qf.pojo.req;

import lombok.Data;

@Data
public class UserList {
    private Integer id;

    private String userName;

    private String password;

    private  String email;

    private String gender;

    private Integer idCard;

    private String level;

}
