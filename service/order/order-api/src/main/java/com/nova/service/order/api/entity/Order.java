package com.nova.service.order.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;
    private Long userId;
    private String name;
    private Integer status;
    private Date createTime;
}