package com.example.redissonserver.service;

import com.example.redissoncommon.entity.Member;
import com.example.redissoncommon.remote.RemoteMemberServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RemoteMemberService implements RemoteMemberServiceInterface {
    @Override
    public Member renameMember(Member member,String newName){
        log.info("before rename => {} ",member);
        member.setName(newName);
        log.info("after rename => {}",member);
        return member;
    }
}
