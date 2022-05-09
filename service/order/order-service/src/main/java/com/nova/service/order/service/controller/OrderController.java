package com.nova.service.order.service.controller;

import com.nova.common.web.annotation.mapping.v1.ProtectedV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1PostMapping;
import com.nova.common.web.annotation.mapping.v2.ProtectedV2GetMapping;
import com.nova.common.web.annotation.mapping.v2.ProtectedV2PostMapping;
import com.nova.service.order.api.entity.Order;
import com.nova.service.order.service.service.OrderService;
import com.nova.service.uac.api.client.UserClient;
import com.nova.service.uac.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nova.common.core.api.ApiResult;
import com.nova.common.core.util.BeanUtils;

@RestController
@RequestMapping("/order/orders")
public class OrderController {
    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @PublicV1PostMapping
    public ApiResult createOrder(@RequestBody Order order) {
        User user = userClient.getUserById(order.getUserId());
        System.out.println(user);
        orderService.save(order);
        return ApiResult.ok();
    }

    @PublicV1GetMapping("/{id}")
    public ApiResult<Order> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        System.out.println(order);
        return ApiResult.ok(order);
    }

    @PublicV1PostMapping("/test/transaction")
    public ApiResult<Order> getOrderById(@RequestBody Order order) {
        boolean ret = orderService.testTransaction(order);
        return ret ? ApiResult.ok() : ApiResult.fail();
    }
}
