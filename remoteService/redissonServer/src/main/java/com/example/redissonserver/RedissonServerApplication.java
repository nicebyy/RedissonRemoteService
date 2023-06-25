package com.example.redissonserver;

import com.example.redissoncommon.remote.RemoteMemberServiceInterface;
import com.example.redissonserver.service.RemoteMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RedissonServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedissonServerApplication.class, args);
    }
}
