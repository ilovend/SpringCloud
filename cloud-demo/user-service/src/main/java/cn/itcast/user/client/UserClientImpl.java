package cn.itcast.user.client;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;

public class UserClientImpl implements UserClient {
    @Override
    public User queryById(Long id) {
        return null;
    }
}
