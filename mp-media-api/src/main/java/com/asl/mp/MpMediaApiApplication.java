package com.asl.mp;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "media-api", autoRefreshed = true,groupId = "mp")
public class MpMediaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpMediaApiApplication.class, args);
	}

}
