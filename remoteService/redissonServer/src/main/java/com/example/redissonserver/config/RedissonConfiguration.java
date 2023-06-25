package com.example.redissonserver.config;

import com.example.redissoncommon.remote.RemoteMemberServiceInterface;
import com.example.redissonserver.service.RemoteMemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedissonConfiguration {

    @Value("${spring.data.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.data.redis.port}")
    private String REDIS_PORT;

    private final RemoteMemberService remoteMemberService;

    @Bean
    public RedissonClient redissonClient() {
        Config redisConfig = new Config();
        redisConfig.setCodec(new JsonJacksonCodec());
        redisConfig.useSingleServer()
               .setAddress(REDIS_HOST + ":" + REDIS_PORT);
        return Redisson.create(redisConfig);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void registerRemoteService(){
        RRemoteService remoteService = redissonClient().getRemoteService();
        remoteService.register(RemoteMemberServiceInterface.class,remoteMemberService);
    }
}
