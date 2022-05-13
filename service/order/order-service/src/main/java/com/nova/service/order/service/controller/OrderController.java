package com.nova.service.order.service.controller;

import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiBusinessException;
import com.nova.common.web.annotation.mapping.v1.PublicV1GetMapping;
import com.nova.common.web.annotation.mapping.v1.PublicV1PostMapping;
import com.nova.service.order.api.entity.Order;
import com.nova.service.order.service.service.OrderService;
import com.nova.service.uac.api.client.UserClient;
import com.nova.service.uac.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/orders")
public class OrderController {
    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @PublicV1PostMapping
    public void createOrder(@RequestBody Order order) {
        User user = userClient.getById(order.getUserId());
        if (user == null) {
            throw new ApiBusinessException(ApiCode.USER_NOT_EXIST, order.getUserId());
        }
        System.out.println(user);
        orderService.save(order);
    }

    @PublicV1GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        System.out.println(order);
        return order;
    }

    @PublicV1GetMapping("/hello/{id}/name")
    public Boolean testPureObject(@PathVariable("id") Long id) {
        String name = userClient.getName(id);
        System.out.println("name get from user client: " + name);
        return name == null;
    }

    @PublicV1GetMapping("/hello/exception")
    public Order test() {
        User user = userClient.getUserThrowException();
        System.out.println("return from getUserThrowException");
        System.out.println(user);
        Order order = orderService.getById(1);
        System.out.println(order);
        return order;
    }

    @PublicV1PostMapping("/hello/{scope}/transaction")
    public void testTransaction(@PathVariable("scope") String scope, @RequestBody Order order) {
        if (scope.equals("global")) {
            orderService.testGlobalTransaction(order);
        } else {
            orderService.testTransaction(order);
        }
    }
}
