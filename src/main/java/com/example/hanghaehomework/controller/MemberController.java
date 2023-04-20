package com.example.hanghaehomework.controller;

import com.example.hanghaehomework.dto.MemberRequestDto;
import com.example.hanghaehomework.dto.SignupRequestDto;
import com.example.hanghaehomework.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/auth/login")
    public String login(MemberRequestDto memberRequestDto, HttpServletResponse response){
        memberService.login(memberRequestDto, response);
        return "로그인 성공";
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }

}
