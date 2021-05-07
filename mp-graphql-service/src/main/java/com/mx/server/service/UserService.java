package com.mx.server.service;

import com.mx.server.dao.UserMapper;
import com.mx.server.entity.User;
import com.mx.server.entity.input.AddUserInput;
import com.mx.server.entity.response.ResponseBuilder;
import com.mx.server.entity.response.Result;
import com.mx.server.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User getUserByNickname(String nickname){
        log.info("Service ==> getUserByNickname");
        return userMapper.getUserByNickname(nickname);
    }

    public List<User> listUsers(){
        log.info("Service ==> listUsers");
        return userMapper.listUsers();
    }

    public Result addUser(String mail, String nickname, String password, String description){
        log.info("Service ==> getUser");

        User userDb = userMapper.getUserByNickname(nickname);
        if (null != userDb){
            return ResponseBuilder.getRespCodeMsg(-110, "用户昵称存在");
        }

        User addUser = new User();
        addUser.setMail(mail);
        addUser.setNickname(nickname);
        addUser.setPassword(password);
        addUser.setDescription(description);

        userMapper.addUser(addUser);

        return ResponseBuilder.getRespCodeMsg(100, "Success");
    }

    public Result deleteUser(String id){
        log.info("Service ==> deleteUser");

        User user = userMapper.getUserById(id);
        if (null == user){
            return ResponseBuilder.getRespCodeMsg(-500, "数据不存在");
        }

        userMapper.deleteUser(id);
        return ResponseBuilder.getRespCodeMsg(100, "Success");
    }

    public User updateUser(Integer id,String mail, String nickname, String description){
        log.info("Service ==> updateUser");
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setMail(mail);
        updateUser.setNickname(nickname);
        updateUser.setDescription(description);
        userMapper.updateUser(updateUser);
        return updateUser;
    }

    public User addUserInput(AddUserInput addUserInput){
        log.info("Service ==> addUserInput");
        User addUser = new User();
        addUser.setMail(addUserInput.getMail());
        addUser.setNickname(addUserInput.getNickname());
        addUser.setPassword(addUserInput.getPassword());
        addUser.setDescription(addUserInput.getDescription());
        userMapper.insert(addUser);
        userMapper.addUser(addUser);
        return addUser;
    }
}
