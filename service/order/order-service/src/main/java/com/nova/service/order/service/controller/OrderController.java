package com.nova.service.order.service.controller;

import com.nova.common.log.annotation.ApiLog;
import com.nova.common.web.annotation.mapping.v1.PublicV1DeleteMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1PutMapping;
import com.nova.common.core.api.ApiCode;
import com.nova.common.web.annotation.mapping.v1.PublicV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1PostMapping;
import com.nova.service.order.api.entity.Order;
import com.nova.service.order.service.service.OrderService;
import com.nova.service.uac.api.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order/orders")
public class OrderController {
    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @ApiLog
    @PublicV1PostMapping
    public void save(@RequestBody Order order) {
        // 获取登录用户信息
        // order.setUserId(1L);
        orderService.save(order);
    }

    @PublicV1GetMapping("/{id}")
    public Order getById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        return order;
    }

    @ApiLog
    @PublicV1DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") Long id) {
        orderService.removeById(id);
    }

    @ApiLog
    @PublicV1PutMapping("/{id}:cancel")
    public void cancelById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        order.setStatus(1);
        orderService.updateById(order);
    }
}
