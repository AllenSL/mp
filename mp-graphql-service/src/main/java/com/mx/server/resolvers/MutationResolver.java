package com.mx.server.resolvers;

import com.mx.server.entity.User;
import com.mx.server.entity.input.AddUserInput;
import com.mx.server.entity.response.ResponseBuilder;
import com.mx.server.entity.response.Result;
import com.mx.server.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Slf4j
@Component
public class MutationResolver implements GraphQLMutationResolver {


    @Resource
    private UserService userService;

    public Result addUser(String mail, String nickname, String password, String description) {
        log.info("Mutation Resolver ==> addUser");
        log.info("params: mail:{}, nickname:{}, password:{}, description:{}",
                mail, nickname, password, description);
        return userService.addUser(mail, nickname, password, description);
    }

    public Result deleteUser(String id) {
        if(StringUtils.isAnyBlank(id)){
            return ResponseBuilder.getRespCodeMsg(-101, "参数缺失");
        }
        log.info("Mutation Resolver ==> deleteUser");
        log.info("params: id:{}", id);
        return userService.deleteUser(id);
    }
    
    public User updateUser(Integer id, String mail, String nickname, String description) {
        log.info("Mutation Resolver ==> updateUser");
        log.info("params: id:{}, mail:{}, nickname:{}, description:{}",
                id, mail, nickname, description);
        return userService.updateUser(id, mail, nickname, description);
    }

    public User addUserByInput(AddUserInput addUserInput){
        log.info("Mutation Resolver ==> addUserByInput");
        log.info("params: {}", addUserInput);
        return userService.addUserInput(addUserInput);
    }
}
