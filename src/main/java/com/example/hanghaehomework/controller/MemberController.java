package com.example.hanghaehomework.controller;

import com.example.hanghaehomework.dto.MemberRequestDto;
import com.example.hanghaehomework.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/auth/login")
    public String login(MemberRequestDto memberRequestDto, HttpServletRequest response){
        memberService.login(memberRequestDto, response);
        return "로그인 성공";
    }
}
