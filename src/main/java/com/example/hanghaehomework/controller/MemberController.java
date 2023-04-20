package com.example.hanghaehomework.controller;

import com.example.hanghaehomework.dto.MemberRequestDto;
import com.example.hanghaehomework.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/auth/signup")
    public MemberRequestDto signup(){
        return memberService.signup();
    }
}
