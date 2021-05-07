package com.asl.mp.ws;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @GetMapping(value = "/get")
    public boolean get() {
        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("key","value");
        applicationEventPublisher.publishEvent(objectObjectHashMap);
        return useLocalCache;
    }


    @PostMapping(value = "/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> parms) {
        log.info("username:{},pwd:{}",parms.get("username"),parms.get("pwd"));
        return parms;
    }

}
