package com.mx.server;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mx.server.dao")
@NacosPropertySource(dataId = "graphql-service", autoRefreshed = true,groupId = "mp")
public class GraphqlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlServiceApplication.class, args);
    }
}
