package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rider")
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rider_name")
    private String riderName;

    @Column(name = "id_card")
    private Integer idCard;

    private Integer gender;

    private String role;

    @Column(name = "user_id")
    private Integer userId;
}
