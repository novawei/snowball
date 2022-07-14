package com.nova.service.order.service.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.service.order.api.entity.Order;
import com.nova.service.order.service.dao.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
