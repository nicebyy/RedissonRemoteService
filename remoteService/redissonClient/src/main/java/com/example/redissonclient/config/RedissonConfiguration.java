package com.example.redissonclient.config;

import com.example.redissoncommon.remote.RemoteMemberServiceInterface;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RemoteInvocationOptions;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedissonConfiguration {

    @Value("${spring.data.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.data.redis.port}")
    private String REDIS_PORT;

    @Bean
    public RedissonClient redissonClient() {
        Config redisConfig = new Config();
        redisConfig.setCodec(new JsonJacksonCodec());
        redisConfig.useSingleServer()
                .setAddress(REDIS_HOST + ":" + REDIS_PORT);
        return Redisson.create(redisConfig);
    }

    @Bean
    public RemoteMemberServiceInterface remoteMemberService(){
        // 호출할 RemoteMemberService 를 RedisClient 로 부터 호출 하는 부분
        return redissonClient()
                .getRemoteService()
                .get(RemoteMemberServiceInterface.class, RemoteInvocationOptions.defaults()
//                        .noAck().noResult() // 변환 결과가 없을 경우 추가할 수 있는 옵션. blocking 이 걸리지 않는다.
                );
    }
}