package cn.itcast.order.service;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        Long userId = order.getUserId();
        //2.利用Feign发起http请求,查询用户
        User user = userClient.queryById(userId);
        //3.封装User到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
