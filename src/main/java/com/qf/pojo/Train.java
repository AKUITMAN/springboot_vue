package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "train")
@SuppressWarnings("all")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "from_time")
    private String fromTime;

    @Column(name = "to_time")
    private String toTime;

    @Column(name = "no_seat")
    private Integer noSeat;

    @Column(name = "hard_seat")
    private Integer hardSeat;

    private Integer sleeper;
}
