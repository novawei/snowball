package com.nova.service.order.api.entity;

import com.nova.common.mybatis.base.BaseEntity;
import lombok.Data;

@Data
public class Order extends BaseEntity {
    private Long id;
    private Long userId;
    private String name;
    private Integer status;
}