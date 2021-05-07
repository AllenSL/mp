package com.asl.mp.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class NacosConfig {
    @Value("${server.port}")
    private int serverPort;
    @Value("${spring.application.name}")
    private String applicationName;
    @NacosInjected
    private NamingService namingService;
    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName,"mp", "192.168.180.3", serverPort);
    }
}
