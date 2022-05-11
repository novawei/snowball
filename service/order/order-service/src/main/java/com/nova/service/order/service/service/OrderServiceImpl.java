package com.nova.service.order.service.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.service.order.api.entity.Order;
import com.nova.service.order.service.dao.OrderMapper;
import com.nova.service.uac.api.client.UserClient;
import com.nova.service.uac.api.entity.User;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private UserClient userClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransaction(Order order) {
        // call user service to save new user
        User user = new User();
        user.setId(order.getUserId());
        user.setUsername("test" + user.getId());
        user.setName(user.getUsername());
        user.setPassword(user.getUsername());
        user.setSalt(user.getUsername());
        userClient.save(user);
        System.out.println("save new user: " + user);
        // save order
        this.save(order);
        System.out.println("save new order: " + order);
        // throw exception
        user = userClient.getById(order.getUserId());
        System.out.println("try to get new user: " + user);
        userClient.getUserThrowException();
        System.out.println("throw exception");
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void testGlobalTransaction(Order order) {
        // call user service to save new user
        User user = new User();
        user.setId(order.getUserId());
        user.setUsername("test" + user.getId());
        user.setName(user.getUsername());
        user.setPassword(user.getUsername());
        user.setSalt(user.getUsername());
        userClient.save(user);
        System.out.println("save new user: " + user);
        // save order
        this.save(order);
        System.out.println("save new order: " + order);
        // throw exception
        user = userClient.getById(order.getUserId());
        System.out.println("try to get new user: " + user);
        userClient.getUserThrowException();
        System.out.println("throw exception");
    }
}
