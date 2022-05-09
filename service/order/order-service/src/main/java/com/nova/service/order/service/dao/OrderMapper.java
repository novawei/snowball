package com.nova.service.order.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nova.service.order.api.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
