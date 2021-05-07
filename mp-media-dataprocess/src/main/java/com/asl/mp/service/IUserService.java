package com.asl.mp.service;

import com.asl.mp.entity.User;

import java.util.List;

/**
 * @ClassName IUserService
 * @Description
 * @Author asl
 * @Date 2021/4/27 13:43
 **/
public interface IUserService {

    void save(User user);

    List<User> queryByDes(String des);

    void updateByName(String name);
}
