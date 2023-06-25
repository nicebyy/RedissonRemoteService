package com.example.redissonclient.service;

import com.example.redissoncommon.entity.Member;
import com.example.redissoncommon.remote.RemoteMemberServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final AtomicLong memberIdSequence = new AtomicLong(0L);
    private final ConcurrentHashMap<Long,Member> memberDB = new ConcurrentHashMap<Long,Member>();

    private final RemoteMemberServiceInterface remoteMemberServiceInterface;

    public Member callRemoteRenameService(Member member,String name){

        if(member==null)
            throw new IllegalArgumentException("couldn't find member");
        log.info("remoteMemberServiceInterface => {}",remoteMemberServiceInterface.getClass());
        return remoteMemberServiceInterface.renameMember(member,name);
    }

    public Member findMemberById(Long id){
        return Optional.ofNullable(memberDB.get(id))
                .orElse(null);
    }

    public Member createMember(String name){
        Member member = new Member(incrementSequence(), name);
        memberDB.put(member.getId(),member);
        return member;
    }

    private Long incrementSequence(){
        return memberIdSequence.incrementAndGet();
    }
}
