package com.asl.mp;

import com.asl.mp.template.RedisRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 配置类
 *
 * @author fuyao
 * @date 2019年7月17日
 */
@Configuration
@ConditionalOnClass(RedisRepository.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfigure {

    /**
     * Redis repository redis repository.
     *
     * @param redisTemplate the redis template
     * @return the redis repository
     */
    @Bean
    @ConditionalOnMissingBean
    public RedisRepository redisRepository(RedisTemplate<String, String> redisTemplate) {
        return new RedisRepository(redisTemplate);
    }
}
