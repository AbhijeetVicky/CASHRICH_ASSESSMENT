package com.common.cashrich.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

    private int code;
    private String status;
    private T data;
}
