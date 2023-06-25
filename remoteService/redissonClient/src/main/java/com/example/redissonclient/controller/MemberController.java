package com.example.redissonclient.controller;

import com.example.redissonclient.service.MemberService;
import com.example.redissoncommon.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j

@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public Member createMember(@RequestParam String name){
        Member member = memberService.createMember(name);
        log.info("created member => {}",member);
        return member;
    }

    @PostMapping("/rename")
    public Member renameMember(@RequestParam Long id,@RequestParam String name){
        Member findMember = memberService.findMemberById(id);
        return memberService.callRemoteRenameService(findMember,name);
    }
}
