package com.mx.server.resolvers;

import com.mx.server.entity.User;
import com.mx.server.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Component
public class QueryResolver implements GraphQLQueryResolver {


    @Resource
    private UserService userService;

    public User user(String nickname) {
        log.info("Query Resolver ==> user");
        log.info("params: nickname:{}", nickname);
        return userService.getUserByNickname(nickname);
    }

    public List<User> users() {
        log.info("Query Resolver ==> users");
        return userService.listUsers();
    }
}
