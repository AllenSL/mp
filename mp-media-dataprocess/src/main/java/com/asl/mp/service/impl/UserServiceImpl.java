package com.asl.mp.service.impl;

import com.asl.mp.entity.User;
import com.asl.mp.repository.UserRepository;
import com.asl.mp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author asl
 * @Date 2021/4/27 13:43
 **/
@Service
public class UserServiceImpl implements IUserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> queryByDes(String des) {
        return userRepository.findUserByDesc(des);
    }

    @Override
    public void updateByName(String name) {

    }

    public static void main(String[] args) throws Exception{
        User user = new User();
        user.setName("哈哈");
        String city = Optional.ofNullable(user)
                .map(u-> u.getName())
                .orElseThrow(()->new Exception("取值错误"));
        System.out.println(city);
    }
}
