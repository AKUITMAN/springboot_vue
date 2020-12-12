package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uid;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address_from")
    private Integer addressFrom;

    @Column(name = "address_to")
    private Integer addressTo;

    @Column(name = "rider_id")
    private Integer riderId;

    @Column(name = "time")
    private String time;
}
