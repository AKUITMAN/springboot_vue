package com.qf.pojo.req;

import lombok.Data;

@Data
public class OrderReq {
    private Integer id;

    private String userName;

    private String siteFromName;

    private String siteToName;

    private String riderName;

    private String time;

    private String putDay;

    private String seatName;

    private Integer price;
}
