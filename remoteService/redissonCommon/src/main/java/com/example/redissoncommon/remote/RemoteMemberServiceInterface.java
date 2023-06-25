package com.example.redissoncommon.remote;


import com.example.redissoncommon.entity.Member;

public interface RemoteMemberServiceInterface {

    public Member renameMember(Member member, String newName);
}
