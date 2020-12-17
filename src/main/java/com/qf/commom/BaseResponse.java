package com.qf.commom;

import lombok.Data;

@Data
public class BaseResponse {
    private Integer code;

    private Object data;

    private String message;

    private Long total;
}
