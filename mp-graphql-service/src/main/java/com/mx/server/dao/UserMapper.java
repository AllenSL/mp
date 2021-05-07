package com.mx.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mx.server.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    User getUserById(String id);
    User getUserByNickname(String nickname);
    List<User> listUsers();
    void addUser(User user);
    void deleteUser(String id);
    void updateUser(User user);
}
