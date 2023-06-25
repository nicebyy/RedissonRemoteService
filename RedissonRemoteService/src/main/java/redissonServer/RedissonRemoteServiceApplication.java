package redissonServer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import redissonServer.service.RemoteMemberService;
import redissonServer.service.RemoteMemberServiceInterface;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RedissonRemoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedissonRemoteServiceApplication.class, args);
	}

	private final RedissonClient redissonClient;

	@EventListener(ApplicationReadyEvent.class)
	public void registerRemoteService() throws IOException {
		log.info("RedissonClient config={}", redissonClient.getConfig().toJSON());
		RRemoteService remoteService = redissonClient.getRemoteService();
		//UserService 파라미터 추가
		RemoteMemberService remoteMemberService = new RemoteMemberService();
		remoteService.register(RemoteMemberServiceInterface.class, remoteMemberService);
	}
}
