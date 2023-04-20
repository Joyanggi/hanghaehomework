package com.example.hanghaehomework.service;

import com.example.hanghaehomework.dto.MemberRequestDto;
import com.example.hanghaehomework.entity.Member;
import com.example.hanghaehomework.jwt.JwtUtil;
import com.example.hanghaehomework.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;



    @Transactional
        public void login(MemberRequestDto memberRequestDto, HttpServletRequest response){
            String userName = memberRequestDto.getUsername();
            String password = memberRequestDto.getPassword();

            Member member = memberRepository.findByUserId(userName).orElseThrow(
                    () -> new IllegalArgumentException("등록된 아이디가 없습니다.")
            );

            if(!member.getPassword().equals(password)){
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUsername())));
        }


}
