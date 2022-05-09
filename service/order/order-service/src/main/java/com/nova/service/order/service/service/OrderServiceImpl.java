package com.nova.service.order.service.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.service.order.api.entity.Order;
import com.nova.service.order.service.dao.OrderMapper;
import com.nova.service.uac.api.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private UserClient userClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean testTransaction(Order order) {
        // 保存成功
        this.save(order);
        userClient.getUserThrowException();
        return false;
    }
}
