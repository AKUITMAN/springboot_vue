package com.qf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private  String email;

    private Integer gender;

    @Column(name = "id_card")
    private Integer idCard;

    private Integer level;
}
